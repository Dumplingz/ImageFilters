package filters;

import javax.swing.JOptionPane;

import imagelab.ImageFilter;
import imagelab.ImgProvider;

public class Pixellate implements ImageFilter {
	private ImgProvider filteredImage;

	public void filter(ImgProvider ip) {
		short[][] red = ip.getRed();
		short[][] green = ip.getGreen();
		short[][] blue = ip.getBlue();
		short[][] alpha = ip.getAlpha();

		int height = red.length;
		int width = red[0].length;

		short[][] newRed = new short[height][width];
		short[][] newGreen = new short[height][width];
		short[][] newBlue = new short[height][width];
		short[][] newAlpha = new short[height][width];

		int pixelSize = Integer.parseInt(JOptionPane.showInputDialog("Size of Pixel?"));
		// Loop through the original image and store the modified
		// version in the newImage array
		for (int row = 0; row < height; row += pixelSize) {
			for (int col = 0; col < width; col += pixelSize) {
				int r = 0;
				int g = 0;
				int b = 0;
				int a = 0;
				int pixels = 0;
				for (int y = 0; y < pixelSize; y++) {
					for (int x = 0; x < pixelSize; x++) {
						int rowVal = y + row;
						int colVal = x + col;
						if (rowVal < height && colVal < width) {
							r += red[rowVal][colVal];
							g += green[rowVal][colVal];
							b += blue[rowVal][colVal];
							a += alpha[rowVal][colVal];

							pixels++;
						}
					}
				}
				short avgRed = (short) ((double) (r) / (double) (pixels));
				short avgGreen = (short) ((double) (g) / (double) (pixels));
				short avgBlue = (short) ((double) (b) / (double) (pixels));
				short avgAlpha = (short) ((double) (a) / (double) (pixels));

				for (int y = 0; y < pixelSize; y++) {
					for (int x = 0; x < pixelSize; x++) {
						int rowVal = y + row;
						int colVal = x + col;
						if (rowVal < height && colVal < width) {
							newRed[rowVal][colVal] = avgRed;
							newGreen[rowVal][colVal] = avgGreen;
							newBlue[rowVal][colVal] = avgBlue;
							newAlpha[rowVal][colVal] = avgAlpha;
						}
					}
				}
			}
		}

		filteredImage = new ImgProvider();
		filteredImage.setColors(newRed, newGreen, newBlue, alpha);

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
}
