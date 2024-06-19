
package Entidades;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;
import recursos.Audio;
import recursos.Tiempo;
import recursos.Constantes;

public class Item3 extends Entidad {

    //Variables

    public Audio musiqueSoucoupe = new Audio("/sons/sonSoucoupePasse.wav");
    public Audio musiqueDestructionSoucoupe = new Audio("/sons/sonDestructionSoucoupe.wav");

    private int compteur2 = 0;
    
    // Constructor
    
    public Item3() {        
        super.xPos = Constantes.X_POS_INI_DTIEMPO;
        super.yPos = getRandomYPosition2();
        super.largeur = Constantes.LARGO_DTIEMPO;
        super.altura = Constantes.ALTURA_DTIEMPO;
        super.dx = Constantes.DX_TIEMPO;
        super.dy = 50;
        this.strImg1 = "/images/toxico.png";
        this.strImg2 = "/images/alerta.png";
        this.strImg3 = "";

        super.ico = new ImageIcon(getClass().getResource(strImg1));
        super.img = this.ico.getImage();
        super.vivo = true;

        this.musiqueSoucoupe.play();
        this.musiqueDestructionSoucoupe.stop();
        this.compteur2 = 0;
    }
    
    // Mëtodos
    
    private int getRandomYPosition2() {
        Random rand2 = new Random();
        // Define the range for the y position
        int minY = 0; // Minimum y position
        int maxY = (Constantes.ALTURA_VENTANA-Constantes.MARGEN) - Constantes.Y_POS_INI_DTIEMPO; // Maximum y position
        return rand2.nextInt(maxY - minY + 1) + minY;
    }

    public int deplacementSoucoupe2() {
        if(this.vivo && Tiempo.compteTours % 2 == 0) {
            if (this.xPos > 0) {
                this.xPos = this.xPos - this.dx;
            } else {
                this.xPos = Constantes.X_POS_INI_DTIEMPO;
                this.yPos = getRandomYPosition2(); //Nueva posición en y
            }    
        }
        return this.xPos;
    }
        
    public void dessinSoucoupe2(Graphics g) {    
        if(this.vivo == false) {
            this.destructionSoucoupe2();
        }
        g.drawImage(this.img, this.deplacementSoucoupe2(), this.yPos, null);            
    }    
    
    public void destructionSoucoupe2() {
        if(compteur2 < 300) {
            super.ico = new ImageIcon(getClass().getResource(super.strImg2));
            super.img = this.ico.getImage();
            compteur2++;
        } else {
            this.xPos = Constantes.X_POS_INI_DTIEMPO;
            this.yPos = getRandomYPosition2(); //nueva posición en y
        }        
    }
}
