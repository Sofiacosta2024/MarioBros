package Enemigo;
import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.Mario;

public class KoopaTroopa extends Enemigo{
	
	protected EstadoKoopaTroopa estado;
	protected boolean detener;
	protected int contadorColisiones;
	
	public KoopaTroopa(int x, int y, int velocidad, Sprite sprite) {
		super(x,y, velocidad, sprite,32,48);
		puntaje = 90;
		puntajeMatarAMario = -45;
		estado = new EstadoCaminando(this);
		TIEMPO_ENFRIAMIENTO = 1000;
		detener = false;
		contadorColisiones = 0;
	}
	
	public void setPosicionInicial() {
		super.setPosicionInicial();
		estado = new EstadoCaminando(this);
		sprite = new Sprite(sprite.getModo() + "/Koopagreen.gif", sprite.getModo());
		detener = false;
		contadorColisiones = 0;
	}
	
	public void atacar(Mario mario) {
		mario.decrementarVida();
	}
	
	public void mover(int nuevaX, int nuevaY) {
		if(!detener)
			estado.mover(nuevaX, nuevaY);
	}
	
	public void movilidad() {
		if(contadorColisiones <= 1) {
			detener = !detener;
			activarEnfriamiento();
		}
		contadorColisiones++;
	}
	
	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarKoopa(this);
	}
	
	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		visitor.visitarKoopaTroopa(this);
	}
	
	public void atacado() {
		estado.afectado(this);
	}
	
	public void setEstado(EstadoKoopaTroopa estado) {
		this.estado = estado;
	}
	
	public EstadoKoopaTroopa getEstado() {
		return estado;
	}
	
	public boolean getMovilidad() {
		return detener;
	}
	
}
