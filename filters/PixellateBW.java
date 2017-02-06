package filters;

import javax.swing.JOptionPane;

import imagelab.*;

public class PixellateBW implements ImageFilter {

	// Attribute to store the modified image
	ImgProvider filteredImage;

	public void filter(ImgProvider ip) {

		// Grab the pixel information and put it into a 2D array
		short[][] im = ip.getBWImage();

		// Make variables for image height and width
		int height = im.length;
		int width = im[0].length;

		// Create a new array to store the modified image
		short[][] newImage = new short[height][width];

		int pixelSize = Integer.parseInt(JOptionPane.showInputDialog("Size of Pixel?"));
		// Loop through the original image and store the modified
		// version in the newImage array
		for (int row = 0; row < height; row += pixelSize) {
			for (int col = 0; col < width; col += pixelSize) {
				int color = 0;
				int pixels = 0;
				for (int y = 0; y < pixelSize; y++) {
					for (int x = 0; x < pixelSize; x++) {
						int rowVal = y + row;
						int colVal = x + col;
						if (rowVal < height && colVal < width) {
							color += im[rowVal][colVal];
							pixels++;
						}
					}
				}
				short avgColor = (short) ((double) (color) / (double) (pixels));

				for (int y = 0; y < pixelSize; y++) {
					for (int x = 0; x < pixelSize; x++) {
						int rowVal = y + row;
						int colVal = x + col;
						if (rowVal < height && colVal < width) {
							newImage[rowVal][colVal] = avgColor;
						}
					}
				}
			}
		}

		// Create a new ImgProvider and set the filtered image to our new image
		filteredImage = new ImgProvider();
		filteredImage.setBWImage(newImage);

		// Show the new image in a new window with title "Flipped Horizontally"
		filteredImage.showPix("Pixellated");
	}

	public ImgProvider getImgProvider() {
		return filteredImage;
	}

	// This is what users see in the Filter menu
	public String getMenuLabel() {
		return "Pixellate (BW)";
	}

}