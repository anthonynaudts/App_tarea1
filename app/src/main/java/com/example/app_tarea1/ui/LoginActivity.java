package com.example.app_tarea1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_tarea1.R;
import com.example.app_tarea1.network.ApiService;
import com.example.app_tarea1.network.RetrofitClient;
import com.example.app_tarea1.model.ApiResponse;
import com.example.app_tarea1.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvErrorMessage = findViewById(R.id.tvErrorMessage);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    tvErrorMessage.setText("Por favor ingrese usuario y contraseña");
                    return;
                }

                ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
                Call<ApiResponse> call = apiService.login(new Usuario(username, password));

                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {
                            ApiResponse apiResponse = response.body();
                            Log.d("Login", "Usuario autenticado: " + apiResponse.toString());

                            Intent intent = new Intent(LoginActivity.this, InicioActivity.class);
                            startActivity(intent);
                        } else {
                            Log.e("Login", "Error de autenticación: " + response.message());
                            tvErrorMessage.setText("Usuario o contraseña incorrectos");
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        Log.e("Login", "Error de conexión: " + t.getMessage());
                        tvErrorMessage.setText("Error de conexión");
                    }
                });
            }
        });
    }
}
