package com.burhan.rashid.daggermvprx.contracter;


import com.burhan.rashid.daggermvprx.models.Post;
import com.burhan.rashid.daggermvprx.data.base.BasePresenter;
import com.burhan.rashid.daggermvprx.data.base.BaseView;

import java.util.List;

/**
 * Created by Burhanuddin Rashid on 11-May-16.
 */
public interface HomeScreenContract {
    interface View extends BaseView {
        void showPosts(List<Post> posts);
    }

    interface Presenter extends BasePresenter {
        void loadPost();
    }
}
