package com.mefy.cavista_demo.controller.networking;

import android.app.Activity;

import com.mefy.cavista_demo.utils.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient(Activity activity) {
        if (retrofit==null) {

            OkHttpClient client = new OkHttpClient();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            builder.addInterceptor(new AddCookiesInterceptor(activity)); // VERY IMPORTANT
            builder.addInterceptor(new ReceivedCookiesInterceptor(activity)); // VERY IMPORTANT
            client = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
