package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entidades.GrupoAliens;
import Entidades.Item1;
import Entidades.Item2;
import Entidades.Item3;
import Entidades.Item4;
import Entidades.ProyectilAlien;
import Entidades.ProyectilNave;
import Entidades.Nave;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import static juego.Main.clip;
import recursos.Tiempo;
import recursos.Teclas;
import recursos.Constantes;

public class Escena extends JPanel {
	
// Variables	
        public Image fondo;
	public Nave vaisseau = new Nave();
	public GrupoAliens groupeAliens = new GrupoAliens();
	public ProyectilNave tirVaisseau = new ProyectilNave();
	public ProyectilAlien tirAlien1, tirAlien2, tirAlien3;
	public Item1 item1;
        public Item2 item2;
        public Item3 item3;
        public Item4 item4;
        private Image scoreImage;
    private Image timeImage;


	private Font afficheScore = new Font("Roboto", Font.PLAIN, 16);
	private Font afficheTexte = new Font("Roboto", Font.ITALIC|Font.BOLD, 100);
            private Font timerFont = new Font("Roboto", Font.PLAIN, 16);

	public int score = 0;
            public Timer timer;
    private int tiempoRestante = 90; // Tiempo en segundos
        private boolean gameOverDisplayed = false; // Nueva variable para controlar la visualización de Game Over


        

        

	
//Constructor
	
	public Escena() throws IOException {
            		super();

            try {
            scoreImage = ImageIO.read(new File("src/images/soucoupe.png"));
            timeImage = ImageIO.read(new File("src/images/dolar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
                fondo = ImageIO.read(new File("src/images/fondo.jpg"));
                // instancias de las teclas
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new Teclas());
                 
            // Iniciar el temporizador
            timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                if (tiempoRestante <= 0) {
                    tiempoRestante = 0;
                    timer.stop(); // Detener el temporizador cuando el tiempo llega a cero
                    
                    Main.jeu=false;
                }
                repaint(); // Redibujar el panel para actualizar el temporizador
            }
        });
        timer.start(); // Comenzar el temporizador
                 
                 this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                 if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                         JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Escena.this);
                        frame.dispose();
                        clip.stop();
                         Main.jeu = true;
                         Main.main(new String[]{});

        }
    }
        });		
		// instancias del tiempo
		Thread chronoEcran = new Thread(new Tiempo());
		chronoEcran.start();
	}
        
// Método para guardar el puntaje en un archivo
    private void guardarPuntaje(String nombre, int puntaje) {
        File puntajeArchivo = new File("src/Puntajes/puntajes.txt");
        try {
            if (!puntajeArchivo.exists()) {
                puntajeArchivo.getParentFile().mkdirs(); // Crear directorio si no existe
                puntajeArchivo.createNewFile(); // Crear archivo si no existe
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(puntajeArchivo, true))) {
                writer.write(nombre + ": " + puntaje);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error guardando el puntaje: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
		
// Metodos
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2 = (Graphics2D) g;
		// fondo de pantalla
                g2.drawImage(fondo, 0, 0, Constantes.LARGO_VENTANA, Constantes.ALTURA_VENTANA, this);
		// Establecer el color de fondo para el rectángulo
                g.setColor(Color.BLUE); // O el color que prefieras
                // Dibujar un único rectángulo grande detrás de ambos textos
                g.fillRect(0, 0, 1000, 40); // Ajusta las coordenadas y el tamaño según sea necesario
                g.drawImage(scoreImage, 20, 10, null); // Ajusta las coordenadas según sea necesario
                // Dibujar el primer texto
                g.setFont(afficheScore);
                g.setColor(Color.WHITE); // O el color que prefieras para el texto
                g.drawString("Score : " + score, 380, 25);
                g.drawImage(timeImage, 350, 10, null); // Ajusta las coordenadas según sea necesario
                // Dibujar el segundo texto
                g.setFont(timerFont);
                g.setColor(Color.WHITE); // O el color que prefieras para el texto
                g.drawString("Tiempo: " + tiempoRestante + " s", 50, 25);
		//diseño de nave
		this.vaisseau.dessinVaisseau(g2);
		// diseño aliens
		this.groupeAliens.dessinAliens(g2);
		// diseño proyectil nave
		this.tirVaisseau.dessinTirVaisseau(g2);
		this.groupeAliens.tirVaisseauToucheAlien(this.tirVaisseau);
		// Inicio de juego
		if(tiempoRestante ==90) {
		    g.setFont(afficheTexte);
                    g.setColor(Color.YELLOW); // O el color que prefieras para el texto
		    g.drawString("LET´S GO!!!", 200, 300);
		}
		 if (!this.vaisseau.isVivo() || tiempoRestante <= 0) {
            g.setFont(afficheTexte);
            g.setColor(Color.YELLOW);
            g.drawString("GAME OVER", 200, 300);
            // Mostrar diálogo solo una vez
            if (!gameOverDisplayed) {
                gameOverDisplayed = true;
                String nombre = JOptionPane.showInputDialog(this, "Ingrese su nombre:", "Game Over", JOptionPane.PLAIN_MESSAGE);
                if (nombre != null && !nombre.trim().isEmpty()) {
                    guardarPuntaje(nombre.trim(), score);
                }
            }
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Escena.this);
                        frame.dispose();
                         clip.stop();
                         Main.jeu = true;
                         Main.main(new String[]{});
        }
		// diseño prooyectil aliens
		if(Tiempo.compteTours % 500 == 0) {
			tirAlien1 = new ProyectilAlien(this.groupeAliens.choixAlienQuiTire());
                }
		if(this.tirAlien1 != null) {
			this.tirAlien1.dessinTirAlien(g2);
			if(this.tirAlien1.toucheVaisseau(vaisseau) == true) 
                        {
                            this.vaisseau.setVivo(false);
                                    timer.stop(); // Comenzar el temporizador

                        }
		}
		if(Tiempo.compteTours % 750 == 0) {
                    
			tirAlien2 = new ProyectilAlien(this.groupeAliens.choixAlienQuiTire());
                }
		if(this.tirAlien2 != null) 
                {
			this.tirAlien2.dessinTirAlien(g2);
			if(this.tirAlien2.toucheVaisseau(vaisseau) == true) 
                        {
                            this.vaisseau.setVivo(false);
                            timer.stop(); // Comenzar el temporizador

                        }
		}
		if(Tiempo.compteTours % 900 == 0) {
			tirAlien3 = new ProyectilAlien(this.groupeAliens.choixAlienQuiTire());
                }
		if(this.tirAlien3 != null) {
			this.tirAlien3.dessinTirAlien(g2);
			if(this.tirAlien3.toucheVaisseau(vaisseau) == true) 
                        {this.vaisseau.setVivo(false);
                        timer.stop(); 
                        }
		}
		// Item		
		if(Tiempo.compteTours % 2150 == 0) {item1 = new Item1();
                }		
		if(this.item1 != null) {
			if(this.item1.getxPos()>0) {	
				// Detección del tiro con el item
				if(this.tirVaisseau.detruitSoucoupe(this.item1) == true) {
					if(this.item1.getDx() != 0) {this.tiempoRestante = this.tiempoRestante + Constantes.VALOR_TIEMPO;}
					this.item1.setDx(0);
				this.item1.setVivo(false);
					this.item1.musiqueSoucoupe.stop();
					this.item1.musiqueDestructionSoucoupe.play();
				}
				this.item1.dessinSoucoupe(g2);
			}else {this.item1 = null;
		}
                if(Tiempo.compteTours % 2100 == 0) {item2 = new Item2();}		
		if(this.item2 != null) {
			if(this.item2.getxPos()>0) {	
				// Detección del tiro con el item
				if(this.tirVaisseau.detruitSoucoupe1(this.item2) == true) {
					if(this.item2.getDx() != 0) {this.score = this.score + Constantes.VALOR_EXTRA;}
					this.item2.setDx(0);
				this.item2.setVivo(false);
					this.item2.musiqueSoucoupe.stop();
					this.item2.musiqueDestructionSoucoupe.play();
				}
				this.item2.dessinSoucoupe1(g2);
			}else {this.item2 = null;}
		}
                if(Tiempo.compteTours % 2050 == 0) {item3 = new Item3();}		
		if(this.item3 != null) {
			if(this.item3.getxPos()>0) {	
				// Detección del tiro con el item
				if(this.tirVaisseau.detruitSoucoupe2(this.item3) == true) {
					if(this.item3.getDx() != 0) {this.tiempoRestante = this.tiempoRestante + Constantes.VALOR_DTIEMPO;}
					this.item3.setDx(0);
				this.item3.setVivo(false);
					this.item3.musiqueSoucoupe.stop();
					this.item3.musiqueDestructionSoucoupe.play();
				}
				this.item3.dessinSoucoupe2(g2);
			}else {this.item3 = null;}
		}
                 if(Tiempo.compteTours % 2000 == 0) {item4 = new Item4();}		
		if(this.item4 != null) {
			if(this.item4.getxPos()>0) {	
				// Detección del tiro con el item
				if(this.tirVaisseau.detruitSoucoupe3(this.item4) == true) {
					if(this.item4.getDx() != 0) {this.score = this.score + Constantes.VALOR_PENALIZACIÓN;}
					this.item4.setDx(0);
				this.item4.setVivo(false);
					this.item4.musiqueSoucoupe.stop();
					this.item4.musiqueDestructionSoucoupe.play();
				}
				this.item4.dessinSoucoupe3(g2);
			}else {this.item4 = null;}
		}
                
               
                
                
		
		if(this.groupeAliens.getNombreAliens() == 0) {groupeAliens = new GrupoAliens();}
	
		if(this.groupeAliens.positionAlienLePlusGauche() > Constantes.Y_POS_INI_NAVE) {this.vaisseau.destructionVaisseau();}			
	}	
}
      
        
        
        }
