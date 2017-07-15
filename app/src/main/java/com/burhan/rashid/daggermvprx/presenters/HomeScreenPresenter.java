package com.burhan.rashid.daggermvprx.presenters;

import android.app.Application;
import android.util.Log;

import com.burhan.rashid.daggermvprx.contracter.HomeScreenContract;
import com.burhan.rashid.daggermvprx.contracter.MainScreenContract;
import com.burhan.rashid.daggermvprx.models.Post;
import com.burhan.rashid.daggermvprx.networks.ApiService;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Burhanuddin Rashid on 11-May-16.
 */
public class HomeScreenPresenter implements HomeScreenContract.Presenter {
    public static final String TAG = HomeScreenPresenter.class.getSimpleName();
    HomeScreenContract.View mView;
    private Application application;
    private ApiService apiService;

    @Inject
    public HomeScreenPresenter(Application application, ApiService apiService, HomeScreenContract.View mView) {
        this.application = application;
        this.apiService = apiService;
        this.mView = mView;
    }

    @Override
    public void loadPost() {
        mView.showLoading();
        apiService.getPostList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ");
                        mView.showErrorMessage(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        mView.showPosts(posts);
                    }
                });
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
