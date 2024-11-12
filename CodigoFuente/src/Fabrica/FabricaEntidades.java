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
	
	public BuzzyBeetle getBuzzyBeetle(int x, int y, int velocidad, Sprite sprite) {
		BuzzyBeetle buzzy = new BuzzyBeetle(x, y, velocidad, sprite);
		buzzy.setSprite(fabricaSprites.getBuzzyBeetle());
		return buzzy;
	}
	
	public KoopaTroopa getKoopa(int x, int y, int velocidad, Sprite sprite) {
		KoopaTroopa koopa = new KoopaTroopa(x, y, velocidad, sprite);
		koopa.setSprite(fabricaSprites.getKoopa());
		return koopa;
	}
	
	public Goomba getGoomba(int x, int y, int velocidad, Sprite sprite) {
		Goomba goomba = new Goomba(x, y, velocidad, sprite);
		goomba.setSprite(fabricaSprites.getGoomba());
		return goomba;
	}
	
	public Spiny getSpinny(int x, int y, int velocidad, Sprite sprite) {
		Spiny spiny = new Spiny(x,y, velocidad, sprite);
		spiny.setSprite(fabricaSprites.getSpinny());
		return spiny;
	}
	
	public PiranhaPlant getPiranha(int x, int y, int velocidad, Sprite sprite) {
		PiranhaPlant piranha = new PiranhaPlant(x, y, velocidad, sprite);
		piranha.setSprite(fabricaSprites.getPiranha());
		return piranha;
	}
	
	public Lakitu getLakitu(int x, int y, int velocidad, Sprite sprite,Juego juego) {
		Lakitu lakitu = new Lakitu(x, y, velocidad, sprite,juego);
		lakitu.setSprite(fabricaSprites.getLakitu());
		return lakitu;
	}
	
	public Estrella getEstrella(int x, int y, int velocidad, Sprite sprite) {
		Estrella estrella = new Estrella(x, y, velocidad, sprite);
		estrella.setSprite(fabricaSprites.getEstrella());
		return estrella;
	}
	
	public Moneda getMoneda(int x, int y, int velocidad, Sprite sprite) {
		Moneda moneda = new Moneda(x, y, velocidad, sprite);
		moneda.setSprite(fabricaSprites.getMoneda());
		return moneda;
	}
	
	public FlorFuego getFlor(int x, int y, int velocidad, Sprite sprite) {
		FlorFuego florFuego = new FlorFuego(x, y, velocidad, sprite);
		florFuego.setSprite(fabricaSprites.getFlor());
		return florFuego;
	}
	
	public SuperChampinon getSuperChampinon(int x, int y, int velocidad, Sprite sprite) {
		SuperChampinon superChamp = new SuperChampinon(x, y, velocidad, sprite);
		superChamp.setSprite(fabricaSprites.getSuperChampinon());
		return superChamp;
	}
	
	public ChampinonVerde getChampinonVerde(int x, int y, int velocidad, Sprite sprite) {
		ChampinonVerde champVerde = new ChampinonVerde(x, y, velocidad, sprite);
		champVerde.setSprite(fabricaSprites.getChampinonVerde());
		return champVerde;
	}
	
	public Tuberia getTuberia(int x, int y, int velocidad, Sprite sprite) {
		Tuberia tuberia = new Tuberia(x, y, velocidad, sprite);
		tuberia.setSprite(fabricaSprites.getTuberia());
		return tuberia;
	}
	
	public TuberiaConPiranha getTuberiaPiranha(int x, int y, int velocidad, Sprite sprite, PiranhaPlant piranhaPlant) {
		TuberiaConPiranha tuberia = new TuberiaConPiranha(x, y, velocidad, sprite, piranhaPlant);
		tuberia.setSprite(fabricaSprites.getTuberiaPiranha());
		return tuberia;
	}
	
	public Vacio getVacio(int x, int y, int velocidad, Sprite sprite) {
		Vacio vacio = new Vacio(x, y, velocidad, sprite);
		vacio.setSprite(fabricaSprites.getVacio());
		return vacio;
	}
	public Princesa getPrincesa(int x, int y, int velocidad, Sprite sprite) {
		Princesa princesa=new Princesa(x,y,velocidad,sprite);
		princesa.setSprite(fabricaSprites.getPrincesa());
		return princesa;
	}
	public Bandera getBandera(int x, int y, int velocidad, Sprite sprite) {
		Bandera bandera=new Bandera(x,y,velocidad,sprite);
		bandera.setSprite(fabricaSprites.getBandera());
		return bandera;
	}
	public LadrilloSolido getLadrilloSolido(int x, int y, int velocidad, Sprite sprite) {
		LadrilloSolido ladrillo = new LadrilloSolido(x, y, velocidad, sprite);
		ladrillo.setSprite(fabricaSprites.getLadrilloSolido());
		return ladrillo;
	}
	
	public BloqueSolido getBloqueSolido(int x, int y, int velocidad, Sprite sprite) {
		BloqueSolido bloqueSolido = new BloqueSolido (x, y, velocidad, sprite);
		bloqueSolido.setSprite(fabricaSprites.getBloqueSolido());
		return bloqueSolido;
	}
	
	public BloquePregunta getBloquePregunta(int x, int y, PowerUp powerUp, int velocidad, Sprite sprite) {
		BloquePregunta bloquePregunta = new BloquePregunta( x, y, powerUp, velocidad, sprite);
		bloquePregunta.setSprite(fabricaSprites.getBloquePregunta());
		return bloquePregunta;
	}
	
	public Mario getMario(int x, int y, int velocidad, Sprite sprite, Juego juego) {
		Mario mario = new Mario(x, y, velocidad, sprite, juego);
		mario.setSprite(fabricaSprites.getMario());
		return mario;
	}
}