import java.io.Serializable;
import java.util.Collection;
import javafx.scene.image.Image;

/**
 * 
 * @author kara pelster, erin wallace, autumn stubbs, kenzie girvan, matt walkley
 *
 */

public class Animal extends GardenObjects implements Serializable{ //need to change all objectsToAdd to Garden objects

	String name;
	String type;
	
	/**
	 * Animal constructor
	 * @param name String English name of Animal
	 */
	public Animal(String name) {
		this.name = name;
	}
	
	/**
	 * default Animal constructor
	 */
	public Animal() {
	}
	
	/**
	 * overrides toString()
	 * @return Animal name
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * setter for Animal name
	 * @param name String Animal's English name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * overrides getter for image
	 */
	@Override
	protected Image getImage() {
		//dont need image of animal but this must be here because of abstract class
		return null;
	}
	
	/**
	 * overrides getAnimals()
	 */
	@Override
	protected Collection<? extends Animal> getAnimals() {
		//dont need collection but this must be here because of abstract class
		return null;
	}
	
	/**
	 * overrides getType in gardenObjects
	 */

	@Override
	public String getType() {
		return null;
	}
	
	/**
	 * overrides getter for image
	 * @return name String name of the animal
	 * 
	 * 
	 */
	@Override
	protected String getName() {
		return name;
	}
	
	

}
