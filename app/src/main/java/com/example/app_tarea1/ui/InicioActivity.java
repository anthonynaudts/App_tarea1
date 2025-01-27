package com.example.app_tarea1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_tarea1.R;

public class InicioActivity extends AppCompatActivity {

    private Button btnCrearUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);  // Asegúrate de tener la vista correcta

        // Inicializar el botón
        btnCrearUsuario = findViewById(R.id.btnCrearUsuario);

        // Configurar el evento onClick
        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirigir a la pantalla de creación de usuario
                Intent intent = new Intent(InicioActivity.this, CrearUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }
}
