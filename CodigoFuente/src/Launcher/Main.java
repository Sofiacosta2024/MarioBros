package Launcher;
import Logica.*;
import Vista.*;

public class Main {
		public static void main(String [] args) {
				Juego juego = new Juego();
				ControladorVistas controladorVistas = new ControladorVistas(juego);
				juego.setControladorVista(controladorVistas);
				controladorVistas.mostrarPantallaModoJuego();
		}
}
