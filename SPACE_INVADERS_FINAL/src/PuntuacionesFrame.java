
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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
        puntuacionesList.setFont(new Font("Arial", Font.BOLD, 25)); // Establecer una fuente y tamaño más grande
        puntuacionesList.setForeground(Color.YELLOW); // Establecer el color de la fuente a amarillo

        // Establecer un ListCellRenderer personalizado para centrar el texto
        puntuacionesList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto horizontalmente
                return label;
            }
        });

        cargarPuntuaciones();

        // Crear un JScrollPane con la lista de puntuaciones
        JScrollPane scrollPane = new JScrollPane(puntuacionesList);

        // Crear un JLayeredPane para manejar la imagen de fondo y el contenido
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new OverlayLayout(layeredPane));

        // Crear un JLabel para la imagen de fondo
          JLabel backgroundLabel = new JLabel();
    // Cargar la imagen desde el classpath
    ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/podio.jpg"));
    backgroundLabel.setIcon(imagenFondo);
    // Ajustar el tamaño del JLabel del fondo al tamaño del JFrame
    backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // Agregar el fondo y el contenido al layeredPane
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(scrollPane, JLayeredPane.PALETTE_LAYER);

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        puntuacionesList.setOpaque(false);
        puntuacionesList.setBackground(new Color(0, 0, 0, 0));

        getContentPane().add(layeredPane);
    }

  private void cargarPuntuaciones() {
    List<Puntuacion> puntuaciones = new ArrayList<>();

    // Obtener el recurso del classpath
    URL url = getClass().getResource("/puntajes.txt");
    
    if (url == null) {
        JOptionPane.showMessageDialog(this, "No se encontró el archivo puntajes.txt en el classpath", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            // Separar nombre y puntuación
            String[] partes = linea.split(":"); // Suponiendo que el archivo tiene el formato "nombre: puntuacion"
            if (partes.length == 2) {
                String nombre = partes[0].trim();
                int puntuacion = Integer.parseInt(partes[1].trim());
                puntuaciones.add(new Puntuacion(nombre, puntuacion));
            }
        }

        // Ordenar las puntuaciones de mayor a menor
        Collections.sort(puntuaciones, Comparator.comparingInt(Puntuacion::getPuntuacion).reversed());

        // Construir el modelo de lista con las puntuaciones
        listModel.clear();
        listModel.addElement("Posiciones:"); // Añadir el título al inicio
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
