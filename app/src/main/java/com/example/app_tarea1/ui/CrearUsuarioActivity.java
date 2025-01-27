package com.example.app_tarea1.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_tarea1.R;
import com.example.app_tarea1.model.Usuario;
import com.example.app_tarea1.network.ApiService;
import com.example.app_tarea1.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearUsuarioActivity extends AppCompatActivity {

    private EditText etNombre, etContrasena;
    private Button btnCrearUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        // Inicializar vistas
        etNombre = findViewById(R.id.etNombre);
        etContrasena = findViewById(R.id.etContrasena);
        btnCrearUsuario = findViewById(R.id.btnCrearUsuario);

        // Configurar evento para el botón
        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuario();
            }
        });
    }

    private void crearUsuario() {
        // Obtener los valores ingresados
        String nombre = etNombre.getText().toString();
        String contrasena = etContrasena.getText().toString();

        if (nombre.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(CrearUsuarioActivity.this, "Por favor, ingresa todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear el objeto usuario
        Usuario usuario = new Usuario(nombre, contrasena);

        // Crear la llamada a la API
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<Usuario> call = apiService.crearUsuario(usuario);

        // Realizar la llamada a la API
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CrearUsuarioActivity.this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                    finish(); // Cerrar esta actividad y regresar
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
