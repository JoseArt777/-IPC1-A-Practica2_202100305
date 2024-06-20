
public abstract class Constantes {
	
        //Tiempo
        // Ventana
	public static final int LARGO_VENTANA = 1000;
	public static final int ALTURA_VENTANA = 600;
	public static final int MARGEN = 50;
	
        //Nave
	public static final int LARGO_NAVE = 30;
	public static final int ALTURA_NAVE = 30;
	
	// Posición inicial de la nave
	public final static int X_POS_INI_NAVE = 75;
	public final static int Y_POS_INI_NAVE = 600/2;	
	
	// Unidad de desplazamiento de la nave
	public final static int DY_NAVE = 2;
	
	// Limite de desplazamiento de la nave
	public final static int LIMITE_SUPERIOR_NAVE = 40;
	public final static int LIMITE_INFERIOR_NAVE = 500;	
	
	// Dimensiones alien
	public static final int LARGO_ALIEN = 30;
	public static final int ALTURA_ALIEN= 20;	
	
	// posiciones de alien
	public final static int Y_POS_INI_ALIEN = 50;
	public final static int X_POS_INI_ALIEN = 750;
	public final static int ESPACIO_FILAS_ALIEN = 32;
	public final static int ESPACIO_COLUMNAS_ALIEN = 0;
	
	// Unidad de desplazamiento alien
	public final static int DX_ALIEN = 20;
	public final static int DY_ALIEN = 2;
	public final static int VELOCIDAD_ALIEN = 2;	
	
	// Numero de aliens
	public final static int NUMERO_ALIENS = 40;
	
	// proyectil nave	
	// dimensiones del proyectil
	public static final int LARGO_PROYECTIL_NAVE = 32;
	public static final int ALTURA_PROYECTIL_NAVE = 32;

	// unidad de desplazamiento del proyectil
	public final static int DY_PROYECTIL_NAVE = 2;
	
	
        //proyectil de los aliens
	public static final int LARGO_PROYECTIL_ALIEN = 30;
	public static final int ALTURA_PROYECTIL_ALIEN = 30;	
	
	// unidad de desplazamiento
	public final static int DY_PROYECTIL_ALIEN = 1;

	// Items
	public static final int LARGO_TIEMPO = 30;
	public static final int ALTURA_TIEMPO = 30;
        
        public static final int LARGO_EXTRA= 30;
        public static final int ALTURA_EXTRA=30;
        
        public static final int LARGO_DTIEMPO=30;
        public static final int ALTURA_DTIEMPO=30;
        
        public static final int LARGO_PENALIZACIÓN=30;
        public static final int ALTURA_PENALIZACIÓN=30;

	// Posición inicial
	public final static int X_POS_INI_TIEMPO = LARGO_VENTANA;
	public final static int Y_POS_INI_TIEMPO = 50;
        
        public final static int X_POS_INI_EXTRA = LARGO_VENTANA;
	public final static int Y_POS_INI_EXTRA = 50;
        
        public final static int X_POS_INI_DTIEMPO = LARGO_VENTANA;
	public final static int Y_POS_INI_DTIEMPO = 50;
        
        public final static int X_POS_INI_PENALIZACIÓN = LARGO_VENTANA;
	public final static int Y_POS_INI_PENALIZACIÓN = 50;
       

	// unidad de desplazamiento
	public final static int DX_TIEMPO = 1;
	
        // Puntos
        // puntos aliens
	public static final int VALOR_ALIEN_TIPO1 = 30;
	public static final int VALOR_ALIEN_TIPO2 = 20;
	public static final int VALOR_ALIEN_TIPO3 = 10;
	public static final int VALOR_TIEMPO = 10;
        public static final int VALOR_EXTRA=10;
        public static final int VALOR_DTIEMPO=-10;
        public static final int VALOR_PENALIZACIÓN=-10;


        
}


