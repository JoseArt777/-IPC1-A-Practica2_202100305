
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
	
    // VAriables
    public static Escena scene;
    public static boolean jeu = true;
    public static Clip clip; // Variable para el clip de música
    



    // metodos
   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Creación de la ventana de la aplicación
            JFrame fenetre = new JFrame("Space Invaders");
            fenetre.setSize(1000, 600);
            fenetre.setResizable(false);
            fenetre.setLocationRelativeTo(null);
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            try {
                InputStream audioSrc = Main.class.getResourceAsStream("/Intro.wav");
                if (audioSrc == null) {
                    throw new IllegalArgumentException("El archivo de audio no se encontró");
                }
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception ex) {
                System.out.println("Error al cargar el archivo de audio: " + ex.getMessage());
            }
            // Crear una etiqueta para la imagen de fondo
            JLabel background = new JLabel();
            background.setLayout(new BorderLayout());

            // Cargar la imagen de fondo desde un archivo o URL
            ImageIcon backgroundImage = new ImageIcon(Main.class.getResource("/fondo_principal.jpg"));
        background.setIcon(backgroundImage);

            // Panel para los botones con GridBagLayout para centrar los botones
            JPanel buttonPanel = new JPanel(new GridBagLayout());
            buttonPanel.setOpaque(false); // Hacer el panel transparente
            buttonPanel.setSize(100,100);
            // Estilo de fuente para los botones
            Font buttonFont = new Font("Futura", Font.BOLD, 22);
            // Cargar la imagen del JLabel
            ImageIcon labelImage = new ImageIcon(Main.class.getResource("/Icono.png"));
           JLabel imageLabel = new JLabel(labelImage);
            JButton startButton = new JButton("Nuevo Juego");
            startButton.setFont(buttonFont);
            startButton.setFocusPainted(false); // Quita el efecto de resaltado al obtener el foco
            startButton.setBackground(Color.BLUE); // Fondo negro
            startButton.setForeground(Color.YELLOW); // Texto verde neón
            startButton.setSize(50,20);
            startButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
if (clip != null && clip.isRunning()) {
                        clip.stop();
                        clip.close();
                    }
        try {
            demarrerJeu();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                                    fenetre.dispose();

    }
});
            // Botón "Cargar Juego"
            JButton cargarButton = new JButton("Cargar Juego");
            cargarButton.setFont(buttonFont);
            cargarButton.setFocusPainted(false);
            cargarButton.setBackground(Color.BLUE); // Fondo negro
            cargarButton.setForeground(Color.YELLOW); // Texto verde neón
            cargarButton.setSize(40,20);

            // Botón "Puntuación Máxima"
            JButton puntuacionButton = new JButton("Posiciones");
            puntuacionButton.setFont(buttonFont);
            puntuacionButton.setFocusPainted(false);
            puntuacionButton.setBackground(Color.BLUE); // Fondo negro
            puntuacionButton.setForeground(Color.YELLOW); // Texto verde neón
            puntuacionButton.setSize(40,20);
            
            puntuacionButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Crear y mostrar el frame de puntuaciones
        JFrame puntuacionesFrame = new PuntuacionesFrame();
        puntuacionesFrame.setVisible(true);
     
    }
});
        
            // Establecer el borde

            // Botón "Salir"
            JButton salirButton = new JButton("Salir");
            salirButton.setFont(buttonFont);
            salirButton.setFocusPainted(false);
            salirButton.setBackground(Color.RED); // Fondo negro
            salirButton.setForeground(Color.YELLOW); // Texto verde neón
            salirButton.setSize(40,20);
            
            salirButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Cierra el programa
        System.exit(0);
    }
});

            // Configuración de GridBagConstraints para centrar los botones
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = GridBagConstraints.RELATIVE;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(5, 0, 5, 0); // Márgenes

            // Agregar el JLabel con la imagen al panel
            buttonPanel.add(imageLabel, gbc);

            // Agregar los botones al panel con GridBagLayout
            buttonPanel.add(startButton, gbc);
            buttonPanel.add(cargarButton, gbc);
            buttonPanel.add(puntuacionButton, gbc);
            buttonPanel.add(salirButton, gbc);

            // Agregar el panel de botones al fondo
            background.add(buttonPanel, BorderLayout.CENTER);

            // Agregar el fondo al contenido del JFrame
            fenetre.setContentPane(background);

            fenetre.setVisible(true);
        });
    }


    public static void demarrerJeu() throws IOException {
        
        // ventana del juegos
        
        JFrame jeuFrame = new JFrame("Space Invaders");
        jeuFrame.setSize(Constantes.LARGO_VENTANA, Constantes.ALTURA_VENTANA);
        jeuFrame.setResizable(false);
        jeuFrame.setLocationRelativeTo(null);
        jeuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jeuFrame.setAlwaysOnTop(true);
        scene = new Escena();
        Main.jeu=true;
        jeuFrame.setContentPane(scene);
        jeuFrame.setVisible(true);
        
        
    }
     
    
}