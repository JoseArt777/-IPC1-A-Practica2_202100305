package Entidades;


import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;
import recursos.Audio;
import recursos.Tiempo;
import recursos.Constantes;

public class Item2 extends Entidad {

    //Variables

    public Audio musiqueSoucoupe = new Audio("/sons/sonSoucoupePasse.wav");
    public Audio musiqueDestructionSoucoupe = new Audio("/sons/sonDestructionSoucoupe.wav");

    private int compteur1 = 0;
    
    // Constructor
    
    public Item2() {        
        super.xPos = Constantes.X_POS_INI_EXTRA;
        super.yPos = getRandomYPosition1();
        super.largeur = Constantes.LARGO_EXTRA;
        super.altura = Constantes.ALTURA_EXTRA;
        super.dx = Constantes.DX_TIEMPO;
        super.dy = 0;
        this.strImg1 = "/images/dolar.png";
        this.strImg2 = "/images/simbolo-de-dolar.png";
        this.strImg3 = "";

        super.ico = new ImageIcon(getClass().getResource(strImg1));
        super.img = this.ico.getImage();
        super.vivo = true;

        this.musiqueSoucoupe.play();
        this.musiqueDestructionSoucoupe.stop();
        this.compteur1 = 0;
    }
    
    // Mëtodos
    
    private int getRandomYPosition1() {
        Random rand = new Random();
        // Define the range for the y position
        int minY = 50; // Minimum y position
        int maxY = (Constantes.ALTURA_VENTANA-Constantes.MARGEN) - Constantes.Y_POS_INI_EXTRA; // Maximum y position
        return rand.nextInt(maxY - minY + 1) + minY;
    }

    public int deplacementSoucoupe1() {
        if(this.vivo && Tiempo.compteTours % 2 == 0) {
            if (this.xPos > 0) {
                this.xPos = this.xPos - this.dx;
            } else {
                this.xPos = Constantes.X_POS_INI_EXTRA;
                this.yPos = getRandomYPosition1(); //Nueva posición en y
            }    
        }
        return this.xPos;
    }
        
    public void dessinSoucoupe1(Graphics g) {    
        if(this.vivo == false) {
            this.destructionSoucoupe1();
        }
        g.drawImage(this.img, this.deplacementSoucoupe1(), this.yPos, null);            
    }    
    
    public void destructionSoucoupe1() {
        if(compteur1 < 300) {
            super.ico = new ImageIcon(getClass().getResource(super.strImg2));
            super.img = this.ico.getImage();
            compteur1++;
        } else {
            this.xPos = Constantes.X_POS_INI_EXTRA;
            this.yPos = getRandomYPosition1(); //nueva posición en y
        }        
    }
}
