import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


import javax.imageio.ImageIO;

import com.sun.javafx.iio.ImageStorage.ImageType;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * 
 * @author kara pelster, erin wallace, autumn stubbs, kenzie girvan, matt
 *         walkley
 *
 */
public class Garden implements Serializable {

	private double width;
	private double height;
	private String name;
	private static final long serialVersionUID = 8163123635726566638L;
	Collection<GardenObjects> stuffInGarden;
	transient BufferedImage gardenImage;
	transient Image gridImage;
	String gardenShapeName;

	/**
	 * Default Constructor for Garden. makes new array list for objects in garden
	 */
	public Garden() { // use for starting a new garden
		this.stuffInGarden = new ArrayList<GardenObjects>();
	}
	
	/**
	 * getStuffInGarden method gets the list of items in the garden
	 * @return this.stuffInGarden
	 */

	public Collection<GardenObjects> getStuffinGarden() {
		return this.stuffInGarden;
	}

	/**
	 * Method for adding an object into the garden, adds to stuffInGarden
	 * 
	 * @param object ObjectsToAdd the object the user wants to add to the garden
	 */
	public void addObject(GardenObjects object) {
		stuffInGarden.add(object);

	}
	
	/**
	 * returns gridImage for object 
	 * @return gridImage that is associated with garden object
	 */
	public Image getGridImage() {
		return gridImage;
	}

	/**
	 * sets grid image for garden object
	 * @param gridImage image of grid for garden
	 */
	public void setGridImage(Image gridImage) {
		this.gridImage = gridImage;
	}
	
	/**
	 * sets image of garden background shape
	 * @param gardenImg a buffered image object
	 */
	public void setGardenImage(BufferedImage gardenImg) {
		gardenImage = gardenImg;
	}

	/**
	 * getter for garden background image
	 * @return buffered garden image for background
	 */
	public BufferedImage getGardenImage() {
		return gardenImage;
	}

	/**
	 * setter method for setting the width of the garden
	 * @param w the width in ft of the garden
	 */
	public void setWidth(double w) {
		width = w;
	}

	/**
	 * getter method for getting the width of the garden
	 * 
	 * @return int width of the garden in ft
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * setter method for setting the height of the garden
	 * 
	 * @param h the height in ft of the garden
	 */
	public void setHeight(double h) {
		height = h;
	}

	/**
	 * setter method for setting the height of the garden
	 * 
	 * @return int height of the garden in ft
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * getter method for name of garden
	 * @return string name of garden
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter method for name of shape file in garden
	 * @return string object of garden shape name
	 */
	public String getGardenShapeName() {
		return this.gardenShapeName;
	}

	/**
	 * setter method for garden shape name
	 * @param gardenShapeName a string for the name of the shape of the garden
	 */
	public void setGardenShapeName(String gardenShapeName) {
		this.gardenShapeName = gardenShapeName;
	}

	/**
	 * Method for removing an object into the garden
	 * @param object the object the user wants to remove from the
	 *               garden
	 */

	public void removeObject(GardenObjects object) {
		stuffInGarden.remove(object); 
	}

	/**
	 * setter method to set the garden name
	 * @param name name of the garden
	 */

	public void setName(String name) { 
		this.name = name; 
	} 


}
 