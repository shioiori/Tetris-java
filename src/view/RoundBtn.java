package view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class RoundBtn implements Border 
{
    private int r;
    
    RoundBtn(int r) {
        this.r = r;
    }
   
    public boolean isBorderOpaque() {
        return true;
    }

	@Override
	public Insets getBorderInsets(Component arg0) {
		// TODO Auto-generated method stub
		return new Insets(this.r+1, this.r+1, this.r+2, this.r);
	}
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.drawRoundRect(x, y, width-1, height-1, r, r);	
	}
}