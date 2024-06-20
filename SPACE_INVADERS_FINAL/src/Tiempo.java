

public class Tiempo implements Runnable {

// variables
	
	private final int PAUSE = 5; //tiempo de espera entre bucles
	public static int compteTours = 0;
	
	
// metodos
	
	@Override
	public void run() {		
		while(Main.jeu == true){ 
			compteTours++;
			Main.scene.repaint(); 
			try {Thread.sleep(PAUSE);} // tiempo de pausa
			catch (InterruptedException e) {}
		}
	}	

    
		
}