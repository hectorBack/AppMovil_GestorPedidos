package com.example.bulldogburger;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bulldogburger.SQLite.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrarPedidoActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText etProductos, etTotal, etUltimos4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pedido);

        dbHelper = new DatabaseHelper(this);
        etProductos = findViewById(R.id.etProductos);
        etTotal = findViewById(R.id.etTotal);
        etUltimos4 = findViewById(R.id.etUltimos4);

        findViewById(R.id.btnGuardarPedido).setOnClickListener(view -> guardarPedido());
    }

    private void guardarPedido() {
        String productos = etProductos.getText().toString();
        double total = Double.parseDouble(etTotal.getText().toString());
        String whatsapp = etUltimos4.getText().toString();
        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        boolean insertado = dbHelper.insertarPedido(productos, total, whatsapp, "Pendiente", fecha);
        if (insertado) {
            Toast.makeText(this, "Pedido guardado", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al guardar pedido", Toast.LENGTH_SHORT).show();
        }
    }
}
