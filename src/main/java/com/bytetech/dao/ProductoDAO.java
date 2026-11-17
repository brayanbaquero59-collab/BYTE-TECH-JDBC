package com.bytetech.dao;

import com.bytetech.configuracion.ConexionBD;
import com.bytetech.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Ejecuta las operaciones CRUD de productos usando consultas parametrizadas. */
public class ProductoDAO {

    public List<Producto> listar(String filtro) throws SQLException {
        String sql = "SELECT * FROM productos WHERE nombre LIKE ? OR categoria LIKE ? OR marca LIKE ? ORDER BY id DESC";
        List<Producto> productos = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            String criterio = "%" + (filtro == null ? "" : filtro.trim()) + "%";
            sentencia.setString(1, criterio);
            sentencia.setString(2, criterio);
            sentencia.setString(3, criterio);
            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    productos.add(mapearProducto(resultado));
                }
            }
        }
        return productos;
    }

    public void insertar(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (nombre, categoria, marca, precio, stock, descripcion) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            asignarParametros(sentencia, producto);
            sentencia.executeUpdate();
        }
    }

    public boolean actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE productos SET nombre=?, categoria=?, marca=?, precio=?, stock=?, descripcion=? WHERE id=?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            asignarParametros(sentencia, producto);
            sentencia.setInt(7, producto.getId());
            return sentencia.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id=?";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            return sentencia.executeUpdate() > 0;
        }
    }

    public int contarProductos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM productos";
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {
            resultado.next();
            return resultado.getInt(1);
        }
    }

    private void asignarParametros(PreparedStatement sentencia, Producto producto) throws SQLException {
        sentencia.setString(1, producto.getNombre());
        sentencia.setString(2, producto.getCategoria());
        sentencia.setString(3, producto.getMarca());
        sentencia.setBigDecimal(4, producto.getPrecio());
        sentencia.setInt(5, producto.getStock());
        sentencia.setString(6, producto.getDescripcion());
    }

    private Producto mapearProducto(ResultSet resultado) throws SQLException {
        return new Producto(
                resultado.getInt("id"),
                resultado.getString("nombre"),
                resultado.getString("categoria"),
                resultado.getString("marca"),
                resultado.getBigDecimal("precio"),
                resultado.getInt("stock"),
                resultado.getString("descripcion")
        );
    }
}
