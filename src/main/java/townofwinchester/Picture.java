/*
 * ChatServer.java
 */
package townofwinchester;

import java.awt.*;
import javax.swing.*;
import java.lang.*;


import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;
import org.apache.logging.log4j.*;
import java.util.Scanner;
import java.applet.*;
//Following Imports are for Reading image files

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * This class is for drawing on the GUI
 *
 * @author Tim Dalton
 * @author Andy Ark
 * @author Roy Xing
 */




public class Picture extends JPanel{
 
private BufferedImage image;    // image variable used to hold images that will be drawn
private final java.util.List<Path> imagePaths;  // List of all character images

 public Picture(){                                                                                        //reads the image from files
     
     imagePaths = getPaths("/images");// read paths to image files
   
     try{                                                                                            //try catch block necessary for reading images
     ClassLoader classLoader = getClass().getClassLoader();                                          //idk what this code does
     for (int i = 0; i < imagePaths.size(); i++){
         File f = new File(classLoader.getResource("images/" + imagePaths.get(i)).getFile());       //pulls an image from imagePaths list at location i
         image = ImageIO.read(f);                                                                        //reads the previously pulled file
         image = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);    //constructor for BufferedImage class that sets image to the proper size and type
     }

     }catch(IOException e){                                                                          //catches the excpetion
          System.out.println("Error: " + e);
     }

 }
 public void paintComponent(Graphics g){
     g.drawImage(image, 50, 50, null);               //Numbers temporary until I figure out how to access c.gridx/y for the temp Pictures
 }
 
     /**
     * Read all file paths from directory and return them as a {@link java.util.List}.
     * Precondition: directory is not null, not empty, and must start with '/'.
     *
     * @param directory directory within resources directory
     * @return {@link java.util.List} of {@link Path}s.
     * @see <a href="https://stackoverflow.com/questions/1429172/how-do-i-list-the-files-inside-a-jar-file/39974405#39974405" target="_blank">https://stackoverflow.com/questions/1429172/</a>
     */
    private java.util.List<Path> getPaths(String directory) {
        assert directory != null && directory.length() > 0 && directory.charAt(0) == '/'
            : String.format("\"%s\" is null, or empty, or doesn't start with '/'", directory);
        LogManager.getLogger(TownOfWinchester.SHORT)
            .info("getPaths(\"{}\") =", directory);
        final java.util.List<Path> paths = new ArrayList<Path>();
        try {
            URI uri = MyFrame.class.getResource(directory).toURI();
            try (FileSystem fileSystem = (uri.getScheme().equals("jar") ? FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap()) : null)) {
                Path myPath = Paths.get(uri);
                Files.walkFileTree(myPath, new SimpleFileVisitor<Path>() { 
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        paths.add(file);
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            System.exit(5); // I/O error
        }
        LogManager.getLogger(TownOfWinchester.SHORT)
            .info("{}.size() ({} files)", paths, paths.size());
        return paths;
    }
 
}