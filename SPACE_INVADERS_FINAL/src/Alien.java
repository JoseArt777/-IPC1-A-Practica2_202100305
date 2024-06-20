import javax.swing.ImageIcon;

public class Alien extends Entidad {
    
    // Constructor
    public Alien(int xPos, int yPos, String strImg1, String strImg2) {
        // Inicialización de variables de la superclase
        super.xPos = xPos;
        super.yPos = yPos;
        super.largeur = Constantes.LARGO_ALIEN;
        super.altura = Constantes.ALTURA_ALIEN;
        super.dx = 0;
        super.dy = 0;
        super.vivo = true;
        super.strImg1 = strImg1;
        super.strImg2 = strImg2;
        super.strImg3 = "/alienMeurt.png"; // Asegúrate de que esta ruta sea correcta
        super.ico = new ImageIcon(getClass().getResource(strImg1)); // Cargar imagen 1 por defecto
        super.img = this.ico.getImage();
    }

    // Método para elegir la imagen
    public void EligeImagen(boolean pos1) {
        if (this.vivo) {
            if (pos1) {
                super.ico = new ImageIcon(getClass().getResource(strImg1));
            } else {
                super.ico = new ImageIcon(getClass().getResource(strImg2));
            }
        } else {
            super.ico = new ImageIcon(getClass().getResource(strImg3));
        }
        super.img = this.ico.getImage();
    }
}
