package Enemigo;

import Fabrica.Sprite;

public abstract class EstadoKoopaTroopa {
	
	protected KoopaTroopa koopaTroopa;
	
	public abstract void mover(int nuevaX, int nuevaY);
	public abstract void afectado(KoopaTroopa koopaTroopa, Sprite[] spritesKoopa);
	public abstract void interactuarEnemigos(Enemigo enemigo);
}
