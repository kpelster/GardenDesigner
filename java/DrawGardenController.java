import javafx.scene.layout.Background;
import javafx.stage.Stage;

/**
 * 
 * @author kara pelster, erin wallace, autumn stubbs, kenzie girvan, matt walkley
 *
 */

public class DrawGardenController {

	DrawGardenView dgv;
	DesignView dv;
	SavedGardenView sgv;
	StartView sv;
	Model model = new Model();
	final int DEFAULT_DIM = 20; //CONSTANT

	public DrawGardenController(DrawGardenView dgv){
		this.dgv = dgv;
	}

	/**
	 * handles flag for DrawGarden that goes to DesignView
	 */
	void handleFlag() {
		if(dgv.getFlag()) {
			model.setGridHeight(dgv.getOriginalH());
			model.setGridWidth(dgv.getOriginalW());
			
			if(model.getGridWidth() == 0) {
				model.setGridWidth(DEFAULT_DIM);
			}
			if(model.getGridHeight() == 0) {
				model.setGridHeight(DEFAULT_DIM);
			}

			dv = new DesignView();
			dv.setgH(model.getGridHeight());
			dv.setgW(model.getGridWidth());

			Garden garden = new Garden();
			garden.setHeight(model.getGridHeight());
			garden.setWidth(model.getGridHeight());
			garden.setName(dgv.getGardenName());

			dv.setGarden(garden);
			dv.setGridImage(dgv.getGridImage());
			dv.start(dgv.getStage());

		}
	}
	
	/**
	 * calls model to check the height and width of the garden
	 * @param gH input height of garden
	 * @param gW input width of garden
	 */
	public void checkDimensions(double gH, double gW){
		double newGH = model.checkH(gH);
		double newGW = model.checkW(gW);
		
		dgv.setgH(newGH);
		dgv.setgW(newGW);
		
	}
	
	/**
	 * calls model to set scale fo garden
	 * @param gH input height of garden
	 * @param gW input width of garden
	 */
	public void checkScale(double gH, double gW) {
		double scale = model.checkScale(gH, gW);
		//dv.setScale(scale);
		dgv.setScale(scale);
	}

	/**
	 * handles back flag that goes to startView
	 */
	void handleBackFlag() {
		model = new Model();
		if(dgv.getBackFlag()) {
			sv = new StartView();
			sv.start(dgv.getStage());
		}
	}

	/**
	 * getter for model
	 * @return Model
	 */
	public Model getModel() {
		return this.model;
	}
}


