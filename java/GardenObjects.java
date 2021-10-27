import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * 
 * @author kara pelster, erin wallace, autumn stubbs, kenzie girvan, matt
 *         walkley
 *
 */
public abstract class GardenObjects implements Serializable {
	int xLoc;
	int yLoc;
	String name; // not too sure about what this and file name are, fix the constructor
	String filename;
	String type;
	transient Image image; // transient tells java not to serialize this object
	double scaleFactor;
	
	
	/**
	 * default constructor
	 */
	public GardenObjects() {
		
	}
	
	/**
	 *	overlaoded constructor for GardenObjects
	 *	@param filename String the filename file name that garden objects will be read in from
	 *	@param name String the name of the object
	 */
	public GardenObjects(String filename, String name) {
		this.filename = filename;
		this.name = name;
	}
	
	/**
	 * tString method
	 * @return String the name of the object and its x and y location
	 */
	public String toString() {
		return name + " " + xLoc + " " + yLoc;
	}
	
	/**
	 * abstract getter method for getting the image of the object
	 * @return Image
	 */
	protected abstract Image getImage();


	/**
	 * getter method for getting the collection of the insects that a particular
	 * garden object has
	 * @return Collection of type Insect
	 */
	public Collection<? extends Insect> getInsects() {
		return null;
	}

	/**
	 * abstract getter method for getting the collection of the animals that a particular
	 * garden object has
	 * @return Collection of type animal
	 */
	protected abstract Collection<? extends Animal> getAnimals();
	
	/**
	 * getter method for getting the scale of a garden object based on what it is
	 * @return double
	 */
	public double getScale() {
		return this.scaleFactor;
	}
	
	/**
	 * setter method for getting the scale of a garden object based on what it is
	 * @param scale double
	 */
	public void setScale(double scale) {
		this.scaleFactor = scale;
	}
	
	/**
	 * setter method for the y location of a GardenObject
	 * @param yLoc int the y coordinate of a particular GardenObject
	 */
	public void setY(int yLoc) {
		this.yLoc = yLoc;
	}
	
	/**
	 * setter method for the x location of a GardenObject
	 * @param xLoc int the x coordinate of a particular GardenObject
	 */
	public void setX(int xLoc) {
		this.xLoc = xLoc;
	}
	
	/**
	 * getter method for the y location of a GardenObject
	 * @return int
	 */
	public int getY() {
		return yLoc;
	}
	
	/**
	 * getter method for the x location of a GardenObject
	 * @return int
	 */
	public int getX() {
		return xLoc;
	}
	
	/**
	 * abstract getter method for getting the type of the object
	 * @return String type
	 */
	public abstract String getType();
	
	/**
	 * getter method for the filename associated with the image of a particular object
	 * @return String
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * setter method for the file name associated with the image of a particular object
	 * @param filename String the name of the file
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * abstract getter method for getting the name of the object
	 * @return name the name of the object
	 */
	protected abstract String getName();
 }
