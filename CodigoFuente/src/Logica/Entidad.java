package Logica;

import Sonido.*;
import java.awt.Rectangle;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.*;
import Vista.*;

public abstract class Entidad implements EntidadLogica{

	protected int posicionY, posicionX, velocidad, alto, ancho;
	protected Sprite sprite;
	protected Observer observador;
	protected Sonido sonido;
	protected boolean  esColisionable;
	protected Rectangle areaColision; 

	public Entidad(int x, int y, int velocidad, int anchoHitBox, int altoHitBox) {
		posicionX = x;
		posicionY = y;
		this.velocidad = velocidad;
		alto = altoHitBox;
		ancho = anchoHitBox;
		areaColision = new Rectangle(getPosicionX(),getPosicionY(), anchoHitBox, altoHitBox);
		esColisionable = true;
	}

	public void notificarObservador() {
		observador.actualizar();
	}

	public void actualizarAreaColision() {
		areaColision.setBounds(posicionX, posicionY, ancho, alto);
	}

	public abstract void aceptarVisita(ColisionadorVisitorMario visitor);

	public abstract void retirar();

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setSonido(Sonido sonido) {
		this.sonido = sonido;
	}

	public void setObserver(Observer observador) {
		this.observador = observador;
	}

	public void setPosicionX(int x) {
		posicionX = x;
	}

	public void setPosicionY(int y) {
		posicionY = y;
	}

	public void setColisionable(boolean colisionable) {
		esColisionable = colisionable;
	}

	public boolean colisionable() {
		return esColisionable;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public Sonido getSonido() {
		return sonido;
	}

	public Observer getObserver() {
		return observador;
	}

	public Rectangle getAreaColision() {
		return areaColision;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public int getVelocidad() {
		return velocidad;
	}

}