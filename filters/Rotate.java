package filters;

import javax.swing.JOptionPane;

import imagelab.ImageFilter;
import imagelab.ImgProvider;

public class Rotate implements ImageFilter {
	private ImgProvider filteredImage;

	public void filter(ImgProvider ip) {
		short[][] red = ip.getRed();
		short[][] green = ip.getGreen();
		short[][] blue = ip.getBlue();
		short[][] alpha = ip.getAlpha();

		int height = red.length;
		int width = red[0].length;

		int midHeight = height / 2;
		int midWidth = width / 2;

		short[][] newRed = new short[height][width];
		short[][] newGreen = new short[height][width];
		short[][] newBlue = new short[height][width];
		short[][] newAlpha = new short[height][width];

		int pixelSize = Integer.parseInt(JOptionPane.showInputDialog("Size of Pixel?"));
		// Loop through the original image and store the modified
		// version in the newImage array

		filteredImage = new ImgProvider();
		filteredImage.setColors(newRed, newGreen, newBlue, newAlpha);

		filteredImage.showPix("Pixellated");

	}

	@Override
	public ImgProvider getImgProvider() {

		return filteredImage;
	}

	@Override
	public String getMenuLabel() {
		return "Pixellate";
	}

	private double getDistance(double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.sqrt((dx * dx) + (dy * dy));
	}

	private double getAngle(double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.atan(dy / dx);
	}
}
