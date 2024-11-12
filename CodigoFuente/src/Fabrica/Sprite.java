package Fabrica;

public class Sprite {
	
	protected String rutaimagen;
	protected String modo; 
	
	public Sprite (String rutaimagen, String modo) {
		this.rutaimagen = rutaimagen;
		this.modo = modo;
	}
	
	public String getRutaImagen() {
		return rutaimagen;
	}
	
	public String getModo() {
		return modo;
	}
}
