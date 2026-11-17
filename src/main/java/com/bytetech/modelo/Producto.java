package com.bytetech.modelo;

import java.math.BigDecimal;

/** Representa un producto comercializado por BYTE TECH. */
public class Producto {
    private int id;
    private String nombre;
    private String categoria;
    private String marca;
    private BigDecimal precio;
    private int stock;
    private String descripcion;

    public Producto() {
    }

    public Producto(int id, String nombre, String categoria, String marca,
                    BigDecimal precio, int stock, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
