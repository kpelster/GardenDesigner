import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * 
 * @author kara pelster, erin wallace, autumn stubbs, kenzie girvan, matt walkley
 *
 */

public class DesignController {
	
	
	static Model model;
	DesignView dv;
	DrawGardenView dgv;
	StartView sv;
	Garden garden;
	DrawGardenController dgc;
	SavedGardenView sgv;
	SavedGardenController sgc;
	
	
	/**
	 * DesignController constructor
	 * @param dv the DesignView
	 */
	public DesignController(DesignView dv) {
		
		model = new Model();
		
		this.dv = dv;
		dv.setgH(model.getGridHeight());
		dv.setgW(model.getGridWidth());

		Model.loadObjects();
		
		dv.setObjectMap(model.getObjectMap());
		dv.setPlantMap(model.getPlantMap());
		//might be for load

		dv.getGarden().setName(model.getGardenName());

	}
	
	/**
	 * overloaded DesignController constructor
	 * @param dv the DesignView
	 * @param model the Model
	 */
	public DesignController(DesignView dv, Model model) {
		
		this.model = model;
		
		this.dv = dv;
		dv.setgH(model.getGridHeight());
		dv.setgW(model.getGridWidth());

		Model.loadObjects();
		
		dv.setObjectMap(model.getObjectMap());
		dv.setPlantMap(model.getPlantMap());

	}
	
	/**
	 * method to remove the objects from the design
	 * @param ob the object to be removed
	 */
	void removeObject(GardenObjects ob) {
		model.getGarden().removeObject(ob);
	}
	
	/**
	 * method to handel the model's flags
	 * @return model the Model
	 */
	public Model handleNewModelFlag() {
		model = new Model();
		return model;
	}
	
	/**
	 * method to handle the flag to move back to the StartView
	 */
	void handleBackFlag() {
		model = new Model();
		if(dv.getBackFlag()) {
			sv = new StartView();
			sv.start(dv.getStage());
		}
		if(dv.getBackFlag2()) {
			dgv = new DrawGardenView();
			dgv.start(dv.getStage());
		}
		
	}
	
	/**
	 * handles save of the Garden
	 * @param garden the Garden
	 * @param file the file
	 */
	void handleSave(Garden garden, File file) {
		
		if (file != null) {
    		model = new Model(garden);
            model.saveGarden(model, file);
		}
	}
		
	/**
	 * opens saved garden
	 * @param sgv the SavedGardenView
	 */
	void openSavedGarden(SavedGardenView sgv) {
			dv.setgH(model.getGridHeight());
			dv.setgW(model.getGridWidth());
			dv.start(sgv.getStage());
	}
	

	/**
	 * sends eco information to model to update the garden stats correctly
	 * @param gardenObjects a garden object to be possibly added to garden stats
	 * @param b boolean if it is already in garden
	 * @param animalsInGarden a hashmap of the animals that are already in the garden
	 * @param insectsInGarden a hashmap of the insects that are already in the garden
	 * @param bloomSzn a string for what season the plant blooms in
	 */
	void handleEcoUpdate(GardenObjects gardenObjects, boolean b, HashMap<String, Integer> animalsInGarden, HashMap<String, Integer> insectsInGarden, HashMap<String, Integer> bloomSzn) {
		model.updateEcoStats(gardenObjects, b, animalsInGarden, insectsInGarden, bloomSzn);
	}
	
	/**
	 * finds the average shade value in string form based off the plants in the garden
	 * @param gardenObjects Collection of GardenObjects  this is the objects already in the garden
	 * @return String
	 */
	public String handleGetAvgShade(Collection<GardenObjects> gardenObjects) {
		return model.getAvgShade(gardenObjects);
	}
	
	/**
	 * recommends up to 3 plants that have shade values equal to the average 
	 * shade value of the garden 
	 * @param gardenObjects Collection of GardenObjects  in the garden
	 * @return String
	 */
	public String handleRecommendations(Collection<GardenObjects> gardenObjects) {
		return model.getRecommendations(gardenObjects);
	}
	
	/**
	 * calls model to check the height and width of the garden
	 * @param gH input height of garden
	 * @param gW input width of garden
	 */
	public void checkDimensions(double gH, double gW){
		double newGH = model.checkH(gH);
		double newGW = model.checkH(gH);
		
		dv.getGarden().setHeight(newGH);
		dv.getGarden().setWidth(newGW);
		
	}
	
	/**
	 * calls model to set scale fo garden
	 * @param gH input height of garden
	 * @param gW input width of garden
	 */
	public void checkScale(double gH, double gW) {
		double scale = model.checkScale(gH, gW);
		dv.setScale(scale);
	}
	
}
	


