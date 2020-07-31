package com.mefy.cavista_demo.controller.networking;

import com.mefy.cavista_demo.model.responces.CommonResponse;
import com.mefy.cavista_demo.utils.URL;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface ApiInterface {
    @Headers("Authorization:Client-ID 137cda6b5008a7c")
    @GET(URL.GET_SEARCHED_RESULT)
    Call<CommonResponse> deviceList(@Query("q") String data);

}
