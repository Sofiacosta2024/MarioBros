package Mario;

import Fabrica.Sprite;
public class EstadoFlorFuego extends EstadoSuperMario{
	
	public EstadoFlorFuego() {
		lanzarBola = true;
	}
	
	public boolean lanzarBolaFuego() {
		return lanzarBola;
	}
	
	public int puntajeFlorFuego() {
		return 50;
	}
	
	public Sprite getSpriteDetenidoDerecha(Sprite[] sprites) {
		return sprites[18];
	}
	
	public Sprite getSpriteDetenidoIzquierda(Sprite[] sprites) {
		return sprites[19];
	}
	
	public Sprite getSpriteDerecha(Sprite[] sprites) {
		return sprites[20];
	}
	
	public Sprite getSpriteIzquierda(Sprite[] sprites) {
		return sprites[21];
	}

	public Sprite getSpriteSaltandoADerecha(Sprite[] sprites) {
		return sprites[22];
	}

	public Sprite getSpriteSaltandoAIzquierda(Sprite[] sprites) {
		return sprites[23];
	}
}
