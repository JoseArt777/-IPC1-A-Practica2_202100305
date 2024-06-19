package Entidades;

import javax.swing.ImageIcon;

import recursos.Constantes;

public class Alien extends Entidad {
	
// variables
	
	
// constructor
	
	public Alien(int xPos, int yPos, String strImg1, String strImg2) {
		
		// Inicialización de variables de la super classe
		super.xPos = xPos;
		super.yPos = yPos;
		super.largeur = Constantes.LARGO_ALIEN;
		super.altura = Constantes.ALTURA_ALIEN;
		super.dx = 0;
		super.dy = 0;
		super.vivo = true;
		// Adresse des images de l'alien
		super.strImg1 = strImg1;
		super.strImg2 = strImg2;
		super.strImg3 = "/images/alienMeurt.png";
		// Chargement de l'image de l'alien
		super.ico = new ImageIcon(getClass().getResource(super.strImg1));
		super.img = this.ico.getImage();
	}
	
	
// metodos
	public void choixImage(boolean pos1) {

            if(this.vivo == true) {
		 if(pos1 == true) {super.ico = new ImageIcon(getClass().getResource(strImg1));} 
		  else {super.ico = new ImageIcon(getClass().getResource(strImg2));}
		}
		else {super.ico = new ImageIcon(getClass().getResource(strImg3));}		
		super.img = this.ico.getImage();
	}

}
