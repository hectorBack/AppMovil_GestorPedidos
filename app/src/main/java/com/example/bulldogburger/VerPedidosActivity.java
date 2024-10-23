package com.example.bulldogburger;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bulldogburger.Entity.Pedido;
import com.example.bulldogburger.SQLite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class VerPedidosActivity extends AppCompatActivity {

    private PedidoAdapter pedidoAdapter;
    private DatabaseHelper dbHelper;
    private RecyclerView recyclerViewPedidos;
    private List<Pedido> listaPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos);

        dbHelper = new DatabaseHelper(this);
        recyclerViewPedidos = findViewById(R.id.recyclerViewPedidos);

        // Obtén la lista de pedidos de la base de datos
        List<Pedido> listaPedidos = dbHelper.obtenerTodosLosPedidos();

        // Configura el RecyclerView
        pedidoAdapter = new PedidoAdapter(this, listaPedidos, dbHelper);
        recyclerViewPedidos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPedidos.setAdapter(pedidoAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Pedido pedidoEditado = (Pedido) data.getSerializableExtra("pedidoEditado");

            // Actualizar la lista de pedidos
            for (int i = 0; i < listaPedidos.size(); i++) {
                if (listaPedidos.get(i).getId() == pedidoEditado.getId()) {
                    listaPedidos.set(i, pedidoEditado);
                    pedidoAdapter.notifyItemChanged(i);
                    break;
                }
            }
        }
    }
}
