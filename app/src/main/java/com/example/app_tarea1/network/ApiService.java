package com.example.app_tarea1.network;

import com.example.app_tarea1.model.ApiResponse;
import com.example.app_tarea1.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/login")
    Call<ApiResponse> login(@Body Usuario usuario);

    @POST("/crearUsuario")
    Call<Usuario> crearUsuario(@Body Usuario usuario);

}
