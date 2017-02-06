package filters;

import javax.swing.JOptionPane;

import imagelab.ImageFilter;
import imagelab.ImgProvider;

public class Colornoise implements ImageFilter {
	private ImgProvider filteredImage;

	public void filter(ImgProvider ip) {
		short[][] red = ip.getRed();
		short[][] green = ip.getGreen();
		short[][] blue = ip.getBlue();
		short[][] transp = ip.getAlpha();

		int height = red.length;
		int width = red[0].length;

		short[][] newRed = new short[height][width];
		short[][] newGreen = new short[height][width];
		short[][] newBlue = new short[height][width];
		short[][] newAlpha = new short[height][width];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				newRed[row][col] = red[row][col];
				newGreen[row][col] = green[row][col];
				newBlue[row][col] = blue[row][col];
				newAlpha[row][col] = transp[row][col];
			}
		}

		int noisePercent = Integer.parseInt(JOptionPane.showInputDialog("How much noise?"));
		int numPixels = (int)( (noisePercent / 100.0) * (height * width));
		
		for(int i = 0; i < numPixels; i ++){
			int row = ((int)(Math.random() * height));
			int col = ((int)(Math.random() * width));
			short r = ((short)(Math.random() * Short.MAX_VALUE));
			short g = ((short)(Math.random() * Short.MAX_VALUE));
			short b = ((short)(Math.random() * Short.MAX_VALUE));
			newRed[row][col] = r;
			newGreen[row][col] = g;
			newRed[row][col] = b;
//			newAlpha[row][col] = 0;
		}
		
		filteredImage = new ImgProvider();
		filteredImage.setColors(newRed, newGreen, newBlue, transp);
		
		filteredImage.showPix("Noise");
	}

	@Override
	public ImgProvider getImgProvider() {

		return filteredImage;
	}

	@Override
	public String getMenuLabel() {
		return "Noise Filter";
	}
}
