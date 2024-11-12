package Vista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Mario.*;
public class Ranking {
	private static Ranking instancia;
	protected List<Puntuacion> ranking;
	
	private Ranking() {
		leerPuntajes();
		ordenarPuntajes();
	}
	
	public static Ranking getInstancia() {
		if(instancia==null)
			instancia=new Ranking();
		return instancia;
	}
	
	public List<Puntuacion> getRanking(){
		return ranking;
	}
	
	private void leerPuntajes() {
		 String rutaArchivo = "src/Parser/Ranking.txt";

		    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
		        String linea;
		        int numeroLinea = 0;
		        while ((linea = br.readLine()) != null) {
		            String[] partes = linea.split(" ");
		            if (partes.length < 2) {
		                continue;
		            }
		            String nombre = partes[0];
		            int puntaje = Integer.parseInt(partes[1]);
		            ranking.add(numeroLinea, new Puntuacion(nombre,puntaje));
		            numeroLinea++;
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    } catch (NumberFormatException e) {
		        System.err.println("Error al convertir puntaje: " + e.getMessage());
		    }
	}
	
	private void ordenarPuntajes() {
	    for (int i = 0; i < ranking.size() - 1; i++) {
	        for (int j = 0; j < ranking.size() - 1 - i; j++) {
	            if (ranking.get(j).getPuntaje() < ranking.get(j + 1).getPuntaje()) {
	                Puntuacion temporal = ranking.get(j);
	                ranking.set(j, ranking.get(j + 1));
	                ranking.set(j + 1, temporal);
	            }
	        }
	    obtenerMejoresPuntajes();
	}
	}
	
	private void obtenerMejoresPuntajes() {
		List<Puntuacion> mejores = new ArrayList<>();
       for (int i = 0; i < Math.min(5, ranking.size()); i++) {
           mejores.add(ranking.get(i));
       }
       ranking = mejores;
	}
	
	public void guardarPuntajes() {
	    String rutaArchivo = "src/Parser/Ranking.txt";
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
	        for (Puntuacion jugador : ranking) {
	            bw.write(jugador.getNombre() + " " + jugador.getPuntaje());
	            bw.newLine();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public void agregarJugador(String nombre,int puntaje) {
		Puntuacion nuevoJugador=new Puntuacion(nombre, puntaje);
		boolean jugadorExiste = false;
	    for (Puntuacion jugador : ranking) {
	        if (nuevoJugador.getNombre().equals(jugador.getNombre())) {
	            jugadorExiste = true;
	            if (jugador.getPuntaje() > nuevoJugador.getPuntaje()) {
	                nuevoJugador.setPuntaje(jugador.getPuntaje());
	            }
	            break;
	        }
	    }
	    if (!jugadorExiste) {
	        ranking.add(nuevoJugador);
	    }
	    ordenarPuntajes(); 
	}
	
}
