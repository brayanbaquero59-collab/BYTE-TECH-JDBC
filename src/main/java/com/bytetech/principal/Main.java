package com.bytetech.principal;

import com.bytetech.vista.VentanaPrincipal;

import javax.swing.*;

/** Punto de entrada de BYTE TECH. */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Se conserva el estilo predeterminado si el sistema no permite cambiarlo.
            }
            new VentanaPrincipal().setVisible(true);
        });
    }
}
