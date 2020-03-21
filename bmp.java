import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class bmp{
  public static void main(String args[]){
    try{
      // read the image from command line
      File inputFile = new File(args[0]);
      BufferedImage image = ImageIO.read(inputFile);

          // iterate through the image and reverse every pixel
      BufferedImage reversedImage = new BufferedImage(image.getWidth(), 
		      image.getHeight(), BufferedImage.TYPE_INT_RGB);
      for(int i = 0; i < image.getWidth(); i++){
        for(int j = 0; j < image.getHeight(); j++){
	  // leave the first byte untouched, masked out the ones in 
	  // the RGB bytes to get the negative
	  reversedImage.setRGB(i, j, image.getRGB(i, j) ^ 0x00ffffff);
        }
      }

      // write the image
      File outputFile = new File(args[0].substring(0, 
			      args[0].lastIndexOf(".bmp")) + "_reversed.bmp");
      ImageIO.write(reversedImage, "bmp", outputFile);

      System.out.println("Done.");
    }catch(FileNotFoundException e){
      System.out.println(args[0] + " not found, please try again.");
    }catch(IOException e){
      System.out.println("An I/O exception has occurred, please try again.");
    }catch(ArrayIndexOutOfBoundsException e){
      System.out.println("Please enter the name of the file in command line.");
    }
  }
}
