package com.burhan.rashid.daggermvprx.networks;

import com.burhan.rashid.daggermvprx.models.Post;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiService {
    @GET("/posts")
    Observable<List<Post>> getPostList();

    @POST("/tokenUrl")
    Call<ResponseBody> refreshAuthToken(String authToken, String clientToken);
}