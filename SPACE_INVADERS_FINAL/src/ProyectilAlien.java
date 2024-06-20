import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;



public class ProyectilAlien extends Entidad {

    // variables

    Random hasard = new Random();

    // Constructor    
    
    public ProyectilAlien(int [] tabPositionAlien) {
        
        // super clase
        super.xPos = tabPositionAlien[0] + Constantes.LARGO_ALIEN /2 - 1;
        super.yPos = tabPositionAlien[1] + Constantes.ALTURA_ALIEN;
        super.largeur = Constantes.LARGO_PROYECTIL_ALIEN;
        super.altura = Constantes.ALTURA_PROYECTIL_ALIEN;
        super.dx = -Constantes.DY_PROYECTIL_ALIEN; // Cambiado a movimiento hacia la derecha
        super.dy = 0; // No hay movimiento vertical
        // Dirección inicial del tiro hacia la derecha
        // imagenes de la nave
        super.strImg1 = "/tirAlien1.png";
        super.strImg2 = "/tirAlien2.png";
        super.strImg3 = "";
      
        if(hasard.nextInt(2) == 0) {
            super.ico = new ImageIcon(getClass().getResource(super.strImg1));
        } else {
            super.ico = new ImageIcon(getClass().getResource(super.strImg2));
        }
        super.img = this.ico.getImage();
    }
    
    // metodos
    
    public int deplacementTirAlien() {
        // Movimiento horizontal
        super.xPos += super.dx;
        return xPos;
    }   
    
    public void dessinTirAlien(Graphics g) {
        g.drawImage(this.img, this.deplacementTirAlien(), this.yPos, null);
    }       

    public boolean toucheVaisseau(Nave vaisseau) {
        // el proyctil del alien toca a la nave?
        if(this.yPos < vaisseau.getyPos() + vaisseau.getAltura() && this.yPos + this.altura > vaisseau.getyPos()  && this.xPos + this.largeur > vaisseau.getxPos() && this.xPos < vaisseau.getxPos() + vaisseau.getLargeur()){
                this.yPos = 700;
                Audio.playSound("/sonDestructionVaisseau.wav");
                  Main.jeu=false;
                  
                  

                return true;
            } 
        else{
            return false;
        }
    }
}