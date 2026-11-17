package com.bytetech.vista;

import com.bytetech.dao.ProductoDAO;
import com.bytetech.modelo.Producto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/** Interfaz gráfica de escritorio para administrar el inventario BYTE TECH. */
public class VentanaPrincipal extends JFrame {
    private final ProductoDAO productoDAO = new ProductoDAO();
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;
    private final JTextField txtBuscar = Estilos.campo();
    private final JTextField txtNombre = Estilos.campo();
    private final JTextField txtCategoria = Estilos.campo();
    private final JTextField txtMarca = Estilos.campo();
    private final JTextField txtPrecio = Estilos.campo();
    private final JTextField txtStock = Estilos.campo();
    private final JTextField txtDescripcion = Estilos.campo();
    private final JLabel lblEstado = new JLabel("Sistema listo");
    private final JLabel lblTotal = new JLabel("0 productos");
    private int idSeleccionado = 0;

    public VentanaPrincipal() {
        setTitle("BYTE TECH | Gestión de inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1180, 760);
        setMinimumSize(new Dimension(1000, 680));
        setLocationRelativeTo(null);
        Estilos.configurarVentana(this);

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "PRODUCTO", "CATEGORÍA", "MARCA", "PRECIO", "STOCK", "DESCRIPCIÓN"}, 0) {
            @Override public boolean isCellEditable(int fila, int columna) { return false; }
        };
        tabla = new JTable(modeloTabla);
        construirInterfaz();
        cargarProductos("");
    }

    private void construirInterfaz() {
        JPanel raiz = new JPanel(new BorderLayout(0, 18));
        raiz.setBackground(Estilos.FONDO);
        raiz.setBorder(new EmptyBorder(22, 26, 18, 26));
        raiz.add(crearEncabezado(), BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(18, 0));
        centro.setOpaque(false);
        centro.add(crearFormulario(), BorderLayout.WEST);
        centro.add(crearInventario(), BorderLayout.CENTER);
        raiz.add(centro, BorderLayout.CENTER);

        JPanel pie = new JPanel(new BorderLayout());
        pie.setOpaque(false);
        lblEstado.setForeground(Estilos.SUAVE);
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblTotal.setForeground(Estilos.CIAN);
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 12));
        pie.add(lblEstado, BorderLayout.WEST);
        pie.add(lblTotal, BorderLayout.EAST);
        raiz.add(pie, BorderLayout.SOUTH);
        setContentPane(raiz);
    }

    private JPanel crearEncabezado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        JPanel marca = new JPanel(new GridLayout(2, 1, 0, 3));
        marca.setOpaque(false);
        JLabel titulo = new JLabel("BYTE TECH");
        titulo.setForeground(Estilos.TEXTO);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 29));
        JLabel subtitulo = new JLabel("TECNOLOGÍA  /  INVENTARIO INTELIGENTE");
        subtitulo.setForeground(Estilos.CIAN);
        subtitulo.setFont(new Font("Segoe UI", Font.BOLD, 10));
        marca.add(titulo);
        marca.add(subtitulo);
        JLabel etiqueta = new JLabel("PANEL DE ADMINISTRACIÓN     ●  JDBC + MYSQL");
        etiqueta.setForeground(Estilos.SUAVE);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 11));
        panel.add(marca, BorderLayout.WEST);
        panel.add(etiqueta, BorderLayout.EAST);
        return panel;
    }

    private JPanel crearFormulario() {
        JPanel panel = tarjeta();
        panel.setPreferredSize(new Dimension(300, 0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("GESTIÓN DE PRODUCTO");
        titulo.setForeground(Estilos.TEXTO);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        panel.add(titulo);
        panel.add(espacio(5));
        JLabel ayuda = new JLabel("Completa los datos del inventario");
        ayuda.setForeground(Estilos.SUAVE);
        ayuda.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        panel.add(ayuda);
        panel.add(espacio(18));

        agregarCampo(panel, "NOMBRE DEL PRODUCTO", txtNombre);
        agregarCampo(panel, "CATEGORÍA", txtCategoria);
        agregarCampo(panel, "MARCA", txtMarca);
        agregarCampo(panel, "PRECIO (COP)", txtPrecio);
        agregarCampo(panel, "CANTIDAD EN STOCK", txtStock);
        agregarCampo(panel, "DESCRIPCIÓN", txtDescripcion);

        panel.add(espacio(15));
        JButton guardar = Estilos.boton("＋  REGISTRAR PRODUCTO", Estilos.AZUL);
        guardar.setAlignmentX(Component.LEFT_ALIGNMENT);
        guardar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        guardar.addActionListener(e -> insertarProducto());
        panel.add(guardar);
        panel.add(espacio(9));

        JPanel fila = new JPanel(new GridLayout(1, 2, 8, 0));
        fila.setOpaque(false);
        JButton actualizar = Estilos.boton("ACTUALIZAR", Estilos.VERDE);
        JButton limpiar = Estilos.boton("LIMPIAR", Estilos.PANEL_CLARO);
        actualizar.addActionListener(e -> actualizarProducto());
        limpiar.addActionListener(e -> limpiarFormulario());
        fila.add(actualizar);
        fila.add(limpiar);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        fila.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(fila);
        panel.add(espacio(9));

        JButton eliminar = Estilos.boton("ELIMINAR SELECCIONADO", Estilos.ROJO);
        eliminar.setAlignmentX(Component.LEFT_ALIGNMENT);
        eliminar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        eliminar.addActionListener(e -> eliminarProducto());
        panel.add(eliminar);
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel crearInventario() {
        JPanel panel = tarjeta();
        panel.setLayout(new BorderLayout(0, 15));

        JPanel superior = new JPanel(new BorderLayout(12, 0));
        superior.setOpaque(false);
        JPanel textos = new JPanel(new GridLayout(2, 1, 0, 4));
        textos.setOpaque(false);
        JLabel titulo = new JLabel("Inventario de productos");
        titulo.setForeground(Estilos.TEXTO);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 19));
        JLabel desc = new JLabel("Consulta, busca y administra los equipos de BYTE TECH");
        desc.setForeground(Estilos.SUAVE);
        desc.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        textos.add(titulo);
        textos.add(desc);
        superior.add(textos, BorderLayout.WEST);

        JPanel busqueda = new JPanel(new BorderLayout(6, 0));
        busqueda.setOpaque(false);
        txtBuscar.setPreferredSize(new Dimension(185, 38));
        txtBuscar.setToolTipText("Buscar por nombre, categoría o marca");
        JButton buscar = Estilos.boton("BUSCAR", Estilos.PANEL_CLARO);
        buscar.addActionListener(e -> cargarProductos(txtBuscar.getText()));
        txtBuscar.addActionListener(e -> cargarProductos(txtBuscar.getText()));
        busqueda.add(txtBuscar, BorderLayout.CENTER);
        busqueda.add(buscar, BorderLayout.EAST);
        superior.add(busqueda, BorderLayout.EAST);
        panel.add(superior, BorderLayout.NORTH);

        configurarTabla();
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        scroll.getViewport().setBackground(Estilos.PANEL);
        panel.add(scroll, BorderLayout.CENTER);

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() >= 0) {
                cargarSeleccion();
            }
        });
        return panel;
    }

    private void configurarTabla() {
        tabla.setRowHeight(38);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabla.setForeground(Estilos.TEXTO);
        tabla.setBackground(Estilos.PANEL);
        tabla.setSelectionBackground(new Color(36, 92, 133));
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setGridColor(new Color(42, 61, 91));
        tabla.setShowVerticalLines(false);
        tabla.setFillsViewportHeight(true);
        tabla.getTableHeader().setBackground(Estilos.PANEL_CLARO);
        tabla.getTableHeader().setForeground(Estilos.CIAN);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 10));
        tabla.getTableHeader().setPreferredSize(new Dimension(0, 38));
        tabla.getColumnModel().getColumn(0).setPreferredWidth(38);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(145);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(170);
    }

    private JPanel tarjeta() {
        JPanel panel = new JPanel();
        panel.setBackground(Estilos.PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(39, 60, 91), 1, true),
                new EmptyBorder(19, 18, 19, 18)));
        return panel;
    }

    private void agregarCampo(JPanel panel, String nombre, JTextField campo) {
        JLabel etiqueta = Estilos.etiqueta(nombre);
        etiqueta.setAlignmentX(Component.LEFT_ALIGNMENT);
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(etiqueta);
        panel.add(espacio(5));
        panel.add(campo);
        panel.add(espacio(11));
    }

    private Component espacio(int alto) {
        return Box.createRigidArea(new Dimension(1, alto));
    }

    private void insertarProducto() {
        try {
            Producto producto = leerFormulario();
            productoDAO.insertar(producto);
            mostrarMensaje("Producto registrado correctamente.", false);
            limpiarFormulario();
            cargarProductos(txtBuscar.getText());
        } catch (IllegalArgumentException ex) {
            mostrarMensaje(ex.getMessage(), true);
        } catch (SQLException ex) {
            mostrarErrorBD(ex);
        }
    }

    private void actualizarProducto() {
        if (idSeleccionado == 0) {
            mostrarMensaje("Selecciona un producto de la tabla para actualizarlo.", true);
            return;
        }
        try {
            Producto producto = leerFormulario();
            producto.setId(idSeleccionado);
            if (productoDAO.actualizar(producto)) {
                mostrarMensaje("Producto actualizado correctamente.", false);
                limpiarFormulario();
                cargarProductos(txtBuscar.getText());
            } else {
                mostrarMensaje("No se encontró el producto seleccionado.", true);
            }
        } catch (IllegalArgumentException ex) {
            mostrarMensaje(ex.getMessage(), true);
        } catch (SQLException ex) {
            mostrarErrorBD(ex);
        }
    }

    private void eliminarProducto() {
        if (idSeleccionado == 0) {
            mostrarMensaje("Selecciona un producto de la tabla para eliminarlo.", true);
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas eliminar el producto seleccionado?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                productoDAO.eliminar(idSeleccionado);
                mostrarMensaje("Producto eliminado correctamente.", false);
                limpiarFormulario();
                cargarProductos(txtBuscar.getText());
            } catch (SQLException ex) {
                mostrarErrorBD(ex);
            }
        }
    }

    private Producto leerFormulario() {
        String nombre = txtNombre.getText().trim();
        String categoria = txtCategoria.getText().trim();
        String marca = txtMarca.getText().trim();
        String precioTexto = txtPrecio.getText().trim().replace(",", ".");
        String stockTexto = txtStock.getText().trim();
        String descripcion = txtDescripcion.getText().trim();

        if (nombre.isEmpty() || categoria.isEmpty() || marca.isEmpty()
                || precioTexto.isEmpty() || stockTexto.isEmpty()) {
            throw new IllegalArgumentException("Completa nombre, categoría, marca, precio y stock.");
        }
        try {
            BigDecimal precio = new BigDecimal(precioTexto);
            int stock = Integer.parseInt(stockTexto);
            if (precio.signum() < 0 || stock < 0) {
                throw new IllegalArgumentException("El precio y el stock no pueden ser negativos.");
            }
            return new Producto(0, nombre, categoria, marca, precio, stock, descripcion);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Verifica el precio y el stock. Usa números válidos.");
        }
    }

    private void cargarProductos(String filtro) {
        try {
            List<Producto> productos = productoDAO.listar(filtro);
            modeloTabla.setRowCount(0);
            NumberFormat moneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
            for (Producto p : productos) {
                modeloTabla.addRow(new Object[]{p.getId(), p.getNombre(), p.getCategoria(),
                        p.getMarca(), moneda.format(p.getPrecio()), p.getStock(), p.getDescripcion()});
            }
            lblTotal.setText(productos.size() + " producto(s) mostrado(s)");
            mostrarMensaje("Inventario actualizado.", false);
        } catch (SQLException ex) {
            modeloTabla.setRowCount(0);
            mostrarErrorBD(ex);
        }
    }

    private void cargarSeleccion() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) return;
        idSeleccionado = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
        txtNombre.setText(tabla.getValueAt(fila, 1).toString());
        txtCategoria.setText(tabla.getValueAt(fila, 2).toString());
        txtMarca.setText(tabla.getValueAt(fila, 3).toString());
        String precio = tabla.getValueAt(fila, 4).toString().replaceAll("[^0-9,.-]", "").replace(",", ".");
        txtPrecio.setText(precio);
        txtStock.setText(tabla.getValueAt(fila, 5).toString());
        Object descripcion = tabla.getValueAt(fila, 6);
        txtDescripcion.setText(descripcion == null ? "" : descripcion.toString());
        lblEstado.setText("Producto seleccionado: #" + idSeleccionado);
    }

    private void limpiarFormulario() {
        idSeleccionado = 0;
        txtNombre.setText("");
        txtCategoria.setText("");
        txtMarca.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtDescripcion.setText("");
        tabla.clearSelection();
    }

    private void mostrarMensaje(String mensaje, boolean error) {
        lblEstado.setForeground(error ? new Color(255, 130, 145) : Estilos.SUAVE);
        lblEstado.setText(mensaje);
    }

    private void mostrarErrorBD(SQLException ex) {
        mostrarMensaje("No se pudo acceder a MySQL. Revisa el servidor y las credenciales.", true);
        JOptionPane.showMessageDialog(this,
                "No fue posible completar la operación.\n\nDetalle: " + ex.getMessage()
                        + "\n\nVerifica que MySQL esté iniciado, que exista byte_tech y que la contraseña esté configurada en ConexionBD.",
                "Error de conexión o base de datos", JOptionPane.ERROR_MESSAGE);
    }
}
