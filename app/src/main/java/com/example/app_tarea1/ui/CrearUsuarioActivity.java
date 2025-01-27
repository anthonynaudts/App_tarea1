package com.example.app_tarea1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_tarea1.R;
import com.example.app_tarea1.network.ApiService;
import com.example.app_tarea1.network.RetrofitClient;
import com.example.app_tarea1.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearUsuarioActivity extends AppCompatActivity {

    private EditText etNombre, etContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        etNombre = findViewById(R.id.etNombre);
        etContrasena = findViewById(R.id.etContrasena);

        findViewById(R.id.btnAtras).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btnCrearUsuario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String contrasena = etContrasena.getText().toString();

                crearUsuario(nombre, contrasena);
            }
        });
    }

    private void crearUsuario(String nombre, String contrasena) {
        if (nombre.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa los datos completos", Toast.LENGTH_SHORT).show();
        } else {
            Usuario usuario = new Usuario(nombre, contrasena);

            ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
            Call<Usuario> call = apiService.crearUsuario(usuario);

            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(CrearUsuarioActivity.this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CrearUsuarioActivity.this, "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(CrearUsuarioActivity.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
