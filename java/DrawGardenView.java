	import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import com.sun.prism.paint.ImagePattern;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage; 
	
/**
 * 
 * @author kara pelster, erin wallace, autumn stubbs, kenzie girvan, matt walkley
 *
 */
	
public class DrawGardenView {


	BorderPane border = new BorderPane();
	BorderPane centerBorder = new BorderPane();
	Stage instructionStage = new Stage();
	Stage catchStage = new Stage();
	TilePane topRibbon = new TilePane();
	TilePane dimAndName = new TilePane(); //RENAME
	GridPane gridPane = new GridPane();
	boolean clearButtonFlag = false;
	Button infoButton = new Button();
	Button homeButton = new Button();
	boolean backFlag = false;
	Stage duplicateStage = new Stage();
	Stage infoStage = new Stage();
	
	double gH;
	double gW;
	
	double originalH;
	double originalW;
	
	final int TILEGAP = 5;
	final int COL = 2;
	final int VGAP = 0;
	final int HGAP = 0;
	final int INSET1 = 10;

	final int INSET2 = 0;
	final int INSET5 = 5;
	final int SCENEH = 700;
	final int SCENEW = 1000;
	final int INFOBOX_HEIGHT = 100;
	final int INFOBOX_WIDTH = 250;
	final int POPUP_WIDTH = 700;
	final int INSTRUCTIONS_HEIGHT = 300;
	final int EMPTY_DIMS_HEIGHT = 40; 
	final int BUTTONWH = 30;
	final int PADDING = 10;
	final int IMGWH = 25;
	final int POPUPX = 600;
	final int POPUPY = 175;
	final int NO_INSET = 0;
	final int INSET_5 = 5;
	final int INSET_0 = 0;
	final int EXAMPLE_GRIDW = 650;
	final int EXAMPLE_GRIDH = 400;

	final String INFO_TITLE_STYLE = "-fx-font-size: 16px; -f-font-family: verdana;";
	final String CENTER_HBOX_STYLE = "-fx-font-size: 24px; -f-font-family: verdana;";
	final String INFO_STYLE ="-fx-font-size: 14px; -f-font-family: verdana;";
	final String BUTTON_DESIGN = "-fx-background-color: #ffdd54; -fx-text-fill: #000000; -fx-font-size: 14px; -f-font-family: verdana;";
	final String TEXT_INPUT_DESIGN = "-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 14px; -f-font-family: verdana;";
	
	double GRIDSCALE = 25;
	
	boolean flag = false;
	Stage stage;
	DrawGardenController dgc;
	transient WritableImage gridImage;
	
	String gardenName;
	
	/**
	 * constructor for DrawGardenView
	 */
	public DrawGardenView() {
		dgc = new DrawGardenController(this);
	}


	/**
	 * starts DrawGardenView
	 * @param s the stage
	 */
	public void start(Stage s) {
		
		stage = s;
		instructionsPopUp();
	
		Button designGarden = new Button("Start Garden Design");
		Button submitButton = new Button("Submit Dimensions and Name");

		//start view return
		Image homeImg = new Image("homeYellow.PNG");
		ImageView home = new ImageView(homeImg);
		home.setFitHeight(IMGWH);
		home.setFitWidth(IMGWH);
		homeButton.setGraphic(home);
		homeButton.setStyle(BUTTON_DESIGN);

		TextField tfW = new TextField();
		tfW.setPromptText("Enter Width in Feet");
		tfW.setStyle(TEXT_INPUT_DESIGN);
		
		TextField tfH = new TextField();
		tfH.setPromptText("Enter Height in Feet");
		tfH.setStyle(TEXT_INPUT_DESIGN);
		
		TextField tfName = new TextField();
		tfName.setPromptText("Name Your Garden");
		tfName.setStyle(TEXT_INPUT_DESIGN);
		
		//button design
		designGarden.setStyle(BUTTON_DESIGN);
		submitButton.setStyle(BUTTON_DESIGN);
		
		//info button
		Image info = new Image("infoGreen.PNG");
		ImageView infoImg = new ImageView(info);
		infoImg.setFitHeight(BUTTONWH);
		infoImg.setFitWidth(BUTTONWH);
		infoButton.setGraphic(infoImg);
		infoButton.setStyle("-fx-background-color: #8AED9D");

		Text topBorderText = new Text(" \n Enter dimensions corresponding to the width and height of your garden as pictured below. \n Your garden may not be identical in shape to the example garden below.");
		topBorderText.setStyle(CENTER_HBOX_STYLE);
		topBorderText.setTextAlignment(TextAlignment.CENTER);
		HBox topBorderH = new HBox(topBorderText);
		//gridPane.getChildren().add(trynew, 10, 10);
		centerBorder.setTop(topBorderH);
		
		topBorderH.setAlignment(Pos.BOTTOM_CENTER);
		
		BufferedImage bi = null;
		Image exampleGrid = null;
		try {
			bi = ImageIO.read(new File("src/main/resources/exampleGrid.png"));
			exampleGrid = SwingFXUtils.toFXImage(bi, null);

		} catch (IOException e) {
		}
		
		BackgroundSize scaleImage = new BackgroundSize(EXAMPLE_GRIDW, EXAMPLE_GRIDH, false, false, false, false);
		BackgroundImage backgroundImage = new BackgroundImage( exampleGrid, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, scaleImage);
		
		Background background = new Background(backgroundImage);
		gridPane.setBackground(background);
		
		/**
		 * event handler that sets garden name and dimensions and moves to designView
		 */
		EventHandler<ActionEvent> newGardenEvent = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{ 

				flag = !flag;
				gardenName = tfName.getText();
				
				File tmpFile = null;
				tmpFile =  new File("src/main/resources/gridImage_" + gardenName + ".png");

				if(tmpFile.exists()) {
					catchDuplicateName();
					flag = false;
					return;
				}
				
				if(tfW.getText().equals("")||tfH.getText().equals("")||gardenName.equals("")) {
					catchEmptyDimensions();
					flag = false;
					return;
				}
			
				
				gW = Integer.parseInt(tfW.getText());
				gH = Integer.parseInt(tfH.getText());
				
				originalW = gW;
				originalH = gH;
				
				dgc.checkDimensions(gH, gW);
			    dgc.checkScale(gH, gW);
		        
				makeGrid(gH, gW, gridPane);

				dgc.handleFlag();
				
				
			} 
		}; 

		dimAndName.setHgap(TILEGAP);
		dimAndName.setPrefColumns(COL);	

		border.setBottom(dimAndName);
		border.setCenter(centerBorder);
		centerBorder.setCenter(gridPane);
		border.setTop(topRibbon);
		dimAndName.setAlignment(Pos.CENTER);
		
	    //top and bottom of border pane styling
	    BackgroundFill white = new BackgroundFill(Color.valueOf("#8aed9d"),  CornerRadii.EMPTY, Insets.EMPTY);
	    Background topBackground = new Background(white);
	    topRibbon.setBackground(topBackground);
	    topRibbon.setMargin(submitButton, new Insets(INSET1, NO_INSET, INSET1, NO_INSET));  
	    dimAndName.setBackground(topBackground);
	    dimAndName.setMargin(tfH, new Insets(INSET1, NO_INSET, INSET1, NO_INSET));
		
	    dimAndName.getChildren().add(tfW);
		dimAndName.getChildren().add(tfH);
		dimAndName.getChildren().add(tfName);
		
		topRibbon.getChildren().add(homeButton);
		topRibbon.getChildren().add(designGarden);
		//topRibbon.getChildren().add(submitButton);
		topRibbon.getChildren().add(infoButton);
		
		//submitButton.setOnAction(submitDimEvent);
		designGarden.setOnAction(newGardenEvent);
		infoButton.setOnAction(infoView);
		homeButton.setOnAction(backButtonEvent);
		
		
		//these dimensions can change later
		Scene sc = new Scene(border, SCENEW, SCENEH);
		s.setScene(sc);
		s.show();
		
		infoStage.toFront();
		
	}
	

	/**
	 * creates pop up for page instructions when button is pressed
	 */
	EventHandler<ActionEvent> infoView = new EventHandler<ActionEvent>() { 				
		public void handle(ActionEvent e) 
		{ 
			instructionsPopUp();

		}
		
	};
	
	/**
	 * event handler for back button that goes back to startView
	 */
	EventHandler<ActionEvent> backButtonEvent = new EventHandler<ActionEvent>() { 
		public void handle(ActionEvent e) 
		{ 	
			backFlag = !backFlag;
			dgc.handleBackFlag();
		} 
	};
	
	/**
	 * handles situations when no dimensions are entered
	 */
	void catchEmptyDimensions() {
		BorderPane catchTextWindow = new BorderPane();
		Text catchText = new Text("You must enter the dimensions and name of your garden before starting Garden Design");//does this need to be changed??
		catchText.setStyle(INFO_TITLE_STYLE);
		catchTextWindow.setCenter(catchText);
		Scene hs = new Scene(catchTextWindow, POPUP_WIDTH, EMPTY_DIMS_HEIGHT);
		catchStage.setScene(hs);
		catchStage.show();
		
	}
	
	/**
	 * handles situations when a duplicate name is entered
	 */
	void catchDuplicateName() {
		duplicateNamePopUp();
	}
	
	/**
	 * pop up for when the user enters a duplicate name
	 */
	void duplicateNamePopUp() {
		BorderPane instructions = new BorderPane();
		Text instructionText = new Text("There is already a garden with this name"
				+ "\nPlease choose a new name");
		instructionText.setStyle(INFO_TITLE_STYLE);
		instructions.setCenter(instructionText);
		Scene popUp = new Scene(instructions, POPUPX, POPUPY);
		duplicateStage.setScene(popUp);
		duplicateStage.show();
	}
	
	/**
	 * creates grid for garden using height and width. sets the global image gridImage
	 * @param gH grid height
	 * @param gW grid width
	 * @param grid the GridPane
	 */
	public void makeGrid(double gH, double gW, GridPane grid) {
		
		Group gridLines = new Group();
		
		grid.setGridLinesVisible(true);
		grid.setVgap(VGAP); 
		grid.setHgap(HGAP); 
	    //set gridPane background to be green
	    BackgroundFill grass = new BackgroundFill(Color.valueOf("#8aed9d"),  CornerRadii.EMPTY, Insets.EMPTY);
	    Background gridBackground = new Background(grass);
	    grid.setBackground(gridBackground);

		grid.setMaxSize(GRIDSCALE*gW, GRIDSCALE*gH);
		for (int x = 0 ; x < gW ; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
            Line line = new Line(x, INSET_0, x, gH);
            gridLines.getChildren().add(line);
        }
        
        for (int y = 0 ; y < gH ; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
            Line line = new Line(y, INSET_0, y, gW);
            gridLines.getChildren().add(line);
        }

        gridImage = grid.snapshot(new SnapshotParameters(), null);	
        System.out.println(gridImage);
	}
	
	
	/**
	 * creates instruction pop up. no param or return
	 */
	private void instructionsPopUp() {
		
		BorderPane infobox = new BorderPane();
		HBox infoHBox = new HBox();
		HBox topInfo = new HBox();
		
		String information = "- Enter dimensions in feet"
				+ "\n- Enter garden name"
				+ "\n- Press 'Start Garden Design' to begin";
		Text instructions = new Text(information);
		Text title = new Text("Instructions");
		instructions.setStyle(INFO_STYLE);
		title.setStyle(INFO_TITLE_STYLE);
		
		infoHBox.getChildren().add(instructions);
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
		infoStage.toFront();
	} 
	
	
	/**
	 * sets flag
	 * @return boolean flag
	 */
	public boolean getFlag() {
		return this.flag;
	}

	/**
	 * gets stage
	 * @return Stage stage
	 */
	public Stage getStage() {
		return this.stage;
	}

	/**
	 * gets grid height
	 * @return int gH
	 */
	public double getgH() {
		return this.gH;
	}

	/**
	 * gets grid width
	 * @return int gW
	 */
	public double getgW() {
		return this.gW;
	}
	
	/**
	 * setter method for height of garden
	 * @param h height to be set
	 */
	public void setgH(double h) {
		this.gH = h;
		System.out.println("new h in dgv setting: " + this.gH);
	}
	
	/**
	 * setter method for width of garde 
	 * @param w width to be set
	 */
	public void setgW(double w) {
		this.gW = w;
	}
	
	/**
	 * gets garden name
	 * @return String gardenName
	 */
	public String getGardenName() {
		return this.gardenName;
	}
	
	/**
	 * gets grid image
	 * @return Image gridImage
	 */
	public Image getGridImage() {
		return this.gridImage;
	}
	
	/**
	 * gets back flag
	 * @return boolean backFlag
	 */
	public boolean getBackFlag() {
		return this.backFlag;
	}
	
	/**
	 * setter method for scale of garden
	 * @param scale scale of garden size
	 */
	
	
	public void setScale(double scale) {
		this.GRIDSCALE = scale;
	}
	
	/**
	 * getter method for user entered height
	 * @return double of height user entered
	 */
	public double getOriginalH() {
		return originalH;
	}

	/**
	 * setter method for original height user entered
	 * @param originalH  height user entered
	 */
	public void setOriginalH(double originalH) {
		this.originalH = originalH;
	}

	/**
	 * getter method for original width user entered
	 * @return double of width user entered
	 */
	public double getOriginalW() {
		return originalW;
	}

	/**
	 * setter method for original width user entered
	 * @param originalW the original width the user entered
	 */
	public void setOriginalW(double originalW) {
		this.originalW = originalW;
	}

}


