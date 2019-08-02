package com.burhan.rashid.daggermvprx.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.burhan.rashid.daggermvprx.App;
import com.burhan.rashid.daggermvprx.R;
import com.burhan.rashid.daggermvprx.contracter.HomeScreenContract;
import com.burhan.rashid.daggermvprx.data.base.BaseFragments;
import com.burhan.rashid.daggermvprx.data.base.BasePresenter;
import com.burhan.rashid.daggermvprx.data.component.DaggerBaseFragmentScreenComponent;
import com.burhan.rashid.daggermvprx.models.Post;
import com.burhan.rashid.daggermvprx.presenters.HomeScreenPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Burhanuddin on 7/1/2017.
 */

public class HomeFragment extends BaseFragments implements HomeScreenContract.View {
    public static final String TAG = HomeFragment.class.getSimpleName();
    @BindView(R.id.my_list)
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Unbinder unbinder;

    @Override
    public BasePresenter getPresenter() {
        return mHomeScreenPresenter;
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    HomeScreenPresenter mHomeScreenPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        DaggerBaseFragmentScreenComponent.builder()
                .netComponent(App.getInstance().getNetComponent())
                .createHomeView(this)
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Call the method in HomePresenter to make Network Request
        mHomeScreenPresenter.loadPost();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showPosts(List<Post> posts) {
        //Loop through the posts and get the title of the post and add it to our list object
        for (int i = 0; i < posts.size(); i++) {
            list.add(posts.get(i).getTitle());
        }
        //Create the array adapter and set it to list view
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}
