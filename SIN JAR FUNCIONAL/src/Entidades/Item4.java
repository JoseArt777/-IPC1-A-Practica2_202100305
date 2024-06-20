
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

public class Item4 extends Entidad {

    //Variables

    public Audio musiqueSoucoupe = new Audio("/sons/sonSoucoupePasse.wav");
    public Audio musiqueDestructionSoucoupe = new Audio("/sons/sonDestructionSoucoupe.wav");

    private int compteur3 = 0;
    
    // Constructor
    
    public Item4() {        
        super.xPos = Constantes.X_POS_INI_DTIEMPO;
        super.yPos = getRandomYPosition3();
        super.largeur = Constantes.LARGO_DTIEMPO;
        super.altura = Constantes.ALTURA_DTIEMPO;
        super.dx = Constantes.DX_TIEMPO;
        super.dy = 0;
        this.strImg1 = "/images/pena.png";
        this.strImg2 = "/images/precaucion.png";
        this.strImg3 = "";

        super.ico = new ImageIcon(getClass().getResource(strImg1));
        super.img = this.ico.getImage();
        super.vivo = true;

        this.musiqueSoucoupe.play();
        this.musiqueDestructionSoucoupe.stop();
        this.compteur3 = 0;
    }
    
    // Mëtodos
    
    private int getRandomYPosition3() {
        Random rand3 = new Random();
        int minY = 50; 
        int maxY = (Constantes.ALTURA_VENTANA-Constantes.MARGEN) - Constantes.Y_POS_INI_PENALIZACIÓN; 
        return rand3.nextInt(maxY - minY + 1) + minY;
    }

    public int deplacementSoucoupe3() {
        if(this.vivo && Tiempo.compteTours % 2 == 0) {
            if (this.xPos > 0) {
                this.xPos = this.xPos - this.dx;
            } else {
                this.xPos = Constantes.X_POS_INI_PENALIZACIÓN;
                this.yPos = getRandomYPosition3(); //Nueva posición en y
            }    
        }
        return this.xPos;
    }
        
    public void dessinSoucoupe3(Graphics g) {    
        if(this.vivo == false) {
            this.destructionSoucoupe3();
        }
        g.drawImage(this.img, this.deplacementSoucoupe3(), this.yPos, null);            
    }    
    
    public void destructionSoucoupe3() {
        if(compteur3 < 300) {
            super.ico = new ImageIcon(getClass().getResource(super.strImg2));
            super.img = this.ico.getImage();
            compteur3++;
        } else {
            this.xPos = Constantes.X_POS_INI_PENALIZACIÓN;
            this.yPos = getRandomYPosition3(); //nueva posición en y
        }        
    }
}
