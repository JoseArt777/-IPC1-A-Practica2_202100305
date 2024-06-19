package Entidades;


import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;
import recursos.Audio;
import recursos.Tiempo;
import recursos.Constantes;

public class Item1 extends Entidad {

    //Variables

    public Audio musiqueSoucoupe = new Audio("/sons/sonSoucoupePasse.wav");
    public Audio musiqueDestructionSoucoupe = new Audio("/sons/sonDestructionSoucoupe.wav");

    private int compteur = 0;
    
    // Constructor
    
    public Item1() {        
        super.xPos = Constantes.X_POS_INI_TIEMPO;
        super.yPos = getRandomYPosition();
        super.largeur = Constantes.LARGO_TIEMPO;
        super.altura = Constantes.ALTURA_TIEMPO;
        super.dx = Constantes.DX_TIEMPO;
        super.dy = 0;
        this.strImg1 = "/images/soucoupe.png";
        this.strImg2 = "/images/diez.png";
        this.strImg3 = "";

        super.ico = new ImageIcon(getClass().getResource(strImg1));
        super.img = this.ico.getImage();
        super.vivo = true;

        this.musiqueSoucoupe.play();
        this.musiqueDestructionSoucoupe.stop();
        this.compteur = 0;
    }
    
    // Mëtodos
    
    private int getRandomYPosition() {
        Random rand = new Random();
        // Define the range for the y position
        int minY = 50; // Minimum y position
        int maxY = (Constantes.ALTURA_VENTANA-Constantes.MARGEN) - Constantes.Y_POS_INI_TIEMPO; // Maximum y position
        return rand.nextInt(maxY - minY + 1) + minY;
    }

    public int deplacementSoucoupe() {
        if(this.vivo && Tiempo.compteTours % 2 == 0) {
            if (this.xPos > 0) {
                this.xPos = this.xPos - this.dx;
            } else {
                this.xPos = Constantes.X_POS_INI_TIEMPO;
                this.yPos = getRandomYPosition(); //Nueva posición en y
            }    
        }
        return this.xPos;
    }
        
    public void dessinSoucoupe(Graphics g) {    
        if(this.vivo == false) {
            this.destructionSoucoupe();
        }
        g.drawImage(this.img, this.deplacementSoucoupe(), this.yPos, null);            
    }    
    
    public void destructionSoucoupe() {
        if(compteur < 300) {
            super.ico = new ImageIcon(getClass().getResource(super.strImg2));
            super.img = this.ico.getImage();
            compteur++;
        } else {
            this.xPos = Constantes.X_POS_INI_TIEMPO;
            this.yPos = getRandomYPosition(); //nueva posición en y
        }        
    }
}
