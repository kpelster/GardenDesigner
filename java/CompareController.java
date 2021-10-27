import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 
 * @author kara pelster, erin wallace, autumn stubbs, kenzie girvan, matt walkley
 *
 */


public class CompareController {
	Model model = new Model();
	CompareView cv;
	DesignView dv;
	DrawGardenView dgv;
	//CompareController cc;
	DesignController dc;
	StartView sv;

	/**
	 * CompareController constructor
	 * @param cv CompareView compare view object
	 */
	public CompareController(CompareView cv) {
		this.cv = cv;

	}
	
	/**
	 * handles flag from CompareView to move to StartView
	 */
	void handleFlag() {
		if(cv.gethFlag()) {
			sv = new StartView();
			sv.start(cv.getStage());	
		}
	}

	/**
	 * creates new model object from objectInputStream
	 * @param oi objectoutputstream is read from compare view when garden is chosen
	 * @throws ClassNotFoundException exception if no class
	 * @throws IOException exception for input stream reader
	 */
	public void loadGardenFile(ObjectInputStream oi) throws ClassNotFoundException, IOException{
		model = (Model) oi.readObject();
		loadGardenInDv(model);

	}
	
	/**
	 * sets model in new DesignView from a saved garden
	 * @param model a model object to be used to set the model in DesignView
	 */
	public void loadGardenInDv(Model model) {
		dv = new DesignView();
		dc = new DesignController(dv, model);
		dv.setgH(model.getGridHeight());
		dv.setgW(model.getGridWidth());
		dv.setSflag(cv.getcFlag());
		dv.setGarden(model.getGarden());
		dv.setShapeName(model.getGarden().getGardenShapeName());
		dv.getGarden().setGridImage(model.getGarden().getGridImage());

		dv.start(cv.getStage());
	}
}
