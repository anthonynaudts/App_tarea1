package com.example.app_tarea1.network;

import com.example.app_tarea1.model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {
    @GET("users")
    Call<ApiResponse> getUsers();
}

