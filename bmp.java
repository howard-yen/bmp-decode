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
      BufferedImage reversedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
      for(int i = 0; i < image.getWidth(); i++){
        for(int j = 0; j < image.getHeight(); j++){
          reversedImage.setRGB(i, j, reverseRGB(image.getRGB(i, j)));
        }
      }

      // write the image
      File outputFile = new File(args[0].substring(0, args[0].lastIndexOf(".bmp")) + "_reversed.bmp");
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

  /*
    for each color, we get the negative by subtracting the value from the
    maximum value for each color (255) and return it all in one int.
    Since we are dealing with negative images, the transparency shouldn't
    really matter so we just use the same transparency value.
  */
  private static int reverseRGB(int rgb){
    int blue = 255 - (rgb & 0xff);
    int green = (255 - ((rgb & 0xff00) >> 8)) << 8;
    int red = (255 - ((rgb & 0xff0000) >> 16)) << 16;
    return (rgb & 0xff000000) + red + green + blue;
  }
}
