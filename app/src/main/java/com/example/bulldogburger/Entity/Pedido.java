package com.example.bulldogburger.Entity;

import java.io.Serializable;

public class Pedido implements Serializable {

    private int id;
    private String productos;
    private double total;
    private String whatsappUltimos4;
    private String estado;
    private String fecha;

    public Pedido(int id, String productos, double total, String whatsappUltimos4, String estado, String fecha) {
        this.id = id;
        this.productos = productos;
        this.total = total;
        this.whatsappUltimos4 = whatsappUltimos4;
        this.estado = estado;
        this.fecha = fecha;
    }

    // Constructor
    public Pedido(int id, String productos, double total, String whatsappUltimos4, String estado) {
        this.id = id;
        this.productos = productos;
        this.total = total;
        this.whatsappUltimos4 = whatsappUltimos4;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getWhatsappUltimos4() {
        return whatsappUltimos4;
    }

    public void setWhatsappUltimos4(String whatsappUltimos4) {
        this.whatsappUltimos4 = whatsappUltimos4;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Pedido() {
    }
}
