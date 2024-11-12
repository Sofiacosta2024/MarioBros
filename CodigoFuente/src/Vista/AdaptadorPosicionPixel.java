package Vista;

public class AdaptadorPosicionPixel {
	
	public static int transformarX(int x) {
		return x;
	}
	
	public static int transformarY(int y) {
		return ConstantesVistas.PANEL_ALTO - y;
	}
}