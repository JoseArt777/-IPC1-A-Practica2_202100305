
import java.awt.Graphics;

import javax.swing.ImageIcon;



public class ProyectilNave extends Entidad {
	
// variables	
	
	private boolean vaisseauTire = false;
	

	//Constructores	
	
	public ProyectilNave() {
    // Super clase variables
    super.largeur = Constantes.LARGO_PROYECTIL_NAVE;
    super.altura = Constantes.ALTURA_PROYECTIL_NAVE;
    super.dx = 0;
    super.dy = Constantes.DY_PROYECTIL_NAVE;
    
    // Ajustar la posición inicial del tiro para que salga del centro vertical del vaisseau
    super.xPos = Constantes.X_POS_INI_NAVE + (Constantes.LARGO_NAVE - Constantes.LARGO_PROYECTIL_NAVE) / 2;
    super.yPos = Constantes.Y_POS_INI_NAVE + Constantes.ALTURA_PROYECTIL_NAVE;
    
    // Dirección del proyectil
    super.strImg1 = "/tirVaisseau.png";
    super.strImg2 = "";
    super.strImg3 = "";
    
    // Cargar la imagen del tiro
    super.ico = new ImageIcon(getClass().getResource(super.strImg1));
    super.img = this.ico.getImage();
}
	
	// metodos
	public boolean isVaisseauTire() {return vaisseauTire;}

	public void setVaisseauTire(boolean vaisseauTire) {this.vaisseauTire = vaisseauTire;}

	public int deplacementTirVaisseau() {
    if (this.vaisseauTire) {
        // Mueve el tiro horizontalmente
        this.xPos += Constantes.DY_PROYECTIL_NAVE;
        
        // Verifica si el tiro está fuera de la pantalla
        if (this.xPos < 0 || this.xPos > Constantes.LARGO_VENTANA) {
            this.vaisseauTire = false; // Marca el tiro como no disparado
        }
    }
    // Devuelve la posición vertical sin cambiarla
    return this.yPos;
}
	
	public void dessinTirVaisseau(Graphics g) {
		if(this.vaisseauTire == true) {
			g.drawImage(this.img, this.xPos, this.deplacementTirVaisseau(), null);}	
	}
	
	public boolean tueAlien(Alien alien) {
    if (this.vaisseauTire && 
        this.yPos < alien.getyPos() + alien.getAltura() &&
        this.yPos + this.altura > alien.getyPos() &&
        this.xPos + this.largeur > alien.getxPos() &&
        this.xPos < alien.getxPos() + alien.getLargeur()) {

        // Reproducir sonido
        Audio.playSound("/sonAlienMeurt.wav");
        // Marcar el tiro como no disparado
        this.vaisseauTire = false;
        return true; // Indica que el alien fue alcanzado
    } else {
        return false; // Indica que el alien no fue alcanzado
    }
}
        	
	
	public boolean detruitSoucoupe(Item1 soucoupe) { 
		// Contacto proyectil con el item
		if(this.yPos < soucoupe.getyPos() + soucoupe.getAltura() && this.yPos + this.altura > soucoupe.getyPos() 
			&& this.xPos + this.largeur > soucoupe.getxPos() && this.xPos < soucoupe.getxPos() + soucoupe.getLargeur()){
				this.vaisseauTire = false;
				return true;
			} 
		else{return false;}
	}
        public boolean detruitSoucoupe1(Item2 soucoupe1) { 
		// Contacto proyectil con el item
		if(this.yPos < soucoupe1.getyPos() + soucoupe1.getAltura() && this.yPos + this.altura > soucoupe1.getyPos() 
			&& this.xPos + this.largeur > soucoupe1.getxPos() && this.xPos < soucoupe1.getxPos() + soucoupe1.getLargeur()){
				this.vaisseauTire = false;
				return true;
			} 
		else{return false;}
	}
        public boolean detruitSoucoupe2(Item3 soucoupe2) { 
		// Contacto proyectil con el item
		if(this.yPos < soucoupe2.getyPos() + soucoupe2.getAltura() && this.yPos + this.altura > soucoupe2.getyPos() 
			&& this.xPos + this.largeur > soucoupe2.getxPos() && this.xPos < soucoupe2.getxPos() + soucoupe2.getLargeur()){
				this.vaisseauTire = false;
				return true;
			} 
		else{return false;}
	}
        public boolean detruitSoucoupe3(Item4 soucoupe3) { 
		// Contacto proyectil con el item
		if(this.yPos < soucoupe3.getyPos() + soucoupe3.getAltura() && this.yPos + this.altura > soucoupe3.getyPos() 
			&& this.xPos + this.largeur > soucoupe3.getxPos() && this.xPos < soucoupe3.getxPos() + soucoupe3.getLargeur()){
				this.vaisseauTire = false;
				return true;
			} 
		else{return false;}
	}
        
}
