import java.io.Serializable;
import java.util.Collection;

import javafx.scene.image.Image;

/**
 * 
 * @author kara pelster, erin wallace, autumn stubbs, kenzie girvan, matt walkley
 *
 */


public class Decoration extends GardenObjects implements Serializable {
	String name;
	transient Image image;

	/**
	 * getter for Image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * setter for Image
	 * @param image Image image of the decoration
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * getter for name
	 * @return name String name of Decoration
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setter for image name
	 * @param name String name of Decoration
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * overloaded constructor for Decoration class
	 * @param name String name of image
	 */
	public Decoration(String name) {
		this.name = name;
	}
	
	/**
	 * default constructor for Decoration class
	 */
	public Decoration() {
	}

	
	/**
	 * overridden toString() for Decoration
	 * @return String name of Decoration
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * overridden getAnimals() method
	 */
	@Override
	protected Collection<? extends Animal> getAnimals() {
		return null;
	}
	
	/**
	 * overridden getType() method. never used for this class 
	 *
	 */
	@Override
	public String getType() {
		return null;
	}
	
	
	

}
