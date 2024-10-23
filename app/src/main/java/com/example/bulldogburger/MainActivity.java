package com.example.bulldogburger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnRegistrarPedido).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegistrarPedidoActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnVerPedidos).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, VerPedidosActivity.class);
            startActivity(intent);
        });


    }
}