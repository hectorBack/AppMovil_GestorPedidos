package com.example.bulldogburger;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bulldogburger.Entity.Pedido;

public class EditarPedidoActivity extends AppCompatActivity {

    private EditText editTextProductos, editTextTotal, editTextWhatsApp;
    private Button btnGuardar;
    private Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pedido);

        editTextProductos = findViewById(R.id.editTextProductos);
        editTextTotal = findViewById(R.id.editTextTotal);
        editTextWhatsApp = findViewById(R.id.editTextWhatsApp);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Recuperar el pedido pasado desde la actividad anterior
        pedido = (Pedido) getIntent().getSerializableExtra("pedido");

        if (pedido != null) {
            // Mostrar los datos actuales del pedido
            editTextProductos.setText(pedido.getProductos());
            editTextTotal.setText(String.valueOf(pedido.getTotal()));
            editTextWhatsApp.setText(pedido.getWhatsappUltimos4());
        }

        btnGuardar.setOnClickListener(v -> {
            // Guardar los cambios y regresar a la actividad anterior
            pedido.setProductos(editTextProductos.getText().toString());
            pedido.setTotal(Double.parseDouble(editTextTotal.getText().toString()));
            pedido.setWhatsappUltimos4(editTextWhatsApp.getText().toString());

            Intent resultIntent = new Intent();
            resultIntent.putExtra("pedidoEditado", pedido);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
