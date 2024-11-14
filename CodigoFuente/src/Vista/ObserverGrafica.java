package Vista;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Logica.EntidadLogica;

public abstract class ObserverGrafica extends JLabel implements Observer {
	private static final long serialVersionUID = 1L;
	protected EntidadLogica entidadObservada;

	public ObserverGrafica(EntidadLogica entidadObservada) {
		super();
		this.entidadObservada = entidadObservada;
	}

	public void actualizar() {
		actualizarImagen();
		actualizarPosicionTamano();
	}

	public void actualizarImagen() {
		if(!entidadObservada.colisionable()) {
			setVisible(false);
			return;
		}
		else {	
			String rutaImagen = entidadObservada.getSprite().getRutaImagen();
			URL url = getClass().getClassLoader().getResource(rutaImagen);
			if (url == null) {
				System.out.println("No se encontró la imagen: " + rutaImagen);
				return; 
			}
			ImageIcon iconoOriginal = new ImageIcon(url);
			int nuevoAncho = iconoOriginal.getIconWidth()*2; 
			int nuevoAlto = iconoOriginal.getIconHeight()*2;
			Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_DEFAULT);
			ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
			setIcon(iconoRedimensionado);
			setBounds(entidadObservada.getPosicionX(), entidadObservada.getPosicionY(), nuevoAncho, nuevoAlto);
			setVisible(true);
			revalidate();
			repaint();
		}
	}

	public void actualizarPosicionTamano() {
		if(this.getIcon() == null) {
			return;
		}
		int ancho = this.getIcon().getIconWidth();
		int alto = this.getIcon().getIconHeight();
		int x = AdaptadorPosicionPixel.transformarX(entidadObservada.getPosicionX());
		int y = AdaptadorPosicionPixel.transformarY(entidadObservada.getPosicionY()) - alto;
		setBounds(x, y, ancho, alto);
	}
	
	public void actualizarPorMuerte() {
		setVisible(false);
		this.setIcon(null);
		revalidate();
		repaint();
		System.out.println("Lo saco de la pantalla");
	}
}
