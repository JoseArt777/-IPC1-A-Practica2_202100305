
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Teclas implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		if(Main.scene.vaisseau.isVivo() == true) {
			if(e.getKeyCode() == KeyEvent.VK_DOWN){Main.scene.vaisseau.setDy(Constantes.DY_NAVE);}
			else if(e.getKeyCode() == KeyEvent.VK_UP){Main.scene.vaisseau.setDy(-Constantes.DY_NAVE);}
			else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
    if (!Main.scene.tirVaisseau.isVaisseauTire()) {
        Audio.playSound("/sonTirVaisseau.wav");
        // Ajusta la posición inicial del tiro para que salga desde el vaisseau
        Main.scene.tirVaisseau.setxPos(Main.scene.vaisseau.getxPos() + (Main.scene.vaisseau.getLargeur() - Constantes.LARGO_PROYECTIL_NAVE) / 2);
                Main.scene.tirVaisseau.setyPos(Main.scene.vaisseau.getyPos() + (Main.scene.vaisseau.getAltura() - Constantes.ALTURA_PROYECTIL_NAVE) / 2);
        Main.scene.tirVaisseau.setVaisseauTire(true);
    }
}
		}
                
                
	}

	@Override
	public void keyReleased(KeyEvent e) {
            Main.scene.vaisseau.setDy(0);}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
