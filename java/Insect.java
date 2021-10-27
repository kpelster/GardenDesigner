import java.io.Serializable;
import java.util.Collection;

import javafx.scene.image.Image;

/**
 * 
 * @author kara pelster, erin wallace, autumn stubbs, kenzie girvan, matt
 *         walkley
 *
 */
public class Insect extends GardenObjects implements Serializable{

	public String name;
	public String type;

	//constructor
	/**
	 * default constructor
	 */
	public Insect() {
	}

	/**
	 * overloaded constructor
	 * @param n String name of the insect
	 */
	public Insect(String n){
		name = n;
	}

	/**
	 * returns the attributes of the class as a string
	 * @author karapelster
	 * @return the attributes of the class as a string
	 */
	public String toString(){
		return name; 
	}


	/**
	 * getter method for getting the image of the particular insect
	 */
	@Override
	protected Image getImage() {
		return null;
	}

	/**
	 * getter method for getting the collection of animals associated with the given insects
	 */
	@Override
	protected Collection<? extends Animal> getAnimals() {
		return null;
	}


	/**
	 * setter method for the name associated with the this particular Insect
	 * @param name String the name of the Insect
	 */
	public void setName(String name) {
		this.name = name;

	}
	
	/**
	 * getter method. only here because insect extends gardenObjects
	 * @return null for this class
	 */
	@Override
	public String getType() {
		return null;
	}
	/**
	 * getter method for getting the name of the insect
	 */
	@Override
	protected String getName() {
		return name;
	}




}
