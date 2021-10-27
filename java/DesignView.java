import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Set;
import java.util.function.Predicate;

import javax.imageio.ImageIO;

/**
 * 
 * @author kara pelster, erin wallace, autumn stubbs, kenzie girvan, matt walkley
 *
 */

public class DesignView{

	DrawGardenView dgv = new DrawGardenView();
	DesignController designController;
	//Model model = DesignController.model;
	//nodes
	BorderPane border = new BorderPane();
	TilePane tileLeft = new TilePane();

	AnchorPane gardenPane = new AnchorPane();
	//FlowPane plantFlow = new FlowPane(Orientation.HORIZONTAL);
	GridPane plantGrid = new GridPane();
	GridPane bushGrid = new GridPane();
	GridPane treeGrid = new GridPane();
	GridPane flowerGrid = new GridPane();
	GridPane grassGrid = new GridPane();
	GridPane objectGrid = new GridPane();
	BorderPane ecoBorder = new BorderPane();
	FlowPane ecoFlow = new FlowPane(Orientation.VERTICAL);
	TitledPane ecoTP = new TitledPane();
	ScrollPane ecoScroll = new ScrollPane();
	ScrollPane menuScroll = new ScrollPane();
	GridPane gridPane = new GridPane();
	Garden garden = new Garden();
	Stage hoverStage = new Stage();
	TitledPane plantTP = new TitledPane();
	TitledPane bushTP = new TitledPane();
	TitledPane treeTP = new TitledPane();
	TitledPane flowerTP = new TitledPane();
	TitledPane grassTP = new TitledPane();
	TitledPane decorTP = new TitledPane();
	TitledPane toDoTP = new TitledPane();
	Accordion ap = new Accordion();
	FlowPane toDoFlow = new FlowPane(Orientation.VERTICAL);
	BorderPane scrollBorder = new BorderPane();
	Button backButton = new Button("Home");
	Button backButton2 = new Button("Design");
	Button ecoButton = new Button("View Garden Info");
	TextField enterItem = new TextField();
	Stage theStage;
	Stage infoStage = new Stage();


	Button uploadButton = new Button("Upload Layout");
	Button saveGardenButton = new Button("Save");
	Button infoButton = new Button();

	//collections
	ArrayList<Image> objectImages = new ArrayList<Image>();
	ArrayList<String> objectNames = new ArrayList<String>();
	ArrayList<String> allPlantsinGarden = new ArrayList<String>();
	HashMap<String, Integer> animalsInGarden = new HashMap<String, Integer>();
	HashMap<String, Integer> insectsInGarden = new HashMap<String, Integer>();
	HashMap<String, Integer> bloomSzn = new HashMap<String, Integer>();
	HashMap<String, GardenObjects> gardenPlantsMap = new HashMap<String, GardenObjects>();
	HashMap<String, GardenObjects> gardenBushesMap = new HashMap<String, GardenObjects>();
	HashMap<String, GardenObjects> gardenTreesMap = new HashMap<String, GardenObjects>();
	HashMap<String, GardenObjects> gardenFlowersMap = new HashMap<String, GardenObjects>();
	HashMap<String, GardenObjects> gardenGrassesMap = new HashMap<String, GardenObjects>();
	HashMap<String, GardenObjects> gardenObjectsMap = new HashMap<String, GardenObjects>();

	//globals
	boolean flag = false;
	boolean backFlag = false;
	boolean backFlag2 = false;
	double gridH;
	double gridW;
	boolean sflag = false;
	String task;
	Image gridImage;
	String gardenName;
	String shapeName = "";

	//Constants
	final int HBOX_SPACING = 50;
	final int PADDING = 10;

	final String DROPDOWN_STYLE ="-fx-font-size: 14px; -f-font-family: verdana;";
	final String BACK_BUTTON_STYLE = "-fx-background-color: #8AED9D;-fx-font-size: 16px; -f-font-family: verdana;";
	final String INFO_TITLE_STYLE = "-fx-font-size: 16px; -f-font-family: verdana;";
	
	
	final int SCENEH = 700;
	final int SCENEW = 1000;
	final int GARDENX = 10;
	final int GARDENY = 10;
	final int OBJX = 50;
	final int OBJY = 100;
	final int DEFAULTFONT = 12;
	final int OBJINVENTORYHEIGHT = 100;
	final int BUTTONWH = 30;
	final int INSET = 0;
	final int PREFHEIGHT = 100;
	final int GAP = 0;
	final int IMGWH = 1;
	final int POPUPX = 600;
	final int POPUPY = 150;
	final int POPUPInanimateXY = 100;
	final int COLINDEX = 0;
	final double OFFSET = 0.0;
	final double HBOX_ANCHOR = 100.0;
	final int INFOBOX_HEIGHT = 250;
	final int INFOBOX_WIDTH = 450;
	final int GARDEN_PANE_PREF_SIZE = 600;
	final double GARDENPANE_TOP_ANCHOR = 15.0;
	final double VGAP = 0;
	final double HGAP = 0;
	final double OPACITY_VALUE = 0.5;
	double GRIDSCALE = 25;
	final int INSET_5 = 5;
	final int INSET_0 = 0;
	final int INSET_FIVE = 5;
	final double ECO_SCALE = 0.6;
	final double TEXT_WRAP_SCALE = 0.5;
	final int PLANT_HOVER_WRAP = 90;

	double scale = 25;


	/**
	 * default DesignView constructor
	 */
	public DesignView() {
		designController = new DesignController(this);
		plantGrid = new GridPane();
		bushGrid = new GridPane();
		treeGrid = new GridPane();
		flowerGrid = new GridPane();
		grassGrid = new GridPane();
		objectGrid = new GridPane();
		gridPane = new GridPane();
	}

	/**
	 * method to start the DesignView
	 * @param s the stage
	 */
	public void start(Stage s) {
		if(sflag) {
			loadSavedGarden(garden);
		}


		theStage = s;
		instructionsPopUp();

		//home button
		Image homeButton = new Image("home.PNG");
		ImageView home = new ImageView(homeButton);
		home.setFitHeight(BUTTONWH);
		home.setFitWidth(BUTTONWH);
		backButton.setGraphic(home);
		backButton.setStyle(BACK_BUTTON_STYLE);

		//draw garden view return
		Image draw = new Image("draw.PNG");
		ImageView drawButton = new ImageView(draw);
		drawButton.setFitHeight(BUTTONWH);
		drawButton.setFitWidth(BUTTONWH);
		backButton2.setGraphic(drawButton);
		backButton2.setStyle(BACK_BUTTON_STYLE);

		//upload Button
		Image upload = new Image("upload.PNG");
		ImageView uploadImg = new ImageView(upload);
		uploadImg.setFitHeight(BUTTONWH);
		uploadImg.setFitWidth(BUTTONWH);
		uploadButton.setGraphic(uploadImg);
		uploadButton.setStyle(BACK_BUTTON_STYLE);

		//save Button
		Image save = new Image("save.PNG");
		ImageView saveImg = new ImageView(save);
		saveImg.setFitHeight(BUTTONWH);
		saveImg.setFitWidth(BUTTONWH);
		saveGardenButton.setGraphic(saveImg);
		saveGardenButton.setStyle(BACK_BUTTON_STYLE);

		//info button
		Image info = new Image("info.PNG");
		ImageView infoImg = new ImageView(info);
		infoImg.setFitHeight(BUTTONWH);
		infoImg.setFitWidth(BUTTONWH);
		infoButton.setGraphic(infoImg);
		infoButton.setStyle(BACK_BUTTON_STYLE);

		//eco button
		ImageView ecoImg = new ImageView(info);
		ecoImg.setFitHeight(BUTTONWH);
		ecoImg.setFitWidth(BUTTONWH);
		ecoButton.setGraphic(ecoImg);
		ecoButton.setStyle(BACK_BUTTON_STYLE);
		ecoButton.setStyle(BACK_BUTTON_STYLE);

		tileLeft.setHgap(5);
		tileLeft.setPrefColumns(2);	
		tileLeft.setStyle("-fx-background-color: 75c6ff;");

		//iventory in scroll pane
		map2Inventory(gardenPlantsMap, plantGrid);
		map2Inventory(gardenObjectsMap, objectGrid);
		
		//Replace this with the commented code below
		//once filters work and setType in model doesn't cause an error
		/*
		map2Inventory(gardenPlantsMap, treeGrid);
		map2Inventory(gardenPlantsMap, bushGrid);
		map2Inventory(gardenPlantsMap, flowerGrid);
		map2Inventory(gardenPlantsMap, grassGrid);
		*/
		
		gardenBushesMap = (HashMap<String, GardenObjects>) findDesiredType(gardenPlantsMap, "Bush");
		gardenTreesMap = (HashMap<String, GardenObjects>) findDesiredType(gardenPlantsMap, "Tree");
		gardenFlowersMap = (HashMap<String, GardenObjects>) findDesiredType(gardenPlantsMap, "Flower");
		gardenGrassesMap = (HashMap<String, GardenObjects>) findDesiredType(gardenPlantsMap, "Grass");
		 
		map2Inventory(gardenTreesMap, treeGrid);
		map2Inventory(gardenBushesMap, bushGrid);
		map2Inventory(gardenFlowersMap, flowerGrid);
		map2Inventory(gardenGrassesMap, grassGrid);
		
		

		menuScroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		menuScroll.setContent(ap);		


		Text gardDim= new Text("Garden Dimensions: " + this.getgH() + " feet x " + this.getgW() + " feet");
		gardDim.setFont(Font.font("verdana", FontWeight.NORMAL, DEFAULTFONT));

		ap.getPanes().addAll(plantTP, treeTP, flowerTP, bushTP, grassTP, decorTP, toDoTP);


		plantTP.setText("All Plants");
		plantTP.setStyle(DROPDOWN_STYLE);
		plantTP.setContent(plantGrid);
		
		bushTP.setText("Bushes");
		bushTP.setStyle(DROPDOWN_STYLE);
		bushTP.setContent(bushGrid);
		
		treeTP.setText("Trees");
		treeTP.setStyle(DROPDOWN_STYLE);
		treeTP.setContent(treeGrid);
		
		flowerTP.setText("Flowers");
		flowerTP.setStyle(DROPDOWN_STYLE);
		flowerTP.setContent(flowerGrid);
		
		grassTP.setText("Grasses");
		grassTP.setStyle(DROPDOWN_STYLE);
		grassTP.setContent(grassGrid);

		decorTP.setText("Decorations");
		decorTP.setStyle(DROPDOWN_STYLE);
		decorTP.setText("Decorations");
		decorTP.setContent(objectGrid);

		toDoTP.setText("To Do List");
		toDoTP.setContent(toDoFlow);
		toDoTP.setStyle(DROPDOWN_STYLE);

		enterItem.setPromptText("Enter New Task");
		enterItem.setOnKeyPressed(writeTask);
		toDoFlow.getChildren().add(enterItem);

		scrollBorder.getChildren().addAll(plantTP, treeTP, flowerTP, bushTP, grassTP, decorTP, toDoTP);

		//set gridPane background to match green in DrawGardenView
		BackgroundFill grass = new BackgroundFill(Color.valueOf("#8aed9d"),  CornerRadii.EMPTY, Insets.EMPTY);
		Background gridBackground = new Background(grass);
		gridPane.setBackground(gridBackground);
		gridPane.setGridLinesVisible(true);
		gridPane.setVgap(GAP); 
		gridPane.setHgap(GAP); 

		Text text = new Text();
		String gardenNameText = garden.getName();
		text.setFont(Font.font("verdana", FontWeight.BOLD, DEFAULTFONT));
		text.setText(gardenNameText);
		HBox gardenNameHBox = new HBox(text);
		gardenNameHBox.setMargin(text, new Insets(PADDING, INSET, PADDING, INSET));

		gardenPane.setPrefHeight(GARDEN_PANE_PREF_SIZE);
		gardenPane.setPrefWidth(GARDEN_PANE_PREF_SIZE);

		//if making new garden.... not from saved
		if(!sflag) {
			Group g = new Group();

			putGrid(gridImage);

		}

		/**
		 * event handler for pressing the info button
		 */
		EventHandler<ActionEvent> infoButtonEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				instructionsPopUp();

			}
		};

		/**
		 * event handler for pressing the upload button
		 */
		EventHandler<ActionEvent> uploadButtonEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.*"));
				File selectedFile = fileChooser.showOpenDialog(theStage);
				shapeName = selectedFile.getName();
				garden.setGardenShapeName(shapeName);
				String filePath = selectedFile.getPath();
				
				

				//save image in resources to use again for loading
				//first check to see if it already exists
				File f = new File("src/main/resources/"+shapeName);
				boolean exists = false;

				if(f.exists()) {
					//System.out.println(exists);
					exists = true;}

				//if file doesnt already exist in resources folder, then delete it
				if(!exists) {
					try {
						Files.copy(Paths.get(filePath), Paths.get("src/main/resources/"+shapeName))	;			
					}
					catch (IOException e1){
						e1.printStackTrace();
					}
				}

				if (selectedFile != null) {

					FileInputStream inputstream = null;
					try {

						inputstream = new FileInputStream(selectedFile);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}

					Image gardenLayoutImage = new Image(inputstream);
					

					putBackgroundImage(gardenLayoutImage);
					

				} // in scaleImage we would subtract the scroll pane width from the panel (for first BackgroundSize.Auto), see if IM using right panes/borders
			}};
			

			/**
			 * event handler for pressing the save garden button
			 */
			EventHandler<ActionEvent> saveGardenButtonEvent = new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e) {
					
					garden.setHeight(gridH);
					garden.setWidth(gridW);
					
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Save");
					fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
					File file = fileChooser.showSaveDialog(theStage);
					designController.handleSave(garden, file);
					
					
					Image gardenImage = gardenPane.snapshot(new SnapshotParameters(), null);	
					BufferedImage bi = SwingFXUtils.fromFXImage(gardenImage, null);
			        try {
						ImageIO.write(bi, "png", new File("src/main/resources/" + file.getName() + ".png"));
						//System.out.println(file.getName());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			        
			        //if continuing from saved
			        if(gridImage != null) {
			        	BufferedImage biGrid = SwingFXUtils.fromFXImage(gridImage, null);
			        	try {
			        		ImageIO.write(biGrid, "png", new File("src/main/resources/gridImage_" + garden.getName()+ ".png"));
			        	} catch (IOException e2) {
			        		e2.printStackTrace();
			        	}
			        
			        }
					//Adding action on the menu item
					saveGardenButton.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							//Opening a dialog box
							File file = fileChooser.showSaveDialog(theStage);
							designController.handleSave(garden, file);
							
							
						}
					}
							);
				}
			};

			Text ecoText = new Text("Garden Overview");
			//ecoText.setStyle(DROPDOWN_STYLE);
			ecoText.setStyle(INFO_TITLE_STYLE);
			HBox topEco = new HBox(ecoText);
			//topEco.getChildren().add(ecoText);
			topEco.setStyle("-fx-background-color: #ffdd54");
			topEco.setAlignment(Pos.CENTER);
			EventHandler<ActionEvent> ecoButtonEvent = new EventHandler<ActionEvent>() { 				
				public void handle(ActionEvent e) 
				{ 
					Stage ecoStage = new Stage();
					BorderPane ecoBP = new BorderPane();
					HBox ecoHBox = new HBox();
					ecoHBox.getChildren().add(ecoFlow);
					//HBox topEco = new HBox();
					
					
					ecoHBox.setStyle("-fx-background-color: #ffffff");

					ecoHBox.setPadding(new Insets(INSET, INSET, INSET, PADDING));
					
					//fix title and margins
					topEco.setMargin(ecoText, new Insets(INSET_FIVE, INSET, INSET_FIVE, INSET));

					ecoBP.setCenter(ecoHBox);
					ecoBP.setTop(topEco);
					ecoBP.getChildren().add(gardDim);
					//fix height and width
					Scene sc = new Scene(ecoBP, SCENEW*ECO_SCALE, SCENEH*ECO_SCALE);
					ecoStage.setScene(sc);

					ecoStage.show();

				}
			};

			HBox hbox = new HBox(backButton, backButton2, uploadButton, saveGardenButton, infoButton, ecoButton);
			hbox.setSpacing(HBOX_SPACING);
			hbox.setMargin(backButton, new Insets(INSET, INSET, PADDING, INSET));
			hbox.setAlignment(Pos.TOP_LEFT);
			hbox.setStyle("-fx-background-color: #ffffff;");

			border.setTop(hbox);
			border.setCenter(gardenPane);
			border.setRight(menuScroll);
			
			Text gardName = new Text("Garden Name: "+ garden.getName() + "\n");
			Text gardDims = new Text("Garden Dimensions: " + this.getgH() + " feet x " + this.getgW() + " feet");

			gardName.setFont(Font.font("verdana", FontWeight.NORMAL, DEFAULTFONT));
			gardDims.setFont(Font.font("verdana", FontWeight.NORMAL, DEFAULTFONT));
			ecoFlow.getChildren().add(gardName);
			ecoFlow.getChildren().add(gardDims);

			backButton.setOnAction(backButtonEvent);
			backButton2.setOnAction(backButton2Event);
			uploadButton.setOnAction(uploadButtonEvent);
			saveGardenButton.setOnAction(saveGardenButtonEvent);
			infoButton.setOnAction(infoButtonEvent);
			ecoButton.setOnAction(ecoButtonEvent);

			//these dimensions can change later
			Scene sc = new Scene(border, SCENEW, SCENEH);
			s.setScene(sc);

			s.show();
			infoStage.toFront();
	}
	
	public void instructionsPopUp() {
		//Information for the help pane
		String information = "\n- Click 'Upload Layout' to add a an image of your garden layout"
				+ "\n- Drag and Drop plants onto your grid from the menu on the right"
				+ "\n- Right-click objects to delete them from your garden"
				+ "\n- Write tasks to do in the 'To Do' tab on menu on the right"
				+ "\nand press enter to add them"
				+ "\n- View your garden's impact on wildlife at the bottom of the screen"
				+ "\n- Press 'Save' to save the garden to your computer"
				+ "\n- Press 'Design' to enter dimensions for a new garden"
				+ "\n- Press the house icon to return to the home screen"
				+ "\n- Click the 'i' icon to review these instructions";
		
		Text instructions = new Text(information);
		Text title = new Text("Instructions");
		instructions.setStyle(DROPDOWN_STYLE);
		title.setStyle(INFO_TITLE_STYLE);
		BorderPane infobox = new BorderPane();
		HBox infoHBox = new HBox();
		infoHBox.getChildren().add(instructions);
		HBox topInfo = new HBox();

		topInfo.setStyle("-fx-background-color: #ffdd54");
		topInfo.setAlignment(Pos.CENTER);
		infoHBox.setStyle("-fx-background-color: #ffffff");
		infoHBox.setPadding(new Insets(INSET_0, INSET_0, INSET_0, PADDING));
		topInfo.getChildren().add(title);
		topInfo.setMargin(title, new Insets(INSET_5, INSET_0, INSET_5, INSET_0));

		infobox.setCenter(infoHBox);
		infobox.setTop(topInfo);
		Scene sc = new Scene(infobox, INFOBOX_WIDTH, INFOBOX_HEIGHT);
		infoStage.setScene(sc);

		infoStage.show();
	}

	/**
	 * method to update the eco flow pane
	 * @param gardenObjects a garden object
	 * @param b whether or not the object is already there
	 */

	public void updateEcoFlow(GardenObjects gardenObjects, boolean b) {//does Plant have methods for handling adding and removing?
		designController.handleEcoUpdate(gardenObjects, b, animalsInGarden, insectsInGarden, bloomSzn);

		ecoFlow.getChildren().removeAll();
		ecoFlow.getChildren().clear();

		String aT ="\nAnimals attracted by this garden: ";
		String iT="\nInsects attracted by this garden: ";
		String bT = "\nBlooming Seasons: ";		

		aT = map2EcoView(animalsInGarden, aT);
		iT = map2EcoView(insectsInGarden, iT);
		bT = map2EcoView(bloomSzn, bT);

		Text animalText = new Text(aT);
		animalText.setWrappingWidth(SCENEW*TEXT_WRAP_SCALE);
		Text insectText = new Text(iT);
		insectText.setWrappingWidth(SCENEW*TEXT_WRAP_SCALE);
		Text bloomText = new Text(bT);
		bloomText.setWrappingWidth(SCENEW*.5);
		
		Text recText = new Text("\nWe recommend these plants that like the same amount of shade: \n\t\t" + designController.handleRecommendations(garden.getStuffinGarden()));
				

		Text gardName = new Text("Garden Name: "+ garden.getName() + "\n");
		Text gardDim = new Text("Garden Dimensions: " + this.getgH() + " feet x " + this.getgW() + " feet");
		
		
		gardName.setFont(Font.font("verdana", FontWeight.NORMAL, DEFAULTFONT));
		gardDim.setFont(Font.font("verdana", FontWeight.NORMAL, DEFAULTFONT));
		animalText.setFont(Font.font("verdana", FontWeight.NORMAL, DEFAULTFONT));
		insectText.setFont(Font.font("verdana", FontWeight.NORMAL, DEFAULTFONT));
		bloomText.setFont(Font.font("verdana", FontWeight.NORMAL, DEFAULTFONT));
		recText.setFont(Font.font("verdana", FontWeight.NORMAL, DEFAULTFONT));
		
		ecoFlow.getChildren().add(gardName);
		ecoFlow.getChildren().add(gardDim);
		ecoFlow.getChildren().add(animalText);
		ecoFlow.getChildren().add(insectText);
		ecoFlow.getChildren().add(bloomText);
		ecoFlow.getChildren().add(recText);
		ecoFlow.setPrefHeight(PREFHEIGHT);

		//Accordion ecoAcc = new Accordion();
		//System.out.println("made accordion");
		//ecoAcc.getPanes().add(ecoTP);
		//ecoTP.setContent(ecoFlow);

	}//end updateEcoFlow
	
	/**
	 * puts objects into inventory from hashmap
	 * @param map hashmap
	 * @param grid gridpane
	 */
	public void map2Inventory(HashMap<String, GardenObjects> map, GridPane grid) {
		int rowIndex = 0;
		Set entrySet = map.entrySet();
		Iterator it = entrySet.iterator();
		while(it.hasNext()) {

			Map.Entry<String, GardenObjects> pair = (Map.Entry<String, GardenObjects>)it.next();
			Image i = pair.getValue().getImage();
			ImageView img = new ImageView(i);
			img.setPreserveRatio(true);
			img.setFitHeight(OBJINVENTORYHEIGHT);

			setNodeEvents(img, pair.getValue(), grid);

			grid.add(img, COLINDEX, rowIndex);
			rowIndex++;
		}
	}


	/**
	 * adds to garden stats given a hashmap and string
	 * @param map hashmap
	 * @param startString string
	 * @return String 
	 */

	public String map2EcoView(HashMap<String, Integer> map, String startString) {
		Set entrySet = map.entrySet();
		Iterator it = entrySet.iterator();
		while(it.hasNext()) {
			Map.Entry<String, GardenObjects> pair = (Map.Entry<String, GardenObjects>)it.next();
			if(pair.getKey().equals("N/a")) {
				continue;
			}

			if(!it.hasNext()) {
				startString += pair.getKey();
			}
			else {
				startString += pair.getKey() + ", ";
			}
		}		
		return startString;		
	}

	/**
	 * loads saved garden object
	 * @param garden the garden
	 */
	public void loadSavedGarden(Garden garden) {

		this.setgH(garden.getHeight());
		this.setgW(garden.getWidth());

		Image grid = null;
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File("src/main/resources/gridImage_" + garden.getName() + ".png"));
			grid = SwingFXUtils.toFXImage(bi, null);

		} catch (IOException e) {
		}
		
		putGrid(grid);

		if(garden.getGardenShapeName()!=null) {
			Image im = new Image(garden.getGardenShapeName());
			putBackgroundImage(im);
		}

		Iterator<GardenObjects> it = garden.getStuffinGarden().iterator();
		while(it.hasNext()) {
			GardenObjects go = it.next();
			Image i = new Image(go.getFilename());
			ImageView iv = new ImageView(i);
			iv.setX(go.getX());
			iv.setY(go.getY());
			iv.setPreserveRatio(true);
			iv.setFitHeight((double)GARDENY/this.getgH()*OBJY*go.getScale());
			iv.setOnMousePressed(event2 -> {
				//System.out.println("Drag check" + event2.isPrimaryButtonDown());
				if(event2.isPrimaryButtonDown()) {
					dragPress(event2, go, null);
				}
			});
			updateEcoFlow(go, true);
			gardenPane.getChildren().add(iv);
			iv.setOnMouseClicked(event -> removePlant(event, go));
		}
	}

	/**
	 * adds background image behind gardenPane
	 * @param im the image
	 */
	public void putBackgroundImage(Image im) {

		//check to see if garden is too big and needs to be rescaled
		designController.checkDimensions(gridH, gridW);
		//check to see if scale needs to change (garden is really small or really large)
		designController.checkScale(gridH, gridW);

		BackgroundSize scaleImage = new BackgroundSize(gridW*scale, gridH*scale, false, false, false, false);
		BackgroundImage backgroundImage = new BackgroundImage( im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, scaleImage);
		Background background = new Background(backgroundImage);
		gardenPane.setBackground(background);
	}

	/**
	 * puts background image of grid into gardenPane
	 * @param im image of grid
	 */
	public void putGrid(Image im) {
		
		BackgroundSize scaleImage = new BackgroundSize(gardenPane.getWidth() - scrollBorder.getWidth(), BackgroundSize.AUTO, false, false, false, false);
		//System.out.println(im);
		BackgroundImage backgroundImage = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, scaleImage);
		Background background = new Background(backgroundImage);
		garden.setGardenImage(SwingFXUtils.fromFXImage(im, garden.getGardenImage()));
		gardenPane.setBackground(background);
		ImageView gi = new ImageView(im);
		HBox gridHBox = new HBox();
		gridHBox.getChildren().add(gi);
		gardenPane.getChildren().add(gridHBox);
		gridHBox.setAlignment(Pos.CENTER);

		gardenPane.setLeftAnchor(gridHBox, HBOX_ANCHOR);
		gardenPane.setRightAnchor(gridHBox, HBOX_ANCHOR);
		gardenPane.setTopAnchor(gridHBox, HBOX_ANCHOR);
		gardenPane.setBottomAnchor(gridHBox, HBOX_ANCHOR);
		
		garden.setGridImage(im);
		gi.setOpacity(OPACITY_VALUE);
		gi.setOpacity(TEXT_WRAP_SCALE);


	}
	
	/**
	 * filters through hashmap of all garden objects to sort plant types
	 * @param plants a hashmap of the plant objects
	 * @param plantType a string of the desired plant type to be filtered
	 * @return result the filtered map of the desired type of plant
	 */
	public Map<String, GardenObjects> findDesiredType(HashMap<String, GardenObjects> plants, String plantType) {		
		//HashMap<String, GardenObjects> onlyOneType = item -> item.getType() = plantType;
		Map<String, GardenObjects> result = (Map<String, GardenObjects>) plants.entrySet()
		.stream()
		.filter(map -> plantType.equals(map.getValue().getType()))
		.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
		
		return result;
	}




	/******************************EVENTS******************************/

	/**
	 * method to remove plane
	 * @param event the right-click
	 * @param gardenObjects the object to be removed
	 */
	public void removePlant(MouseEvent event, GardenObjects gardenObjects) {
		if(event.getButton() == MouseButton.SECONDARY) {
			Node n = (Node)event.getSource();
			gardenPane.getChildren().remove(n);
			designController.removeObject(gardenObjects);
			updateEcoFlow(gardenObjects, false);
			garden.stuffInGarden.remove(gardenObjects);

		}
	}

	// back button to StartView
	/**
	 * handles back button by taking user to startView
	 */
	EventHandler<ActionEvent> backButtonEvent = new EventHandler<ActionEvent>() { 
		public void handle(ActionEvent e) {
			backFlag = !backFlag;
			designController.handleBackFlag();
		} 
	};

	/**
	 * handles other back button by taking user to drawGardenView
	 */
	EventHandler<ActionEvent> backButton2Event = new EventHandler<ActionEvent>() { 
		public void handle(ActionEvent e) 
		{ 	//System.out.println(backFlag2);
		backFlag2 = !backFlag2;
		//System.out.println(backFlag2);
		designController.handleBackFlag();
		} 
	};

	/**
	 * handles drag for plants
	 * @param event the mouse event
	 * @param go the garden object
	 * @param scale scale for the plant
	 */
	public void dragContinue(MouseEvent event, GardenObjects go, double scale) {
		ImageView n = (ImageView)event.getSource();

		n.setTranslateX(n.getTranslateX() + event.getX()-(n.getFitWidth()/2));
		n.setTranslateY(n.getTranslateY() + event.getY()-(n.getFitHeight()/2));
		go.setX((int)n.getTranslateX());
		go.setY((int)n.getTranslateY());
		n.setOnMouseClicked(event1->removePlant(event1, go));

		//set to null so there is no pop up in the garden because it is annoying
		n.setOnMouseEntered(null);
		n.setOnMouseExited(null);
		n.setPreserveRatio(true);

		//scale factor is (garden baseline Y/user garden Y)*object baseline Y* scale

		n.setFitHeight((double)GARDENY/this.getgH()*OBJY*scale); 	 	
	}

	/**
	 * handles dragging onto pane
	 * @param event mouse event
	 * @param gardenObjects object
	 * @param gp gridpane that object came from
	 */
	public void dragPress(MouseEvent event, GardenObjects gardenObjects, GridPane gp) {
		double scale = gardenObjects.getScale();
		int ri = -1;
		Node n = (Node)event.getSource();
		if(gp != null) {
			ri = gp.getRowIndex(n);
			gp.getChildren().remove(n);
		}

		AnchorPane.setTopAnchor(n, OFFSET);
		AnchorPane.setLeftAnchor(n, OFFSET);

		if(!gardenPane.getChildren().contains(n)) {
			gardenPane.getChildren().add(n);
		}

		n.setTranslateX(n.getTranslateX() + event.getX());
		n.setTranslateY(n.getTranslateY() + event.getY());

		if(!garden.getStuffinGarden().contains(gardenObjects)) {
			garden.addObject(gardenObjects);

		}
		updateEcoFlow(gardenObjects,true);

		n.setOnMouseDragged(event1->dragContinue(event1, gardenObjects, scale));
		n.setOnMouseClicked(event1->{
			if(event1.isSecondaryButtonDown()) {
				removePlant(event1, gardenObjects);

			}
		});
		n.setOnMousePressed(null);

		ImageView nv = new ImageView(gardenObjects.getImage());
		nv.setPreserveRatio(true);
		nv.setFitHeight(OBJINVENTORYHEIGHT);

		setNodeEvents(nv, gardenObjects, gp);

		if(gp != null){
			gp.add(nv, COLINDEX, ri);
		}
	}

	/**
	 * displays plant info when mouse hovers over it
	 * @param event MouseEvent for when the mouse hovers over a plant
	 * @param gardenObjects a gardenObject that is being hovered over
	 */
	public void plantHoverEnter(MouseEvent event, GardenObjects gardenObjects) {
		BorderPane hovBorder = new BorderPane();
		HBox topInfo = new HBox();
		HBox infoHBox = new HBox();
		Text hovText = new Text();
		Text title = new Text(gardenObjects.getName());
		

		Scene hs;
		if(!gardenObjects.getClass().equals(Plant.class)) {

			hovText.setWrappingWidth(PLANT_HOVER_WRAP);
			hovText.setTextAlignment(TextAlignment.CENTER);

			hs = new Scene(hovBorder, POPUPInanimateXY , POPUPInanimateXY);
		}
		else {
			hovText = new Text(gardenObjects.toString()); 
			hovText.setWrappingWidth(590);
			infoHBox.getChildren().add(hovText);
			hovText.setStyle(INFO_TITLE_STYLE);
			hs = new Scene(hovBorder, POPUPX, POPUPY);
		}
		
		title.setStyle(INFO_TITLE_STYLE);
		topInfo.getChildren().add(title);
		hovBorder.setCenter(infoHBox);
		hovBorder.setTop(topInfo);
		
		topInfo.setStyle("-fx-background-color: #ffdd54");
		topInfo.setAlignment(Pos.CENTER);
		infoHBox.setStyle("-fx-background-color: #ffffff");
		infoHBox.setPadding(new Insets(INSET_0, INSET_0, INSET_0, PADDING));
		
		hoverStage.setScene(hs);
		hoverStage.show();

	}

	/**
	 * closes window when mouse stops hovering over plant
	 */
	public void plantHoverExit(){
		hoverStage.close();
	}
	
	/**
	 * handles to do list
	 */
	EventHandler<KeyEvent> writeTask = new EventHandler<KeyEvent>() { 
		@Override
		public void handle(KeyEvent event) {
			if(event.getCode()==KeyCode.ENTER) {
				task = enterItem.getText();
				CheckBox cb1 = new CheckBox();
				cb1.setText(task);
				toDoFlow.getChildren().add(cb1);
			}

		}
	};

	/**
	 * sets nodes events
	 * @param n image node 
	 * @param go gardenObject
	 * @param gp gridpane that object originally came from
	 */
	public void setNodeEvents(Node n, GardenObjects go, GridPane gp) {    	
		n.setOnMousePressed(event2 -> {
			if(event2.isPrimaryButtonDown()) {
				dragPress(event2, go, gp);
			}
		});

		n.setOnMouseEntered(event -> plantHoverEnter(event, go));
		n.setOnMouseExited(event -> plantHoverExit());
		n.setOnMouseClicked(event -> removePlant(event, go));

	}

	/******************************SETTERS AND GETTERS******************************/

	/**
	 * getter for shape name
	 * @return shapeName name of shape
	 */
	public String getShapeName() {
		return shapeName;
	}

	/**
	 * sets name for shape
	 * @param shapeName name of shape
	 */
	public void setShapeName(String shapeName) {
		this.shapeName = shapeName;
	}

	/**
	 * gets flag
	 * @return boolean flag
	 */
	public boolean getFlag() {
		return this.flag;
	}

	/**
	 * gets back flag
	 * @return boolean backFlag
	 */
	public boolean getBackFlag() {
		return this.backFlag;
	}

	/**
	 * gets gets back flag 2
	 * @return boolean backFlag2
	 */
	public boolean getBackFlag2() {
		return this.backFlag2;
	}

	/**
	 * sets grid height
	 * @param gH height of the grid
	 */
	public void setgH(double gH) {
		this.gridH = gH;
	}

	/**
	 * sets grid width
	 * @param gW width of the grid
	 */
	public void setgW(double gW) {
		this.gridW = gW;
	}

	/**
	 * getter for grid height
	 * @return double gridH
	 */
	public double getgH() {
		return this.gridH;
	}

	/**
	 * getter for grid width
	 * @return double gridW
	 */
	public double getgW() {
		return this.gridW;
	}

	/** 
	 * setter for s flag
	 * @param sflag the flag
	 */
	public void setSflag(boolean sflag) {
		this.sflag = sflag;
	}

	/**
	 * getter for gridPane
	 * @return GridPane gridPane
	 */
	public GridPane  getGridPane() {
		return this.gridPane;
	}

	/**
	 * setter for object images
	 * @param objectImages images of objects
	 */
	public void setObjectImages(ArrayList<Image> objectImages) {
		this.objectImages = objectImages;
	}

	/**
	 * setter for object names
	 * @param objectNames names of objects
	 */
	public void setObjectNames(ArrayList<String> objectNames) {
		this.objectNames = objectNames;
	}

	/**
	 * setter for object map
	 * @param hashMap the hash map
	 */
	public void setObjectMap(HashMap<String, GardenObjects> hashMap) {
		this.gardenObjectsMap = hashMap;
	}

	/**
	 * setter for plant map
	 * @param hashMap the hash map
	 */
	public void setPlantMap(HashMap<String, GardenObjects> hashMap) {
		this.gardenPlantsMap = hashMap;
	}

	/***
	 * getter for the stage
	 * @return Stage theStage
	 */
	public Stage getStage() {
		return this.theStage;
	}

	/**
	 * getter for the garden
	 * @return Garden garden
	 */
	public Garden getGarden() {
		return this.garden;
	}

	/**
	 * setter for the garden
	 * @param garden the garden
	 */
	public void setGarden(Garden garden) {
		this.garden = garden;
	}

	/**
	 * setter for the grid image
	 * @param im the image
	 */
	public void setGridImage(Image im) {
		this.gridImage = im;
	}
	
	/**
	 * getter method for scale of garden
	 * @return scale 
	 */
	public double getScale() {
		return scale;
	}
	/**
	 * setter method for scale of garden
	 * @param scale new scale for garden
	 */
	public void setScale(double scale) {
		this.scale = scale;
	}
}


