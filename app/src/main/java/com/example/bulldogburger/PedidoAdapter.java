package com.example.bulldogburger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bulldogburger.Entity.Pedido;
import com.example.bulldogburger.SQLite.DatabaseHelper;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    private Context context;
    private List<Pedido> listaPedidos;
    private DatabaseHelper dbHelper;

    public PedidoAdapter(Context context, List<Pedido> listaPedidos, DatabaseHelper dbHelper) {
        this.context = context;
        this.listaPedidos = listaPedidos;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pedido, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = listaPedidos.get(position);
        holder.textViewProductos.setText("Productos: " + pedido.getProductos());
        holder.textViewTotal.setText("Total: $" + pedido.getTotal());
        holder.textViewWhatsapp.setText("WhatsApp: " + pedido.getWhatsappUltimos4());

        // Acción para eliminar pedido
        holder.btnEliminar.setOnClickListener(v -> {
            boolean eliminado = dbHelper.eliminarPedido(pedido.getId());
            if (eliminado) {
                listaPedidos.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, "Pedido eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Error al eliminar pedido", Toast.LENGTH_SHORT).show();
            }
        });

        // Acción para editar pedido (aquí puedes abrir una nueva actividad para editar)
        holder.btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditarPedidoActivity.class);
            intent.putExtra("pedido", pedido);  // Enviar el pedido a editar
            ((Activity) context).startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProductos, textViewTotal, textViewWhatsapp;
        Button btnEditar, btnEliminar, btnEstadoPedido;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProductos = itemView.findViewById(R.id.textViewProductos);
            textViewTotal = itemView.findViewById(R.id.textViewTotal);
            textViewWhatsapp = itemView.findViewById(R.id.textViewWhatsapp);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}

