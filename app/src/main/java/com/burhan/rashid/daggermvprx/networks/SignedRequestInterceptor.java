package com.burhan.rashid.daggermvprx.networks;

import android.os.ConditionVariable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.burhan.rashid.daggermvprx.util.ApiConstants;
import com.burhan.rashid.daggermvprx.util.AppConstants;
import com.burhan.rashid.daggermvprx.util.PrefUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * This class has two tasks:
 * 1) sign requests with the auth token, when available
 * 2) try to refresh a new token
 */
public class SignedRequestInterceptor implements Interceptor {
    // these two static variables serve for the pattern to refresh a token
    private final static ConditionVariable LOCK = new ConditionVariable(true);
    private static final AtomicBoolean mIsRefreshing = new AtomicBoolean(false);
    private static final long REFRESH_WAIT_TIMEOUT = 2 * 1000;
    private Retrofit retrofit;

    public SignedRequestInterceptor(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        // Request customization: add request headers
        String authToken = PrefUtils.getString(AppConstants.Preference.PREF_AUTH_TOKEN, "");
        String clientToken = PrefUtils.getString(AppConstants.Preference.PREF_CLIENT_TOKEN, "");
        //TODO: Change the static tokens
        authToken = "authToken";
        clientToken = "clientToken";
        request = chain.request().newBuilder()
                .header(ApiConstants.AUTHORIZATION, "Bearer " + authToken)
                .header(ApiConstants.CLIENT_TOKEN, clientToken)
                .method(chain.request().method(), chain.request().body()).build();
        // 2. proceed with the request
        Response response = chain.proceed(request);
        // 3. check the response: have we got a 401?
        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            if (!TextUtils.isEmpty(authToken)) {
               /* *Because we send out multiple HTTP requests in parallel, they might all list a 401
                at the same time.
                *Only one of them should refresh the token, because otherwise we
                'd refresh the same token multiple times
                        * and that is bad.Therefore we have these two static objects,
                a ConditionVariable and a boolean.The
                        * first thread that gets here closes the ConditionVariable and changes the
                boolean flag*/
                if (mIsRefreshing.compareAndSet(false, true)) {
                    LOCK.close();
                    // we're the first here. let's refresh this token.
                    // it looks like our token isn't valid anymore.
//                    mAccountManager.invalidateAuthToken(AuthConsts.ACCOUNT_TYPE, token);
                    ResponseBody newToken = retrofit.create(ApiService.class).refreshAuthToken(authToken, clientToken).execute().body();
                    // do we have an access token to refresh?
                    String refreshToken = newToken.toString();
                    if (!TextUtils.isEmpty(refreshToken)) {
                        // Storing the token somewhere.
                       /* authToken = newToken.getData().getToken();
                        clientToken = newToken.getData().getClient_token();*/
                        authToken = "authToken";
                        clientToken = "clientToken";
                        PrefUtils.putString(AppConstants.Preference.PREF_AUTH_TOKEN, authToken);
                        PrefUtils.putString(AppConstants.Preference.PREF_CLIENT_TOKEN, clientToken);
                        Log.e("New Token", authToken + "\n" + clientToken);
                        response = chain.proceed(request.newBuilder()
                                .header(ApiConstants.AUTHORIZATION, "Bearer " + authToken)
                                .header(ApiConstants.CLIENT_TOKEN, clientToken)
                                .build());
                    }
                    LOCK.open();
                    mIsRefreshing.set(false);
                } else {
                    // Another thread is refreshing the token for us, let's wait for it.
                    boolean conditionOpened = LOCK.block(REFRESH_WAIT_TIMEOUT);
                    // If the next check is false, it means that the timeout expired, that is - the refresh
                    // stuff has failed. The thread in charge of refreshing the token has taken care of
                    // redirecting the user to the login activity.
                    if (conditionOpened) {
                        // another thread has refreshed this for us! thanks!
                        // sign the request with the new token and proceed
                        // return the outcome of the newly signed request
                        // Request customization: add request headers
                        authToken = PrefUtils.getString(AppConstants.Preference.PREF_AUTH_TOKEN, "");
                        clientToken = PrefUtils.getString(AppConstants.Preference.PREF_CLIENT_TOKEN, "");
                        response = chain.proceed(request.newBuilder()
                                .header(ApiConstants.AUTHORIZATION, "Bearer " + authToken)
                                .header(ApiConstants.CLIENT_TOKEN, clientToken)
                                .build());
                    }
                }
            }
        }
        // check if still unauthorized (i.e. refresh failed)
        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            // clean your access token and prompt user for login again.
        }
        // returning the response to the original request
        return response;
    }
}