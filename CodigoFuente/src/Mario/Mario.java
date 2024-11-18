package Mario;

import Sonido.*;
import Fabrica.Sprite;
import Logica.*;
import Plataforma.Vacio;
import Vista.*;
import Colisionador.ColisionadorVisitorMario;
import Threads.TemporizadorEstadoEstrella;

public class Mario extends Entidad implements EntidadMario,EntidadMovimientoMario{

	protected int vidas;
	protected int monedas;
	protected boolean cayendoVacio, enSuelo,saltando, esColisionable,
	puedeDerecha,puedeIzquierda,cambioEstado, moverDerecha, moverIzquierda;
	protected EstadoMario estado,anterior;
	protected char sentidoMovimientoMario;
	protected double progreso;
	protected int posInicialSalto,velocidadSalto,limiteDerecha, limiteIzquierda; 
	private Juego juego;
	protected String nombre;
	protected int puntaje;
	protected Sprite sprites[], spritesBolaFuego[];
	protected int VELDERECHA = 5;
	protected int VELIZQUIERDA = -5;
	protected int contSalto;

	public Mario(int x, int y, int velocidad, Juego juego, Sprite[] sprites, Sprite[] spritesBolaFuego) {
		super(x,y, velocidad,20,20);
		estado = new EstadoNormal();
		anterior = estado;
		vidas = 3;
		enSuelo = true;
		esColisionable = true;
		velocidadSalto = 30; 
		this.velocidad = velocidad;
		puedeDerecha = true;
		puedeIzquierda = true;
		puntaje = 0;
		cambioEstado = false;
		cayendoVacio = false;
		posInicialSalto = getPosicionY();
		progreso = 0;
		saltando = false;
		limiteDerecha = 5000;
		limiteIzquierda = 0;
		this.juego = juego;
		this.sprites = sprites;
		this.spritesBolaFuego = spritesBolaFuego;
		moverDerecha = false;
		moverIzquierda = false;
		contSalto=0;
	}
	
	public void activarDerecha() {
		moverDerecha = true;
		moverIzquierda = false;
	}

	public void activarIzquierda() {
		moverDerecha = false;
		moverIzquierda = true;
	}

	public void desactivarDerecha() {
		moverDerecha = false;
	}

	public void desactivarIzquierda() {
		moverIzquierda = false;
	}

	public void actualizarMovimientoMario(char direccion) {
		int suelo=60;
		int desplazamientoX = 0;
		int desplazamientoY = 0;

		if(puedeDerecha && moverDerecha) {
			desplazamientoX += 50;
			puedeIzquierda = true;
			sentidoMovimientoMario = DERECHA;
			posicionX += 5;
			actualizarAreaColision();
		}
		
		if( puedeIzquierda && moverIzquierda) {
			desplazamientoX -= 50;
			puedeDerecha = true;
			if(posicionX>=40) {
				sentidoMovimientoMario = IZQUIERDA;
				posicionX -= 5;
				actualizarAreaColision();
			}
		}
		
		if(direccion == SALTO) {
			desplazamientoY -= velocidadSalto;
		}
		
		if(direccion == QUIETO) {
			detenerMario();
		}

		if (saltando) {	
			contSalto++;
			setPosicionY(getPosicionY()+20-contSalto);
			actualizarAreaColision();
			if(contSalto>20) {
				saltando=false;
				enSuelo=false;
			}
		} 
		else if(!enSuelo){
			//codigo caida
			setPosicionY(getPosicionY()-20+contSalto);
			actualizarAreaColision();
			contSalto--;
			if(getPosicionY()<=suelo && !cayendoVacio) {
				setPosicionY(suelo);
				actualizarAreaColision();

				limiteDerecha=5000;
				limiteIzquierda=0;
				enSuelo=true;
			}
		} 

		if(getPosicionX()>limiteDerecha || getPosicionX()<limiteIzquierda ){
			posInicialSalto = getPosicionY();
			enSuelo=false;

		}

		if(desplazamientoX!= 0 || desplazamientoY!=0) {
			actualizarSprite(direccion);			
			direccionMario(desplazamientoX, desplazamientoY);
		}

		observador.actualizarPosicionTamano();

	}

	public void saltar() {
		if(progreso == 0 && enSuelo){
			enSuelo=false;
			contSalto=0;
			Sonido.obtenerInstancia().reproducirSonidoSaltar();
			saltando = true;
			posInicialSalto = getPosicionY();
			sentidoMovimientoMario = SALTO;
		}
	}

	public void detenerSalto(){
		saltando = false;
	}

	public void rebotarHaciaAbajo(){
		enSuelo=false;
		saltando=false;
		puedeIzquierda=true;
		puedeDerecha=true;
	}

	public void direccionMario(int desplazamientoX, int desplazamientoY) {
		if(desplazamientoX > 0) {
			direccionMovimiento = DERECHA;
			direccionMovimientoAnterior = direccionMovimiento;
		}
		
		if(desplazamientoX<0) {
			direccionMovimiento = IZQUIERDA;
			direccionMovimientoAnterior = direccionMovimiento;

		}
		
		if(desplazamientoY < 0) {
			direccionMovimiento = SALTO;
			direccionMovimientoAnterior = direccionMovimiento;
		}
	}

	public void detenerMario() {
		if(direccionMovimiento == DERECHA)
			sprite = estado.getSpriteDetenidoDerecha(sprites);
		else 
			if(direccionMovimiento == IZQUIERDA)
				sprite = estado.getSpriteDetenidoIzquierda(sprites);
		direccionMovimiento= QUIETO;
		velocidad = 0;
		notificarObservador();
	}

	public Sprite actualizarSprite(char direccion) {
		Sprite aux = sprite;
		if (saltando || !enSuelo) {
			if (direccion == DERECHA || direccionMovimiento == DERECHA) 
				sprite = estado.getSpriteSaltandoADerecha(sprites);
			else 
				if (direccion == IZQUIERDA || direccionMovimiento == IZQUIERDA) 
					sprite = estado.getSpriteSaltandoAIzquierda(sprites);
		}
		else {
			if (direccion == DERECHA) 
				sprite = estado.getSpriteDerecha(sprites);
			else 
				if (direccion == IZQUIERDA) 
					sprite = estado.getSpriteIzquierda(sprites);
				else 
					if (direccion == QUIETO) {
						if (direccionMovimientoAnterior == DERECHA) 
							sprite = estado.getSpriteDetenidoDerecha(sprites);
						else 
							if (direccionMovimientoAnterior == IZQUIERDA) 
								sprite = estado.getSpriteDetenidoIzquierda(sprites);
					}
		}
		if(aux != sprite) {
			notificarObservador();
		}
		return sprite;
	}
	
	public void morirPorVacio(Vacio vacio) {
		if(esColisionable) {
		Sonido.obtenerInstancia().reproducirSonidoCaeAlVacio();
		enSuelo=false;
		cayendoVacio=true;
		setCambioEstado(true);
		vacio.interactuar(this, juego);
		}
	}

	public void lanzarBolaFuego() {
		if(estado.lanzarBolaFuego()) {
			Sprite spriteBola = spritesBolaFuego[0];
			BolaFuego bolaFuego = new BolaFuego(posicionX + 45, posicionY + 15,5,spriteBola,sentidoMovimientoMario,direccionMovimientoAnterior);
			juego.registrarObserverProyectiles(bolaFuego);
			juego.getNivelActual().agregarProyectil(bolaFuego);
			bolaFuego.setEnMovimiento(true);
		}
	}

	public void decrementarVida() {
		if(esColisionable) {
			Sonido.obtenerInstancia().reproducirSonidoMorir();
			vidas--;
			juego.actualizarVidas();
			if(vidas==0) {
				juego.finalizarPartida();
			}
		}
	}
	
	public void setEstadoInicial() {
		super.setEstadoInicial();
		estado = new EstadoNormal();
		actualizarSprite(direccionMovimiento);
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
		juego.actualizarVidas();
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {

	}

	public void setMarioEstadoNivel() {
		sprite = estado.getSpriteDetenidoDerecha(sprites);
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

	public void setLimiteDerecha(int i) {
		limiteDerecha = i;
	}

	public void setLimiteIzquierda(int i) {
		limiteIzquierda = i;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
		
		if(this.puntaje < 0) {
			this.puntaje = 0;
		}
		
		juego.actualizarPuntaje();
	}

	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	public void setColisionable(boolean colisionable) {
		esColisionable = colisionable;
		notificarObservador();
	}

	public boolean colisionable() {
		return esColisionable;
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

	public Sprite[] getSpritesMario() {
		return sprites;
	}

	public boolean getSaltando() {
		return saltando;
	}

	public int getVelocidadSalto() {
		return velocidadSalto;
	}

	public boolean getEnSuelo() {
		return enSuelo;
	}

	public int getMonedas() {
		return monedas;
	}

	public Juego getJuego() {
		return juego;
	}

	public char getDireccionMovimiento() {
		return direccionMovimiento;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public String getNombre() {
		return nombre;
	}
}