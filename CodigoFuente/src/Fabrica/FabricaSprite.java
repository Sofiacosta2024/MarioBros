package Fabrica;

public abstract class FabricaSprite {
	
	protected String rutaImagen;
	protected String modo;
	
	public FabricaSprite(String rutaImagen, String modo) {
		this.rutaImagen=rutaImagen;
		this.modo = modo;
	}
	
	public void setModo(String modo) {
		this.rutaImagen = modo;
	}
	
	public Sprite getMario() {
		return new Sprite(rutaImagen+"/Marioquieto.png", modo);
	}
	
	public Sprite getSuperMario() {
		return new Sprite(rutaImagen+"/Supermario-quieto.png", modo);
	}
	
	public Sprite getMarioFlorFuego() {
		return new Sprite(rutaImagen+"/Marioflor.png", modo); 
	}
	
	public Sprite getMarioInvencible() {
		return new Sprite(rutaImagen+"/Mario-invencible.gif", modo);
	}
	
	public Sprite getKoopa() {
		return new Sprite(rutaImagen+"/Koopagreen.gif", modo);
	}
	
	public Sprite getGoomba() {
		return new Sprite(rutaImagen+"/Goomba.gif", modo);
	}
	
	public Sprite getSpinny() {
		return new Sprite(rutaImagen+"/Spiny.gif", modo);	
	}
	
	public Sprite getPiranha() {
		return new Sprite(rutaImagen+"/PiranhaPlant.gif", modo);
	}
	
	public Sprite getLakitu() {
		return new Sprite(rutaImagen+"/Lakitu.gif", modo);
	}
	
	public Sprite getEstrella() {
		return new Sprite(rutaImagen+"/Starman.gif", modo);
	}
	
	public Sprite getBuzzyBeetle() {
		return new Sprite(rutaImagen+"/BuzzyBeetle.gif", modo);
	}
	
	public Sprite getMoneda() {
		return new Sprite(rutaImagen+"/Coin.gif", modo);
	}
	
	public Sprite getFlor() {
		return new Sprite(rutaImagen+"/FireFlower.gif", modo);
	}
	
	public Sprite getSuperChampinon() {
		return new Sprite(rutaImagen+"/SuperChampinon.png", modo);	
	}
	
	public Sprite getChampinonVerde() {
		return new Sprite(rutaImagen+"/ChampinonVerde.png", modo);
	}
	
	public Sprite getTuberia() {
		return new Sprite(rutaImagen+"/tuberia.png", modo);
	}
	
	public Sprite getTuberiaPiranha() {
		return new Sprite(rutaImagen+"/tuberia.png", modo);
	}
	
	public Sprite getVacio() {
		return new Sprite(rutaImagen+"/vacio.png", modo);	
	}
	
	public Sprite getLadrilloSolido() {
		return new Sprite(rutaImagen+"/LadrilloSolido.png", modo);
	}
	
	public Sprite getBloqueSolido() {
		return new Sprite(rutaImagen+"/BloqueSolido.png", modo);
	}
	
	public Sprite getBloquePregunta() {
		return new Sprite(rutaImagen+"/QuestionBlock.gif", modo);
	}
	public Sprite getPrincesa() {
		return new Sprite(rutaImagen+"/Princesa.png", modo);
	}
	public Sprite getBandera() {
		return new Sprite(rutaImagen+"/Bandera.png",modo);
	}
}
