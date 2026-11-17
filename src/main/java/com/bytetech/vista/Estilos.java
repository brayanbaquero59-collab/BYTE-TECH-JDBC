package com.bytetech.vista;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public final class Estilos {
    public static final Color FONDO = new Color(12, 20, 38);
    public static final Color PANEL = new Color(21, 34, 58);
    public static final Color PANEL_CLARO = new Color(29, 46, 75);
    public static final Color AZUL = new Color(34, 160, 220);
    public static final Color CIAN = new Color(62, 220, 230);
    public static final Color TEXTO = new Color(238, 245, 255);
    public static final Color SUAVE = new Color(157, 177, 205);
    public static final Color ROJO = new Color(220, 83, 102);
    public static final Color VERDE = new Color(47, 190, 145);

    private Estilos() {}

    public static void configurarVentana(JFrame ventana) {
        ventana.getContentPane().setBackground(FONDO);
        ventana.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    }

    public static JButton boton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);
        boton.setFocusPainted(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setBorder(new EmptyBorder(11, 17, 11, 17));
        return boton;
    }

    public static JTextField campo() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setForeground(TEXTO);
        campo.setCaretColor(CIAN);
        campo.setBackground(PANEL_CLARO);
        campo.setBorder(new CompoundBorder(new LineBorder(new Color(55, 79, 115), 1, true),
                new EmptyBorder(9, 11, 9, 11)));
        return campo;
    }

    public static JLabel etiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(SUAVE);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return etiqueta;
    }
}
