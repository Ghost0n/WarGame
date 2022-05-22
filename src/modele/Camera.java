package modele;

import javax.swing.JScrollPane;

public class Camera {

	private int offX, offY;
	
	public Camera(int x, int y) {
		this.offX = x;
		this.offY = y;
	}

	public void update(int x, int y, JScrollPane scrollPane) {
		this.offX = x - scrollPane.getWidth()/2;
		this.offY = y - scrollPane.getHeight()/3;
	}
	
	public int getOffX() {
		return offX;
	}

	public void setOffX(int offX) {
		this.offX = offX;
	}

	public int getOffY() {
		return offY;
	}

	public void setOffY(int offY) {
		this.offY = offY;
	}
	
}
