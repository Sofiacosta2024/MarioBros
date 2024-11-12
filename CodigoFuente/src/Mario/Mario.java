package Mario;

import Sonido.*;
import Fabrica.Sprite;
import Logica.*;
import Vista.*;
import java.lang.Math;

import Colisionador.ColisionadorVisitorMario;
import Threads.TemporizadorEstadoEstrella;

public class Mario extends Entidad implements EntidadMario{

	protected int vidas;
	protected int monedas;
	protected final int MINIMOY=60;
	protected boolean cayendoVacio;
	protected final char QUIETO = 'q';
	protected final char DERECHA = 'd';
	protected final char IZQUIERDA = 'a';
	protected final char SALTO = 'w';
	protected final int suelo = 60;
	protected char direccionMovimiento, direccionMovimientoAnterior;
	protected EstadoMario estado,anterior;
	protected boolean enSuelo; 
	protected double gravedad;
	protected int velocidadSalto;
	protected String rutaImagen;
	protected boolean puedeDerecha,puedeIzquierda,cambioEstado;
	protected char sentidoMovimientoMario;
	protected double progreso;
	protected boolean saltando;
	protected int posInicialSalto;
	protected int tiempoCaida; 
	protected int factorVelocidad;
	private Juego juego;
	protected int limiteDerecha, limiteIzquierda; 
	protected String nombre;
	protected int puntaje;
	protected Mario personaje;
	public Mario(int x, int y, int velocidad, Sprite sprite, Juego juego) {
		super(x,y, velocidad, sprite,16,16);
		//rutaImagen = sprite.getModo();
		estado = new EstadoNormal(rutaImagen);
		anterior=estado;
		vidas = 3;
		enSuelo = true;
		gravedad = 0.5; 
		velocidadSalto = 30; 
		this.velocidad=velocidad;
		puedeDerecha=true;
		puedeIzquierda=true;
		puntaje=0;
		cambioEstado=false;
		cayendoVacio=false;
		posInicialSalto = getPosicionY();
		progreso = 0;
		saltando = false;
		factorVelocidad = 0;
		limiteDerecha=5000;
		limiteIzquierda=0;
		this.juego = juego;
	}

	public void actualizarMovimientoMario(char direccion) {
		int desplazamientoX = 0;
		int desplazamientoY = 0;
		if(direccion == DERECHA && puedeDerecha) {
			desplazamientoX += 50;
			puedeIzquierda = true;
		}
		if(direccion == IZQUIERDA && puedeIzquierda) {
			desplazamientoX -= 50;
			puedeDerecha = true;
		}
		if(direccion == SALTO) {
			desplazamientoY -= velocidadSalto;
		}
		if(direccion == QUIETO) {
			detenerMario();
		}
		if (saltando) {
			if(direccion == 'd')
				moverDerecha();
			if(direccion == 'a')
				moverIzquierda();
			double factorVelocidad = Math.cos(progreso) * velocidadSalto;
			int nuevaPos = (int) Math.round(getPosicionY() + factorVelocidad);
			setPosicionY(nuevaPos);
			progreso += 0.15; 		
			saltando = progreso <= Math.PI;
			actualizarAreaColision();
			if(progreso > 0 && getPosicionY() < MINIMOY && !cayendoVacio){
				detenerSalto();
				setPosicionY(posInicialSalto);
				actualizarAreaColision();
			}
		} 
		else {
			if(progreso > 0) {
				setPosicionY(posInicialSalto);
				actualizarAreaColision();
			}
			progreso = 0;
			saltando = false;
			if(getPosicionX()>limiteDerecha || getPosicionX()<limiteIzquierda){
				posInicialSalto = suelo;
				saltando=true;
				progreso=Math.PI/2;
				if(getPosicionY()<=suelo) {
					setPosicionY(suelo);
					actualizarAreaColision();
					notificarObservador();
					limiteDerecha=5000;
					limiteIzquierda=0;
					enSuelo=true;
					progreso = 0;
					saltando = false;
				}
			}
		} 
		if(desplazamientoX!= 0 || desplazamientoY!=0) {
			char x = direccionMovimiento;
			actualizarSprite(direccion);			
			direccionMario(desplazamientoX, desplazamientoY);
			if(direccion==x) {
				observador.actualizarPosicionTamano();
			}
			else {
				notificarObservador();
			}
		}
	}

	public void saltar() {
		if(progreso == 0){
			Sonido.obtenerInstancia().reproducirSonidoSaltar();
			saltando = true;
			posInicialSalto = getPosicionY();
			sentidoMovimientoMario = 'w';
		}
	}

	public void detenerSalto(){
		progreso = 0;
		saltando = false;
	}

	public void rebotarHaciaAbajo(){
		if(progreso<((Math.PI /2))) {
			progreso = Math.PI - progreso;
		}
	}

	public void morirPorVacio() {
		Sonido.obtenerInstancia().reproducirSonidoCaeAlVacio();
		saltando=true;
		progreso=Math.PI/2;
		cayendoVacio=true;
	}

	public void direccionMario(int desplazamientoX, int desplazamientoY) {
		if(desplazamientoX > 0) {
			direccionMovimiento = 'd';
			direccionMovimientoAnterior = direccionMovimiento;
		}
		if(desplazamientoX<0) {
			direccionMovimiento = 'a';
			direccionMovimientoAnterior = direccionMovimiento;

		}
		if(desplazamientoY < 0) {
			direccionMovimiento = 'w';
			direccionMovimientoAnterior = direccionMovimiento;
		}

	}

	public void moverDerecha() {
		if(puedeDerecha) {
			sentidoMovimientoMario = 'd';
			posicionX+=5;
			actualizarAreaColision();
		}
	}

	public void moverIzquierda() {
		if(posicionX>=40 && puedeIzquierda) {
			sentidoMovimientoMario = 'a';
			posicionX-=5;
			actualizarAreaColision();
		}
	}

	public void detenerMario() {
		if(direccionMovimiento == DERECHA)
			sprite = estado.getSpriteDetenidoDerecha();
		else 
			if(direccionMovimiento == IZQUIERDA)
				sprite = estado.getSpriteDetenidoIzquierda();
		direccionMovimiento='q';
		sentidoMovimientoMario = 'q';
		notificarObservador();
	}

	public Sprite actualizarSprite(char direccion) {
		
		if(direccion == DERECHA) {
			if(direccionMovimiento == SALTO) 
				sprite = estado.getSpriteSaltandoADerecha();
			else 
				sprite = estado.getSpriteDerecha();
		}
		if(direccion == IZQUIERDA) {
			if(direccionMovimiento == SALTO)
				sprite = estado.getSpriteSaltandoAIzquierda();
			else
				sprite = estado.getSpriteIzquierda();
		}
		if(direccion == SALTO) {
			if(direccionMovimientoAnterior==DERECHA)
				sprite = estado.getSpriteSaltandoADerecha();
			else
			if(direccionMovimientoAnterior==IZQUIERDA)
				sprite = estado.getSpriteSaltandoAIzquierda();
		}
		return sprite;
	}

	public void lanzarBolaFuego() {
		if(estado.lanzarBolaFuego()) {
			int posicionXBola = posicionX + 45; 
			int posicionYBola = posicionY + 15;
			Sprite spriteBola = new Sprite(rutaImagen+ "/FireBall.gif",rutaImagen);
			BolaFuego bolaFuego = new BolaFuego(posicionXBola, posicionYBola,5,spriteBola,sentidoMovimientoMario,direccionMovimientoAnterior);
			juego.getNivelActual().agregarProyectil(bolaFuego);
			juego.registrarObserverProyectiles(bolaFuego);
			bolaFuego.setEnMovimiento(true);
		}
	}

	public void decrementarVida() {
		Sonido.obtenerInstancia().reproducirSonidoMorir();
		vidas--;
	}
	public void resetearPosicion() {
		posicionX = 50;
		posicionY = 60;
		estado = new EstadoNormal(rutaImagen);
		puedeDerecha=true;
		puedeIzquierda=true;
		cayendoVacio=false;
		esColisionable = true;
		actualizarAreaColision();
		notificarObservador();
	}

	public void retirar() {

	}

	public void mover(){
		actualizarMovimientoMario(sentidoMovimientoMario);
	}

	public void iniciarRegresionEstadoEstrella(){
		TemporizadorEstadoEstrella temporizadorEstrella = new TemporizadorEstadoEstrella(this);
		temporizadorEstrella.start();
	}

	public void aumentarMonedas() {
		monedas++;
	}

	public void aumentarVida() {
		vidas++;
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {

	}
	
	public void setMarioEstadoNivel() {
		sprite = estado.getSpriteDetenidoDerecha();
		notificarObservador();
		observador.actualizarPosicionTamano();
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public void setCambioEstado(boolean b) {
		cambioEstado = b;
	}

	public void setEstadoAnterior(EstadoMario e) {
		anterior=e;
	}

	public void setPuedeMoverseALaDerecha(boolean puede) {
		puedeDerecha=puede;
	}

	public void setPuedeMoverseALaIzquierda(boolean puede) {
		puedeIzquierda=puede;
	}

	public void setEstadoMario(EstadoMario estadoActual) {
		estado = estadoActual;
	}

	public void setObserver(Observer observer) {
		observador = observer;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public void setEnSuelo(boolean cayendo) {
		enSuelo=cayendo;
	}

	public void setVelocidadSalto(int velocidadSalto) {
		this.velocidadSalto=velocidadSalto;
	}

	public void setGravedad(double gravedad) {
		this.gravedad=gravedad;
	}

	public void setLimiteDerecha(int i) {
		limiteDerecha = i;
	}

	public void setLimiteIzquierda(int i) {
		limiteIzquierda = i;
	}

	public EstadoMario getEstadoMario() {
		return estado;
	}
	
	public EstadoMario getEstadoAnterior() {
		return anterior;
	}

	public int getVidas() {
		return vidas;
	}

	public boolean getCambioEstado() {
		return cambioEstado;
	} 

	public boolean getSaltando() {
		return saltando;
	}

	public int getVelocidadSalto() {
		return velocidadSalto;
	}

	public double getGravedad() {
		return gravedad;
	}

	public boolean getEnSuelo() {
		return enSuelo;
	}

	public int getMonedas() {
		return monedas;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public Juego getJuego() {
		return juego;
	}
	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
		if(this.puntaje < 0)
			this.puntaje = 0;
	}
	
	public void setPersonaje(Mario m) {
		personaje = m;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Mario getPersonaje() {
		return personaje;
	}
}