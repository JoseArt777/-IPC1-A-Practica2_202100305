package Entidades;
import java.awt.Graphics;
import java.util.Random;

import juego.Main;
import recursos.Audio;
import recursos.Tiempo;
import recursos.Constantes;

public class GrupoAliens {

    // Variables
    // tabla que contiene a los aliens
    private Alien tabAlien[][] = new Alien[8][5];
    private boolean vaEnBas, pos1; // numeros 
    private int vitesse;

    private int[] tabAlienMort = {-1,-1}; // alien muerto

    Random hasard = new Random();

    private int nombreAliens = Constantes.NUMERO_ALIENS;

    private int compteurSonAlien = 0;

    // Constructor

    public GrupoAliens() {
        this.initTableauAliens();
        this.vaEnBas = true;
        this.pos1 = true;
        this.vitesse = Constantes.VELOCIDAD_ALIEN;
    }

    // Metodo

    private void initTableauAliens() {
        // desplega la tabla de aliens
        for(int ligne = 0; ligne < 8; ligne++) {
            for(int colonne = 0; colonne < 5; colonne++) {
                int xPos = Constantes.X_POS_INI_ALIEN + (Constantes.LARGO_ALIEN + Constantes.ESPACIO_COLUMNAS_ALIEN) * colonne;
                int yPos = Constantes.Y_POS_INI_ALIEN + Constantes.ESPACIO_FILAS_ALIEN * ligne;
                String image1, image2;

                switch (colonne) {
                    case 0:
                        image1 = "/images/alienBas1.png";
                        image2 = "/images/alienBas2.png";
                        break;
                    case 1:
                    case 2:
                        image1 = "/images/alienMilieu1.png";
                        image2 = "/images/alienMilieu2.png";
                        break;
                    default:
                        image1 = "/images/alienHaut1.png";
                        image2 = "/images/alienHaut2.png";
                        break;
                }

                this.tabAlien[ligne][colonne] = new Alien(xPos, yPos, image1, image2);
            }
        }
    }

    public void dessinAliens(Graphics g) {
        if(Tiempo.compteTours % (100 - 10 * this.vitesse) == 0) {
            this.deplacementAliens();
        }
        // Diseño de aliens contenidos en la matriz
        for(int ligne = 0; ligne < 8; ligne++) {
            for(int colonne = 0; colonne < 5; colonne++) {
                if(this.tabAlien[ligne][colonne] != null) {
                    this.tabAlien[ligne][colonne].choixImage(pos1);
                    g.drawImage(this.tabAlien[ligne][colonne].getImg(), this.tabAlien[ligne][colonne].getxPos(), this.tabAlien[ligne][colonne].getyPos(), null);
                }
            }
        }
    }

    private boolean toucheHaut() { // Cambiada la lógica para el movimiento horizontal
        boolean reponse = false;
        for(int ligne = 0; ligne < 8; ligne++) {
            for(int colonne = 0; colonne < 5; colonne++) {
                if(this.tabAlien[ligne][colonne] != null) {
                    if(this.tabAlien[ligne][colonne].getyPos() < Constantes.MARGEN) {
                        reponse = true;
                        break;
                    }
                }
            }
        }
        return reponse;
    }

    private boolean toucheBas() { // Cambiada la lógica para el movimiento horizontal
        boolean reponse = false;
        for(int ligne = 0; ligne < 8; ligne++) {
            for(int colonne = 0; colonne < 5; colonne++) {
                if(this.tabAlien[ligne][colonne] != null) {
                    if(this.tabAlien[ligne][colonne].getyPos() > 
                    Constantes.ALTURA_VENTANA - Constantes.ALTURA_ALIEN - Constantes.DY_ALIEN - Constantes.MARGEN) {
                        reponse = true;
                        break;
                    }
                }
            }
        }
        return reponse;
    }

    public void alienTourneEtDescend() {
        if (this.toucheBas()) {
            // Si toca el borde inferior, mueve hacia la izquierda
            for (int ligne = 0; ligne < 8; ligne++) {
                for (int colonne = 0; colonne < 5; colonne++) {
                    if (this.tabAlien[ligne][colonne] != null) {
                        this.tabAlien[ligne][colonne].setxPos(this.tabAlien[ligne][colonne].getxPos() - Constantes.DX_ALIEN);
                    }
                }
            }
            this.vaEnBas = false;
            if (this.vitesse < 9) {
                this.vitesse++;
            }
        } else {
            if (this.toucheHaut()) {
                // Si toca el borde superior, mueve hacia la izquierda
                for (int ligne = 0; ligne < 8; ligne++) {
                    for (int colonne = 0; colonne < 5; colonne++) {
                        if (this.tabAlien[ligne][colonne] != null) {
                            this.tabAlien[ligne][colonne].setxPos(this.tabAlien[ligne][colonne].getxPos() - Constantes.DX_ALIEN);
                        }
                    }
                }
                this.vaEnBas = true;
                if (this.vitesse < 9) {
                    this.vitesse++;
                }
            }
        }
    }

    public void deplacementAliens() {
        if (this.tabAlienMort[0] != -1) {
            elimineAlienMort(tabAlienMort);
            tabAlienMort[0] = -1;
        }
        if (this.vaEnBas) {
            // Si va hacia abajo, mueve hacia la izquierda
            for (int ligne = 0; ligne < 8; ligne++) {
                for (int colonne = 0; colonne < 5; colonne++) {
                    if (this.tabAlien[ligne][colonne] != null) {
                        this.tabAlien[ligne][colonne].setyPos(this.tabAlien[ligne][colonne].getyPos() + Constantes.DY_ALIEN);
                    }
                }
            }
        } else {
            // Si va hacia arriba, mueve hacia la izquierda
            for (int ligne = 0; ligne < 8; ligne++) {
                for (int colonne = 0; colonne < 5; colonne++) {
                    if (this.tabAlien[ligne][colonne] != null) {
                        this.tabAlien[ligne][colonne].setyPos(this.tabAlien[ligne][colonne].getyPos() - Constantes.DY_ALIEN);
                    }
                }
            }
        }
        this.joueSonAlien();
        this.compteurSonAlien++;
        if (this.pos1) {
            this.pos1 = false;
        } else {
            this.pos1 = true;
        }
        this.alienTourneEtDescend();
    }

    public void tirVaisseauToucheAlien(ProyectilNave tirVaisseau) {
    for(int ligne = 0; ligne < 8; ligne++) {
        for(int colonne = 0; colonne < 5; colonne++) {
            if(this.tabAlien[ligne][colonne] != null) {
                if(tirVaisseau.tueAlien(this.tabAlien[ligne][colonne]) == true) {
                    this.tabAlien[ligne][colonne].vivo = false; // On tue l'alien
                    tirVaisseau.yPos = -1; // On tue le tir
                    this.tabAlienMort[0] = ligne;
                    this.tabAlienMort[1] = colonne; 
                    
                    // Determinar el valor según la columna
                    if(colonne == 0 ) {
                        Main.scene.score = Main.scene.score + Constantes.VALOR_ALIEN_TIPO3;
                    } else if(colonne == 1 || colonne == 2) {
                        Main.scene.score = Main.scene.score + Constantes.VALOR_ALIEN_TIPO2;
                    } else {
                        Main.scene.score = Main.scene.score + Constantes.VALOR_ALIEN_TIPO1;
                    }
                    break;
                }
            }                    
        }                    
    }
}

    private void elimineAlienMort(int[] tabAlienMort) {
        this.tabAlien[tabAlienMort[0]][tabAlienMort[1]] = null;
        this.nombreAliens--;
    }

    public int[] choixAlienQuiTire() {
        int positionAlien[] = {-1,-1};
        if(this.nombreAliens != 0) {
            do {
                int ligne = hasard.nextInt(7); // Tiro al azar de los aliens
                // tableau des aliens        
                for(int colonne = 4; colonne >= 0; colonne--) { 
                // en partant du bas
                    if(tabAlien[ligne][colonne] != null) {
                        positionAlien[0] = this.tabAlien[ligne][colonne].getxPos();
                        positionAlien[1] = this.tabAlien[ligne][colonne].getyPos();
                        break;
                    }
                }
            } while(positionAlien[0] == -1);
        }
        return positionAlien;
    }

    private void joueSonAlien() {
        int compteur = this.compteurSonAlien % 4;
        if(compteur == 0) {
            Audio.playSound("/sons/sonAlien1.wav");
        } else if(compteur == 1) {
            Audio.playSound("/sons/sonAlien2.wav");
        } else if(compteur == 2) {
            Audio.playSound("/sons/sonAlien3.wav");
        } else {
            Audio.playSound("/sons/sonAlien4.wav");
        }
    }

    public int getNombreAliens() {
        return nombreAliens;
    }

   public int positionAlienLePlusGauche() {
    int posHaut = Constantes.ALTURA_VENTANA; // Inicializar posición más arriba

    // Iterar sobre los aliens para encontrar la posición más a la izquierda horizontalmente
    for (int colonne = 0; colonne < 5; colonne++) {
        for (int ligne = 0; ligne < 8; ligne++) {
            if (this.tabAlien[ligne][colonne] != null) {
                int yPos = this.tabAlien[ligne][colonne].getyPos();

                // Actualizar posición más arriba
                if (yPos < posHaut) {
                    posHaut = yPos;
                }
            }
        }
    }

    return posHaut;
}
}
