package Entidades;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import juego.Main;
import recursos.Tiempo;
import recursos.Constantes;

public class Nave extends Entidad {
    

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
	
	// variables
	private int compteur = 0;
	
	//Constructor
		
		public Nave() {
			
			// super clase
			super.xPos = Constantes.X_POS_INI_NAVE;
			super.yPos = Constantes.Y_POS_INI_NAVE;
			super.largeur = Constantes.LARGO_NAVE;
			super.altura = Constantes.ALTURA_NAVE;
			super.dx = 0;
			super.dy = 0;
			// imagenes
			super.strImg1 = "/images/vaisseau.png";
			super.strImg2 = "/images/vaisseauDetruit1.png";
			super.strImg3 = "/images/vaisseauDetruit2.png";
			// dibuja las imagenes
			super.ico = new ImageIcon(getClass().getResource(super.strImg1));
			super.img = this.ico.getImage();
			super.vivo = true;
		}
		
		
	// metodos
	public int deplacementVaisseau() {

            if(this.dy < 0){if(this.yPos > Constantes.LIMITE_SUPERIOR_NAVE) {this.yPos = this.yPos + this.dy;}
		}else if(dy > 0) {if(this.yPos + this.dy < Constantes.LIMITE_INFERIOR_NAVE) {this.yPos = this.yPos + this.dy;}}
		return this.yPos;
	}
       
	
		public void dessinVaisseau(Graphics g) {
		if (!this.vivo) {
			this.destructionVaisseau();
		}
		g.drawImage(this.img, this.xPos, this.deplacementVaisseau(), null);
	}
	
	public void destructionVaisseau() {
		if(compteur < 900) {
			if(Tiempo.compteTours % 2 == 0)
                        {
                            super.ico = new ImageIcon(getClass().getResource(super.strImg2));
                        }
			else {super.ico = new ImageIcon(getClass().getResource(super.strImg3));
                        }
			compteur++;
		}else 
                {
                    		super.img = this.ico.getImage();

                }		
	}
        public int getCentreVaisseauX() {
    return this.xPos + this.largeur / 2;
}
        
        
}
