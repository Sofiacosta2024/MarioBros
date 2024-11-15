package Vista;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import Logica.EntidadLogica;
import Logica.EntidadMario;
import Logica.Juego;

public class PantallaPartida extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel panelPartida;
	private JPanel panelInformacion;
	private JPanel panelIndicadores;
	private JLabel imagenFondo;
	private JScrollPane panelScrollNivel;
	private JLabel labelPuntaje;
	private JLabel labelVidas;
	private JLabel labelTimer;
	private JLabel labelNivel;
	private JLabel labelIndicaVidas;
	private JLabel labelIndicaNivel;
	private JLabel labelIndicaPuntaje;
	private JLabel labelIndicaTimer;
	private int tiempoNivel, tiempoNivelInicial;
	private Timer timer, timerActualizacionInformacion;
	private ControladorDeVistas controlador;
	private Juego juego;
	
	public PantallaPartida(ControladorDeVistas controladorVistas, Juego juego) {
		this.juego = juego;
		this.controlador = controladorVistas;
		setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO));
		setLayout(new BorderLayout());
		setDoubleBuffered(true); 
		setFocusable(true);
		requestFocusInWindow();
		tiempoNivel = 400; 
		tiempoNivelInicial = 400;
		agregarPanelPartidaConFondoYScroll();
	}
	
	public Observer incorporarEntidad(EntidadLogica entidad) {
		ObserverEntidades observer = new ObserverEntidades(entidad,controlador);
		observer.setBounds(entidad.getPosicionX(), entidad.getPosicionY(), 50, 50);
		imagenFondo.add(observer);
		refrescarPantalla();
		return observer;
	}
	
	public Observer incorporarEntidadMario(EntidadMario entidad) {
		ObserverMario observer = new ObserverMario(this, entidad, controlador);
		observer.setBounds(entidad.getPosicionX(), entidad.getPosicionY(), 10, 10);
		imagenFondo.add(observer);
		actualizarInfoMario(entidad);
		refrescarPantalla();
		return observer;
	}
	
	public void actualizarInfoMario(EntidadMario mario) {
		actualizarLabelsInformacion(mario);
		actualizarScrollHaciaMario(mario);
	}
	
	public void actualizarLabelsInformacion(EntidadMario mario) {
		actualizarLabelPuntaje();
		actualizarLabelTiempo();
		actualizarLabelVidas(mario);
		actualizarLabelNivel();
	}
	
	public void actualizarLabelPuntaje() {
		labelPuntaje.setText(textoConCantidadDigitos(juego.getNivelActual().getMario().getPuntaje() , 5)+" ");
		labelPuntaje.repaint();
	}
	
	public void actualizarLabelVidas(EntidadMario mario) {
		labelVidas.setText( textoConCantidadDigitos(mario.getVidas(), 2)+"   ");
		labelVidas.repaint();
	}
	
	public void actualizarLabelNivel() {
		labelNivel.setText(textoConCantidadDigitos(juego.getNivelActual().getNivel(),1));
		labelNivel.repaint();
	}
	
	public void actualizarLabelTiempo() {
		labelTimer.setText( textoConCantidadDigitos(tiempoNivel,3)+"  ");
		labelTimer.repaint();
	}
	
	private String textoConCantidadDigitos(int numero, int digitos) {
		return String.format("%0" + digitos + "d", numero);
	}
	
	public void iniciarActualizacionInformacion() {
        timerActualizacionInformacion = new Timer(1000, e -> {
            if (juego != null && juego.getNivelActual().getMario() != null) {
                actualizarLabelTiempo();
            }
        });
        timerActualizacionInformacion.start();
    }
	
	public void detenerActualizacion() {
        if (timerActualizacionInformacion != null) {
        	timerActualizacionInformacion.stop();
        }
    }
	
	public void agregarPanelPartidaConFondoYScroll() {
		panelPartida = new JPanel();
		panelPartida.setLayout(null);
		panelPartida.setPreferredSize(new Dimension(ConstantesVistas.PANEL_PARTIDA_ANCHO*6, ConstantesVistas.PANEL_ALTO));
		panelScrollNivel = new JScrollPane(panelPartida);
		panelScrollNivel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelScrollNivel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		panelScrollNivel.setBounds(0, 0, ConstantesVistas.PANEL_PARTIDA_ANCHO, ConstantesVistas.PANEL_ALTO);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(ConstantesVistas.PANEL_PARTIDA_ANCHO, ConstantesVistas.PANEL_ALTO));
        panelScrollNivel.setBounds(0, 0, ConstantesVistas.PANEL_PARTIDA_ANCHO, ConstantesVistas.PANEL_ALTO);
        layeredPane.add(panelScrollNivel, JLayeredPane.DEFAULT_LAYER);
        agregarPanelInformacion();
        agregarPanelIndicadores();
        panelIndicadores.setBounds((ConstantesVistas.PANEL_PARTIDA_ANCHO - 300)/ 2-100,10,500,50);
        panelInformacion.setBounds((ConstantesVistas.PANEL_PARTIDA_ANCHO - 300)/ 2-30,30,300,50);
        layeredPane.add(panelInformacion, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(panelIndicadores,JLayeredPane.PALETTE_LAYER);
        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);
	}
	
	public void actualizarScrollHaciaMario(EntidadMario mario) {
	    JScrollBar barraHorizontal = panelScrollNivel.getHorizontalScrollBar();
	    int posicionMarioX = mario.getPosicionX();
	    int AnchoPantalla = panelScrollNivel.getViewport().getWidth();
	    int posicionDelScroll = barraHorizontal.getValue();
	    int MaximoDelScroll = barraHorizontal.getMaximum();
	    if(posicionMarioX > posicionDelScroll + AnchoPantalla/2 && posicionDelScroll < MaximoDelScroll) {
	    	barraHorizontal.setValue( posicionMarioX - AnchoPantalla/2 );
	    }
	    if (posicionMarioX <= AnchoPantalla / 2) {
	        barraHorizontal.setValue(0);  
	    } 
	}
	
	 public void resetearScroll() {
	        if (panelScrollNivel != null) {
	            panelScrollNivel.getHorizontalScrollBar().setValue(0);
	            panelScrollNivel.revalidate();  
	            panelScrollNivel.repaint();    
	        }
	 }
	
	public void agregarImagenFondo() {
		imagenFondo= new JLabel();
		ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource(juego.getRutaModo()+"/Fondojuego.jpg"));
		Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_PARTIDA_ANCHO*6, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
		ImageIcon iconoImagenEscalado = new ImageIcon(imagenEscalada);
		imagenFondo.setIcon(iconoImagenEscalado);
		imagenFondo.setLayout(null);
		imagenFondo.setBounds(0,0, ConstantesVistas.PANEL_PARTIDA_ANCHO*6, ConstantesVistas.PANEL_ALTO);
		panelPartida.add(imagenFondo);
		panelPartida.revalidate();
		panelPartida.repaint();
	}
	
	public void agregarPanelIndicadores() {
		Font fuente=cargarFuente();
		panelIndicadores = new JPanel();
		panelIndicadores.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelIndicadores.setOpaque(false);
		panelIndicadores.setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO));
		labelIndicaPuntaje.setBounds(10,5,80,30);
		labelIndicaPuntaje.setFont(fuente);
		labelIndicaVidas.setBounds(100,5,80,30);
		labelIndicaVidas.setFont(fuente);
	    labelIndicaTimer.setBounds(190,5,80,30);
	    labelIndicaTimer.setFont(fuente);
	    labelIndicaNivel.setBounds(240,5,80,30);
	    labelIndicaNivel.setFont(fuente);
	    panelIndicadores.add(labelIndicaPuntaje);
	    panelIndicadores.add(labelIndicaTimer);
	    panelIndicadores.add(labelIndicaVidas);
	    panelIndicadores.add(labelIndicaNivel);
        panelIndicadores.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, 100);
		add(panelIndicadores);
	}
	
	public void agregarPanelInformacion() {
		panelInformacion = new JPanel();
		panelInformacion.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelInformacion.setOpaque(false);
		panelInformacion.setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO));
		agregarLabelsEditablesInformacion();
		Font fuente=cargarFuente();
		labelPuntaje.setBounds(10,30,80,30);
		labelPuntaje.setFont(fuente);
        labelTimer.setBounds(100,30,80,30);
        labelTimer.setFont(fuente);
        labelVidas.setBounds(190,30,80,30);
        labelVidas.setFont(fuente);
        labelNivel.setBounds(10,30,80,30);
        labelNivel.setFont(fuente);
		panelInformacion.add(labelPuntaje);
	    panelInformacion.add(labelTimer);
	    panelInformacion.add(labelVidas);
	    panelInformacion.add(labelNivel);
        panelInformacion.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, 100);
		add(panelInformacion);
		refrescarPantalla();
	}
	
	public void agregarLabelsEditablesInformacion() {
		labelIndicaPuntaje=new JLabel("SCORE");
		labelIndicaVidas=new JLabel("LIVES");
		labelIndicaNivel=new JLabel("LEVEL");
		labelIndicaTimer=new JLabel("TIMER");
		labelPuntaje = new JLabel("00000");
		labelTimer = new JLabel("400");
		labelVidas = new JLabel("000");
		labelNivel = new JLabel("00");
		decorarLabelsInformacion();	
	}	
	public void decorarLabelsInformacion() {
		labelPuntaje.setForeground(Color.WHITE);
		labelTimer.setForeground(Color.WHITE);
		labelVidas.setForeground(Color.WHITE);
		labelNivel.setForeground(Color.WHITE);
		labelIndicaVidas.setForeground(Color.WHITE);
		labelIndicaPuntaje.setForeground(Color.WHITE);
		labelIndicaTimer.setForeground(Color.WHITE);
		labelIndicaNivel.setForeground(Color.WHITE);
		labelIndicaTimer.setFont(new Font(labelPuntaje.getFont().getName(), Font.BOLD, 24));
		labelIndicaPuntaje.setFont(new Font(labelPuntaje.getFont().getName(), Font.BOLD, 24));
		labelIndicaVidas.setFont(new Font(labelPuntaje.getFont().getName(), Font.BOLD, 24));
		labelIndicaNivel.setFont(new Font(labelPuntaje.getFont().getName(), Font.BOLD, 24));
		labelPuntaje.setFont(new Font(labelPuntaje.getFont().getName(), Font.BOLD, 24));
		labelTimer.setFont(new Font(labelPuntaje.getFont().getName(), Font.BOLD, 24));
		labelVidas.setFont(new Font(labelPuntaje.getFont().getName(), Font.BOLD, 24));
		labelNivel.setFont(new Font(labelPuntaje.getFont().getName(), Font.BOLD, 24));
	}
	
	public void refrescarPantalla() {
		revalidate();
		repaint();
	}
	
	public void limpiarEntidadesGraficas() {
		 if (imagenFondo != null) {
		        imagenFondo.removeAll(); 
		    }  
        refrescarPantalla();
    }
	
	public void iniciarTemporizador() {
	    timer = new Timer(1000, e -> {
	        if (tiempoNivel > 0) {
	            tiempoNivel--;
	            labelTimer.setText(String.format("%d", tiempoNivel));
	        } else {
	            timer.stop();
	        }
	    });
	    timer.start();
	}
	
	private Font cargarFuente() {
		File fuenteFile = new File("src/Fuente/fuentejuego.ttf");
		try {
        	return Font.createFont(Font.TRUETYPE_FONT, fuenteFile).deriveFont(16.3f);
		} catch (FontFormatException | IOException e) {
			return new Font("Arial", Font.BOLD, 22);
		}
	}
	
	public void setTiempoNivel(int segundos) {
		tiempoNivel = segundos;
		tiempoNivelInicial = segundos;
	}
	
	public int getTiempoNivel() {
		return tiempoNivel;
	}
	
	public int getTiempoNivelInicial() {
		return tiempoNivelInicial;
	}
	
	public JLabel getImagenFondo() {
		return imagenFondo;
	}
}