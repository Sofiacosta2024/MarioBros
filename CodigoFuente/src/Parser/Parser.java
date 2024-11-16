package Parser;

import Logica.Juego;
import Logica.Nivel;
import Enemigo.*;
import Fabrica.FabricaEntidades;
import PowerUp.*;
import Fabrica.FabricaSprite;

//imports para la lectura de archivos
import java.nio.file.*;
import java.io.IOException;
import java.util.List;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Parser {

	protected Nivel nivel;
	private static Parser parser;
	protected FabricaEntidades fabricaEntidad;
	private Juego juego;

	private Parser(Juego juego){
		this.juego = juego;
		nivel = new Nivel(juego.getNumeroDeNivel());
	}

	public static Parser getInstancia(Juego juego) {
		if (parser == null)
				parser = new Parser(juego);
		return parser;
	}

	public void parsearNivel(Nivel nivel) {
		Path ruta = null;		
		String[] columnas = null;
		List<String> lineas = null;
		this.nivel=nivel;
		try {       	        
			ruta = getRutaArchivo("matrizNivel"+nivel.getNivel()+".txt");
			lineas = Files.readAllLines(ruta);
			for(String linea : lineas) {
				if (linea.trim().isEmpty()) {
					continue; 
				}
				columnas = linea.split(",");
				generarEnNivel(columnas);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public Path getRutaArchivo(String nombreArchivo) {
		String rutaCarpeta = null;
		URL url = null;
		File file = null;
		Path ruta = null;
		try {
			url = getClass().getResource(getClass().getSimpleName() + ".class");
			file = new File(url.toURI());
			rutaCarpeta = file.getParent();
			ruta = Paths.get(rutaCarpeta).resolve(nombreArchivo);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return ruta;
	}

	private void generarEnNivel(String[] columna) {
		FabricaSprite fSprite = fabricaEntidad.getFabricaSprite();
		int velocidadNula = 0;
		int velocidadNuevaEntidad = 0;
		if(columna[3] != null)
			velocidadNuevaEntidad = Integer.parseInt(columna[3]);
		int posicionX = Integer.parseInt(columna[1]);
		int posicionY = Integer.parseInt(columna[2]);
		switch(columna[0]) {
		case "1":
			nivel.agregarMario(fabricaEntidad.getMario(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getMario(),juego));   
			break;
		case "20":
			nivel.agregarEnemigo(fabricaEntidad.getKoopa(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getKoopa()));
			break;
		case "21":
			nivel.agregarEnemigo(fabricaEntidad.getGoomba(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getGoomba()));
			break;
		case "22":
			nivel.agregarEnemigo(fabricaEntidad.getPiranha(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getPiranha()));
			break;
		case "23":
			nivel.agregarEnemigo(fabricaEntidad.getLakitu(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getLakitu(),juego));
			break;
		case "24":
			nivel.agregarEnemigo(fabricaEntidad.getSpiny(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getSpiny()));  
			break;
		case "25":
			nivel.agregarEnemigo(fabricaEntidad.getBuzzyBeetle(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getBuzzyBeetle()));    
			break;
		case "30":
			nivel.agregarPowerUp(fabricaEntidad.getSuperChampinon(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getSuperChampinon()));
			break;
		case "31":
			nivel.agregarPowerUp(fabricaEntidad.getFlor(posicionX, posicionY, velocidadNula, fSprite.getFlor()));
			break;
		case "32":
			nivel.agregarPowerUp(fabricaEntidad.getEstrella(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getSuperChampinon()));
			break;
		case "33":
			nivel.agregarPowerUp(fabricaEntidad.getChampinonVerde(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getChampinonVerde()));
			break;
		case "34":
			nivel.agregarPowerUp(fabricaEntidad.getMoneda(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getMoneda()));
			break;
		case "40":
			nivel.agregarPlataforma(fabricaEntidad.getBloqueSolido(posicionX, posicionY, velocidadNula, fSprite.getBloqueSolido()));
			break;
		case "41":
			nivel.agregarPlataforma(fabricaEntidad.getLadrilloSolido(posicionX, posicionY, velocidadNula, fSprite.getLadrilloSolido()));
			break;
		case "420":{ 
			PowerUp powerUpContenido = fabricaEntidad.getSuperChampinon(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getSuperChampinon());
			nivel.agregarPlataforma(fabricaEntidad.getBloquePregunta(posicionX, posicionY, powerUpContenido, velocidadNula, fSprite.getBloquePregunta()));
			nivel.agregarPowerUp(powerUpContenido);		
			break;
		}
		case "421":{
			PowerUp powerUpContenido = fabricaEntidad.getFlor(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getFlor());
			nivel.agregarPlataforma(fabricaEntidad.getBloquePregunta(posicionX, posicionY, powerUpContenido, velocidadNula, fSprite.getBloquePregunta()));
			nivel.agregarPowerUp(powerUpContenido);		
			break;
		}
		case "422":{
			PowerUp powerUpContenido = fabricaEntidad.getChampinonVerde(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getChampinonVerde());
			nivel.agregarPlataforma(fabricaEntidad.getBloquePregunta(posicionX, posicionY, powerUpContenido, velocidadNula, fSprite.getBloquePregunta()));
			nivel.agregarPowerUp(powerUpContenido);		
			break;
		}
		case "423":{
			PowerUp powerUpContenido = fabricaEntidad.getEstrella(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getEstrella());
			nivel.agregarPlataforma(fabricaEntidad.getBloquePregunta(posicionX, posicionY, powerUpContenido, velocidadNula, fSprite.getBloquePregunta()));
			nivel.agregarPowerUp(powerUpContenido);		
			break;
		}
		case "424":{
			PowerUp powerUpContenido = fabricaEntidad.getMoneda(posicionX, posicionY, velocidadNuevaEntidad, fSprite.getMoneda());
			nivel.agregarPlataforma(fabricaEntidad.getBloquePregunta(posicionX, posicionY, powerUpContenido, velocidadNula, fSprite.getBloquePregunta()));
			nivel.agregarPowerUp(powerUpContenido);			
			break;
		}
		case "43":
			nivel.agregarPlataforma(fabricaEntidad.getTuberia(posicionX, posicionY, velocidadNula, fSprite.getTuberia()));
			break;
		case "44":{
			PiranhaPlant piranhaPlant = fabricaEntidad.getPiranha(posicionX, posicionY + 40, velocidadNuevaEntidad, fSprite.getPiranha());
			nivel.agregarPlataforma(fabricaEntidad.getTuberiaPiranha(posicionX, posicionY, velocidadNula, fSprite.getTuberiaPiranha(), piranhaPlant));
			nivel.agregarEnemigo(piranhaPlant);
		}
		break;
		case "45":
			nivel.agregarPlataforma(fabricaEntidad.getVacio(posicionX, posicionY, velocidadNula, fSprite.getVacio()));
			break;
		case "46":
			nivel.agregarPlataforma(fabricaEntidad.getBandera(posicionX, posicionY, velocidadNula, fSprite.getBandera()));
			break;
		case "47":
			nivel.agregarPlataforma(fabricaEntidad.getPrincesa(posicionX, posicionY, velocidadNula, fSprite.getPrincesa()));
		}
	}
	
	public void setFabrica(FabricaEntidades fabrica) {
		this.fabricaEntidad = fabrica;
	}
}