/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PuntuacionesFrame extends JFrame {

    private DefaultListModel<String> listModel;
    private JList<String> puntuacionesList;

    public PuntuacionesFrame() {
        super("Mejores Puntuaciones");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        puntuacionesList = new JList<>(listModel);
        puntuacionesList.setFont(new Font("Arial", Font.PLAIN, 16)); // Establecer una fuente y tamaño más grande

        cargarPuntuaciones();

        getContentPane().add(new JScrollPane(puntuacionesList), BorderLayout.CENTER);
    }

    private void cargarPuntuaciones() {
        List<Puntuacion> puntuaciones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/Puntajes/puntajes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Separar nombre y puntuación
                String[] partes = linea.split("\\s+"); // Separar por espacios en blanco, ajustar según el delimitador
                if (partes.length >= 2) {
                    String nombre = partes[0];
                    int puntuacion = Integer.parseInt(partes[1]);
                    puntuaciones.add(new Puntuacion(nombre, puntuacion));
                }
            }

            // Ordenar las puntuaciones de mayor a menor
            Collections.sort(puntuaciones, Comparator.comparingInt(Puntuacion::getPuntuacion).reversed());

            // Construir el modelo de lista con las puntuaciones
            listModel.clear();
            for (int i = 0; i < Math.min(puntuaciones.size(), 5); i++) {
                Puntuacion p = puntuaciones.get(i);
                String item = (i + 1) + ". " + p.getNombre() + " - " + p.getPuntuacion();
                listModel.addElement(item);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las puntuaciones.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Clase interna para representar una puntuación
    private static class Puntuacion {
        private String nombre;
        private int puntuacion;

        public Puntuacion(String nombre, int puntuacion) {
            this.nombre = nombre;
            this.puntuacion = puntuacion;
        }

        public String getNombre() {
            return nombre;
        }

        public int getPuntuacion() {
            return puntuacion;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PuntuacionesFrame frame = new PuntuacionesFrame();
            frame.setVisible(true);
        });
    }

}
