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

        // Inicializar vistas
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvErrorMessage = findViewById(R.id.tvErrorMessage);

        // Configurar botón de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener datos de usuario y contraseña
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Validar usuario y contraseña no vacíos
                if (username.isEmpty() || password.isEmpty()) {
                    tvErrorMessage.setText("Por favor ingrese usuario y contraseña");
                    return;
                }

                // Llamar a la API para login
                ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
                Call<ApiResponse> call = apiService.login(new Usuario(username, password));

                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {
                            // Si el login es exitoso, mostrar mensaje y redirigir
                            ApiResponse apiResponse = response.body();
                            Log.d("Login", "Usuario autenticado: " + apiResponse.toString());

                            // Redirigir a la actividad de inicio
                            Intent intent = new Intent(LoginActivity.this, InicioActivity.class);
                            startActivity(intent);
                        } else {
                            // Si la respuesta es un error, mostrar mensaje
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
