package com.example.bulldogburger.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bulldogburger.Entity.Pedido;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PedidosDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PEDIDOS = "pedidos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCTOS = "productos";
    private static final String COLUMN_TOTAL = "total";
    private static final String COLUMN_WHATSAPP = "whatsappUltimos4";
    private static final String COLUMN_ESTADO = "estado";
    private static final String COLUMN_FECHA = "fecha";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_PEDIDOS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTOS + " TEXT, " +
                COLUMN_TOTAL + " REAL, " +
                COLUMN_WHATSAPP + " TEXT, " +
                COLUMN_ESTADO + " TEXT, " +
                COLUMN_FECHA + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEDIDOS);
        onCreate(db);
    }

    public boolean insertarPedido(String productos, double total, String whatsapp, String estado, String fecha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTOS, productos);
        values.put(COLUMN_TOTAL, total);
        values.put(COLUMN_WHATSAPP, whatsapp);
        values.put(COLUMN_ESTADO, estado);
        values.put(COLUMN_FECHA, fecha);

        long result = db.insert(TABLE_PEDIDOS, null, values);
        return result != -1;
    }

    public Cursor obtenerPedidos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PEDIDOS, null);
    }

    public boolean eliminarPedido(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int filasAfectadas = db.delete("pedidos", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return filasAfectadas > 0;
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        List<Pedido> listaPedidos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pedidos", null);

        if (cursor.moveToFirst()) {
            do {
                Pedido pedido = new Pedido(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                listaPedidos.add(pedido);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaPedidos;
    }

}
