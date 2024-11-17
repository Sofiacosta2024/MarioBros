package Vista;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import Logica.*;

public class ObserverMario extends ObserverGrafica{
	
	private static final long serialVersionUID = 1L;
	private PantallaPartida panelPartida;
	private EntidadMario marioObservado;
	
	public ObserverMario(PantallaPartida panelPartida, EntidadMario observado, ControladorDeVistas controlador) {
		super(observado,controlador);
		this.panelPartida = panelPartida;
		marioObservado = observado;
		actualizar();
	}
	
	public void actualizar() {
		actualizarImagen();
		super.actualizarPosicionTamano();
		panelPartida.actualizarScrollHaciaMario(marioObservado);
	}
	
	public void actualizarImagen() {
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
	
	public void actualizarPosicionTamano() {
		super.actualizarPosicionTamano();
		panelPartida.actualizarScrollHaciaMario(marioObservado);
		if(marioObservado.getCambioEstado()==true) {
			actualizarImagen();
			marioObservado.setCambioEstado(false);
		}
	}
}