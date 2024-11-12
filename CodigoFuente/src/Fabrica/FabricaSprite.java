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
	
	public Sprite[] getMario() {
		Sprite[] spritesMario = new Sprite[15];
		spritesMario[0] = new Sprite(rutaImagen + "/Marioquieto.png");
		spritesMario[1] = new Sprite(rutaImagen + "/Marioquietoizq.png");
		spritesMario[2] = new Sprite(rutaImagen + "/Mariocaminando.png");
		spritesMario[3] = new Sprite(rutaImagen + "/Mariocaminandoizq.png");
		spritesMario[4] = new Sprite(rutaImagen + "/MarioJumping.png");
		spritesMario[5] = new Sprite(rutaImagen + "/MarioJumpingizq.png");
		spritesMario[6] = new Sprite(rutaImagen + "/Supermario-quieto.png");
		spritesMario[7] = new Sprite(rutaImagen + "/SuperMario-quietoizq.png");
		spritesMario[8] = new Sprite(rutaImagen + "/SuperMario-caminandoderecha.gif");
		spritesMario[9] = new Sprite(rutaImagen + "/SuperMario-caminandoizquierda.gif");
		spritesMario[10] = new Sprite(rutaImagen + "/SuperMario-saltando.png");
		spritesMario[11] = new Sprite(rutaImagen + "/SuperMario-saltandoizq.png");
		spritesMario[12] = new Sprite(rutaImagen + "/Mario-invencible.gif");
		spritesMario[13] = new Sprite(rutaImagen + "/Mario-invencible-aziq.gif");
		spritesMario[14] = new Sprite(rutaImagen + "/Invencible-caminandoderecha.gif");
		spritesMario[15] = new Sprite(rutaImagen + "/Invencible-caminandoizquierda.gif");
		spritesMario[16] = new Sprite(rutaImagen + "/Invencible-saltanddero.gif");
		spritesMario[17] = new Sprite(rutaImagen + "/Invencible-saltandoizq.gif");
		spritesMario[18] = new Sprite(rutaImagen + "/Marioflor-quieto.png");
		spritesMario[19] = new Sprite(rutaImagen + "/Marioflor-quietoizq.png");
		spritesMario[20] = new Sprite(rutaImagen + "/Marioflor-caminandoderecha.gif");
		spritesMario[21] = new Sprite(rutaImagen + "/Marioflor-caminandoizquierda.gif");
		spritesMario[22] = new Sprite(rutaImagen + "/Marioflor-saltando.png");
		spritesMario[23] = new Sprite(rutaImagen + "/Marioflor-saltandoaizq.png");
		
		return spritesMario;
	}
	
	public Sprite[] getKoopa() {
		Sprite[] spritesKoopa = new Sprite[2];
		spritesKoopa[0] = new Sprite(rutaImagen + "/Koopagreen.gif");
		spritesKoopa[1] = new Sprite(rutaImagen + "KoopaTroopaProyectil.png");
		return spritesKoopa;
	}
	
	public Sprite[] getGoomba() {
		Sprite[] spritesGoomba = new Sprite[1];
		spritesGoomba[0] = new Sprite(rutaImagen+"/Goomba.gif");
		return spritesGoomba;
	}
	
	public Sprite[] getSpiny() {
		Sprite[] spritesSpiny = new Sprite[1];
		spritesSpiny[0] = new  Sprite(rutaImagen+"/Spiny.gif");
		return spritesSpiny;
	}
	
	public Sprite[] getPiranha() {
		Sprite[] spritesPiranha = new Sprite[1];
		spritesPiranha[0] = new  Sprite(rutaImagen+"/PiranhaPlant.gif");
		return spritesPiranha;
	}
	
	public Sprite[] getLakitu() {
		Sprite[] spritesLakitu = new Sprite[1];
		spritesLakitu[0] = new Sprite(rutaImagen+"/Lakitu.gif");
		return spritesLakitu;
	}
	
	public Sprite[] getEstrella() {
		Sprite[] spritesEstrella = new Sprite[1];
		spritesEstrella[0] = new Sprite(rutaImagen+"/Starman.gif");
		return spritesEstrella;
	}
	
	public Sprite[] getBuzzyBeetle() {
		Sprite[] spritesBuzzyBeetle = new Sprite[1];
		spritesBuzzyBeetle[0] = new Sprite(rutaImagen+"/BuzzyBeetle.gif");
		return spritesBuzzyBeetle;
	}
	
	public Sprite[] getMoneda() {
		Sprite[] spritesMoneda = new Sprite[1];
		spritesMoneda[0] = new Sprite(rutaImagen+"/Coin.gif");
		return spritesMoneda;
	}
	
	public Sprite[] getFlor() {
		Sprite[] spritesFlor = new Sprite[1];
		spritesFlor[0] = new Sprite(rutaImagen+"/FireFlower.gif");
		return spritesFlor;
	}
	
	public Sprite[] getSuperChampinon() {
		Sprite[] spritesSuperChampinon = new Sprite[1];
		spritesSuperChampinon[0] = new Sprite(rutaImagen+"/SuperChampinon.png");	
		return spritesSuperChampinon;
	}
	
	public Sprite[] getChampinonVerde() {
		Sprite[] spritesChampinonVerde = new Sprite[1];
		spritesChampinonVerde[0] = new Sprite(rutaImagen+"/ChampinonVerde.png");
		return spritesChampinonVerde;
	}
	
	public Sprite[] getTuberia() {
		Sprite[] spritesTuberia = new Sprite[1];
		spritesTuberia[0] =  new Sprite(rutaImagen+"/tuberia.png");
		return spritesTuberia;
	}
	
	public Sprite[] getTuberiaPiranha() {
		Sprite[] spritesTuberiaPiranha = new Sprite[1];
		spritesTuberiaPiranha[0] =  new Sprite(rutaImagen+"/tuberia.png");
		return spritesTuberiaPiranha;
	}
	
	public Sprite[] getVacio() {
		Sprite[] spritesVacio = new Sprite[1];
		spritesVacio[0] = new Sprite(rutaImagen+"/vacio.png");	
		return spritesVacio;
	}
	
	public Sprite[] getLadrilloSolido() {
		Sprite[] spritesLadrilloSolido = new Sprite[1];
		spritesLadrilloSolido[0] = new Sprite(rutaImagen+"/LadrilloSolido.png");
		return spritesLadrilloSolido;
	}
	
	public Sprite[] getBloqueSolido() {
		Sprite[] spritesBloqueSolido = new Sprite[1];
		spritesBloqueSolido[0] = new Sprite(rutaImagen+"/BloqueSolido.png");
		return spritesBloqueSolido;
	}
	
	public Sprite[] getBloquePregunta() {
		Sprite[] spritesBloquePregunta = new Sprite[1];
		spritesBloquePregunta[0] = new Sprite(rutaImagen+"/QuestionBlock.gif");
		return spritesBloquePregunta;
	}
	public Sprite[] getPrincesa() {
		Sprite[] spritesPrincesa = new Sprite[1];
		spritesPrincesa[0] = new Sprite(rutaImagen+"/Princesa.png");
		return spritesPrincesa;
	}
	public Sprite[] getBandera() {
		Sprite[] spritesBandera = new Sprite[1];
		spritesBandera[0] = new Sprite(rutaImagen+"/Bandera.png");
		return spritesBandera;
	}
}
