package Fabrica;
import Enemigo.*;
import Mario.*;
import Logica.*;
import PowerUp.*;
import Plataforma.*;

public class FabricaEntidades{

	protected FabricaSprite fabricaSprites;

	public FabricaEntidades(FabricaSprite fabricaSprites) {
		this.fabricaSprites = fabricaSprites;
	}
	
	public FabricaSprite getFabricaSprite() {
		return fabricaSprites;
	}
	
	public BuzzyBeetle getBuzzyBeetle(int x, int y, int velocidad, Sprite[] sprites) {
		BuzzyBeetle buzzy = new BuzzyBeetle(x, y, velocidad, sprite);
		buzzy.setSprite(sprites[0]);
		return buzzy;
	}
	
	public KoopaTroopa getKoopa(int x, int y, int velocidad, Sprite[] sprites) {
		KoopaTroopa koopa = new KoopaTroopa(x, y, velocidad, sprite);
		koopa.setSprite(sprites[0]);
		return koopa;
	}
	
	public Goomba getGoomba(int x, int y, int velocidad, Sprite[] sprites) {
		Goomba goomba = new Goomba(x, y, velocidad, sprite);
		goomba.setSprite(sprites[0]);
		return goomba;
	}
	
	public Spiny getSpinny(int x, int y, int velocidad, Sprite[] sprites) {
		Spiny spiny = new Spiny(x,y, velocidad, sprite);
		spiny.setSprite(sprites[0]);
		return spiny;
	}
	
	public PiranhaPlant getPiranha(int x, int y, int velocidad, Sprite[] sprites) {
		PiranhaPlant piranha = new PiranhaPlant(x, y, velocidad, sprite);
		piranha.setSprite(sprites[0]);
		return piranha;
	}
	
	public Lakitu getLakitu(int x, int y, int velocidad, Sprite[] sprites,Juego juego) {
		Lakitu lakitu = new Lakitu(x, y, velocidad, sprite,juego);
		lakitu.setSprite(sprites[0]);
		return lakitu;
	}
	
	public Estrella getEstrella(int x, int y, int velocidad, Sprite[] sprites) {
		Estrella estrella = new Estrella(x, y, velocidad, sprite);
		estrella.setSprite(sprites[0]);
		return estrella;
	}
	
	public Moneda getMoneda(int x, int y, int velocidad, Sprite[] sprites) {
		Moneda moneda = new Moneda(x, y, velocidad, sprite);
		moneda.setSprite(sprites[0]);
		return moneda;
	}
	
	public FlorFuego getFlor(int x, int y, int velocidad, Sprite[] sprites) {
		FlorFuego florFuego = new FlorFuego(x, y, velocidad, sprite);
		florFuego.setSprite(sprites[0]);
		return florFuego;
	}
	
	public SuperChampinon getSuperChampinon(int x, int y, int velocidad, Sprite[] sprites) {
		SuperChampinon superChamp = new SuperChampinon(x, y, velocidad, sprite);
		superChamp.setSprite(sprites[0]);
		return superChamp;
	}
	
	public ChampinonVerde getChampinonVerde(int x, int y, int velocidad, Sprite[] sprites) {
		ChampinonVerde champVerde = new ChampinonVerde(x, y, velocidad, sprite);
		champVerde.setSprite(sprites[0]);
		return champVerde;
	}
	
	public Tuberia getTuberia(int x, int y, int velocidad, Sprite[] sprites) {
		Tuberia tuberia = new Tuberia(x, y, velocidad, sprite);
		tuberia.setSprite(sprites[0]);
		return tuberia;
	}
	
	public TuberiaConPiranha getTuberiaPiranha(int x, int y, int velocidad, Sprite[] sprites, PiranhaPlant piranhaPlant) {
		TuberiaConPiranha tuberia = new TuberiaConPiranha(x, y, velocidad, sprite, piranhaPlant);
		tuberia.setSprite(sprites[0]);
		return tuberia;
	}
	
	public Vacio getVacio(int x, int y, int velocidad, Sprite[] sprites) {
		Vacio vacio = new Vacio(x, y, velocidad, sprite);
		vacio.setSprite(sprites[0]);
		return vacio;
	}
	public Princesa getPrincesa(int x, int y, int velocidad, Sprite[] sprites) {
		Princesa princesa=new Princesa(x,y,velocidad,sprite);
		princesa.setSprite(sprites[0]);
		return princesa;
	}
	public Bandera getBandera(int x, int y, int velocidad, Sprite[] sprites) {
		Bandera bandera=new Bandera(x,y,velocidad,sprite);
		bandera.setSprite(sprites[0]);
		return bandera;
	}
	public LadrilloSolido getLadrilloSolido(int x, int y, int velocidad, Sprite[] sprites) {
		LadrilloSolido ladrillo = new LadrilloSolido(x, y, velocidad, sprite);
		ladrillo.setSprite(sprites[0]);
		return ladrillo;
	}
	
	public BloqueSolido getBloqueSolido(int x, int y, int velocidad, Sprite[] sprites) {
		BloqueSolido bloqueSolido = new BloqueSolido (x, y, velocidad, sprite);
		bloqueSolido.setSprite(sprites[0]);
		return bloqueSolido;
	}
	
	public BloquePregunta getBloquePregunta(int x, int y, PowerUp powerUp, int velocidad, Sprite[] sprites) {
		BloquePregunta bloquePregunta = new BloquePregunta( x, y, powerUp, velocidad, sprite);
		bloquePregunta.setSprite(sprites[0]);
		return bloquePregunta;
	}
	
	public Mario getMario(int x, int y, int velocidad, Sprite[] sprites, Juego juego) {
		Mario mario = new Mario(x, y, velocidad, sprite, juego);
		mario.setSprite(sprites[0]);
		return mario;
	}
}