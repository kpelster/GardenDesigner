import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout.Alignment;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
//import javafx.*;

public class CompareView {

	BorderPane border = new BorderPane(); 
	HBox t1 = new HBox();
	HBox t3 = new HBox();
	SavedGardenView sgv = new SavedGardenView();
	SavedGardenController sgController = new SavedGardenController(sgv);
	CompareController cc;
	Stage s;
	boolean hFlag = false;
	boolean cFlag = false;
	final int IMGWH = 25;



	// load garden buttons
	Button load1 = new Button("Load Garden 1");
	Button load2 = new Button("Load Garden 2");
	Button cont1 = new Button("Continue Garden Design");
	Button cont2 = new Button("Continue Garden Design");
	Button homeButton = new Button("Home");
	Button infoButton = new Button();
	
	// creating border panes to load gardens into
	BorderPane bpleft = new BorderPane();
	BorderPane bpright = new BorderPane();
	HBox displayHbox = new HBox(bpleft,bpright);
	Stage instructionStage = new Stage();

	final String BACK_BUTTON_STYLE = "-fx-background-color: #ffffff;-fx-font-size: 16px; -f-font-family: verdana;";
	final String BUTTON_DESIGN = "-fx-background-color: #ffdd54; -fx-text-fill: #000000; -fx-font-size: 16px; -f-font-family: verdana;";
	final String BUTTON_DESIGN2 = "-fx-background-color: #8AED9D; -fx-text-fill: #000000; -fx-font-size: 16px; -f-font-family: verdana;";
	final int HEADER_FONT_SIZE = 32;
	final int HBOX_SPACING = 200;
	final int BUTTONHEIGHT = 30;
	final int BUTTONWIDTH = 30;
	final int IMAGEHEIGHT = 100;
	final int TILESPACING = 50;
	final int SCENEH = 700;
	final int SCENEW = 1000;
	final int INFOBOX_HEIGHT = 200;
	final int INFOBOX_WIDTH = 600;
	final int BUTTONWH = 30;
	final int PADDING = 10;
	final int POPUP_WIDTH = 700;
	final int INSTRUCTIONS_HEIGHT = 300;
	final int INSET_TOP = 100;
	final int INSET_RIGHT = 80;
	final int INSET_BOTTOM = 0;
	final int INSET_LEFT = 80;
	final int LEFT_PADDING = 100;
	final int INSET_ZERO = 0;
	final int MARGIN_INSET = 5;
	final int HBOX_LOAD_SPACING = 325;
	final int IMAGE_WH = 350;

	final String INFO_TITLE_STYLE = "-fx-font-size: 16px; -f-font-family: verdana;";
	final String INFO_STYLE ="-fx-font-size: 14px; -f-font-family: verdana;";

	//globals for files to be compared
	File selectedFile1;
	File selectedFile2;

	public boolean gethFlag() {
		return hFlag;
	}

	public Stage getStage() {
		return s;
	}

	public CompareView() {
		cc = new CompareController(this);
	}

	/**
	 *Pop up for instructions that is called at start of page and if "i" is pressed 
	 */
	
	private void compareViewInstructionsPopUp() {
		BorderPane instructions = new BorderPane();
		Text instructionText = new Text(
				"\n1. Click the 'Load Garden' buttons to load 2 gardens side by side "
						+ "\n2. If you wish to continue designing one of your gardens, press \n 'Continue Saved Garden' "
						+ "button above the Garden you would like to edit "
						+ "\n3. Press the Home button to go back to the start page"
						+ "\n4. Click the 'i' button to see this info again!");
		
		Text instruction = new Text("Instructions");
		instructionText.setStyle(INFO_TITLE_STYLE);
		instructions.setCenter(instructionText);

		Stage infoStage = new Stage();
		BorderPane infobox = new BorderPane();
		HBox infoHBox = new HBox();
		infoHBox.getChildren().add(instructions);
		HBox topInfo = new HBox();
		topInfo.setStyle("-fx-background-color: #ffdd54");
		topInfo.setAlignment(Pos.CENTER);
		infoHBox.setStyle("-fx-background-color: #ffffff");
		infoHBox.setPadding(new Insets(INSET_ZERO, INSET_ZERO, INSET_ZERO, PADDING));
		Text title = new Text("Instructions");
		topInfo.getChildren().add(title);
		topInfo.setMargin(title, new Insets(MARGIN_INSET, INSET_ZERO, MARGIN_INSET, INSET_ZERO));
		infobox.setCenter(infoHBox);
		infobox.setTop(topInfo);
		Scene sc = new Scene(infobox, INFOBOX_WIDTH, INFOBOX_HEIGHT);
		infoStage.setScene(sc);

		infoStage.show();
	}	


	// goes to the DesignView window
	EventHandler<ActionEvent> homeEvent = new EventHandler<ActionEvent>() { 
		public void handle(ActionEvent e) 
		{ 
			//disables the handleFlag function every other time since flag is false
			hFlag = !hFlag;
			cc.handleFlag();
		} 
	}; 


	EventHandler<ActionEvent> load1Event = new EventHandler<ActionEvent>() { 
		public void handle(ActionEvent e) 
		{ 
			loadImage(bpleft, cont1);

		} 
	}; 

	EventHandler<ActionEvent> load2Event = new EventHandler<ActionEvent>() { 
		public void handle(ActionEvent e) 
		{ 
			loadImage(bpright, cont2);

		}
	};


	EventHandler<ActionEvent> contEvent = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			cFlag = !cFlag;
			Button b = (Button) e.getSource();
			FileInputStream inputstream;
			File selectedFile = null;
			if(b == cont1) {
				if (selectedFile1 != null) {
					inputstream = null;
					selectedFile = selectedFile1;
				}
			}
			else if(b == cont2) {
				if (selectedFile2 != null) {
					inputstream = null;
					selectedFile = selectedFile2;
				}
			}
			try {
				inputstream = new FileInputStream(selectedFile);
				ObjectInputStream oi = new ObjectInputStream(inputstream);
				try {
					cc.loadGardenFile(oi);
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				}
				oi.close();
				inputstream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	};
	
	/**
	 * loads image of saved garden into appropriate pane
	 * @param bp the border pane that the object is to be added to
	 * @param bb the button that was pressed. used to decide which selectedFile global to use
	 */
	public void loadImage(BorderPane bp, Button bb) {
		//load garden into hbox pane
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
		String gardenPath = "";
		if(bb == cont1) {
			selectedFile1 = fileChooser.showOpenDialog(getStage());
			gardenPath = "src/main/resources/" + selectedFile1.getName() + ".png";
		}
		else if(bb == cont2) {
			selectedFile2 = fileChooser.showOpenDialog(getStage());
			gardenPath = "src/main/resources/" + selectedFile2.getName() + ".png";
		}
		Image myImage1 = null;
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File(gardenPath));
			myImage1 = SwingFXUtils.toFXImage(bi, null);

		} catch (IOException e1) {
		}
		ImageView iv = new ImageView(myImage1);
		iv.setFitWidth(IMAGE_WH);
		iv.setFitHeight(IMAGE_WH);
		bp.setCenter(iv);
		bp.setTop(bb);
		bb.setAlignment(Pos.CENTER);
		bp.setMargin(bb, new Insets(INSET_TOP,INSET_RIGHT,INSET_BOTTOM,INSET_LEFT));
	}	

	/**
	 * start method for the compare view stage
	 * @param s stage object from previous page
	 */
	public void start (Stage s) {
		this.s = s; 

		//load and continue design buttons
		load1.setOnAction(load1Event);
		load2.setOnAction(load2Event);
		load1.setStyle(BUTTON_DESIGN2);
		load2.setStyle(BUTTON_DESIGN2);
		cont1.setStyle(BUTTON_DESIGN2);
		cont2.setStyle(BUTTON_DESIGN2);
		cont1.setOnAction(contEvent);
		cont2.setOnAction(contEvent);
		
		//home button
		Image homeImg = new Image("homeGreen.PNG");
		ImageView home = new ImageView(homeImg);
		home.setFitHeight(IMGWH);
		home.setFitWidth(IMGWH);
		homeButton.setGraphic(home);
		homeButton.setStyle(BUTTON_DESIGN2);
		homeButton.setOnAction(homeEvent);

		Text compareText = new Text("Compare Gardens");
		compareText.setFont(Font.font("verdana", FontWeight.BOLD, HEADER_FONT_SIZE));
		//added HBox to border top so the background could be changed
		HBox designButtonHbox = new HBox();
		designButtonHbox.getChildren().add(homeButton);
		designButtonHbox.getChildren().add(compareText);
		designButtonHbox.getChildren().add(infoButton);

		designButtonHbox.setSpacing(HBOX_SPACING);
		BackgroundFill hboxBackground = new BackgroundFill(Color.valueOf("#8AED9D"), null, null);
		Background backgroundTop = new Background(hboxBackground);
		designButtonHbox.setBackground(backgroundTop);
	
		//info button
		Image info = new Image("infoGreen.PNG");
		ImageView infoImg = new ImageView(info);
		infoImg.setFitHeight(BUTTONWH);
		infoImg.setFitWidth(BUTTONWH);
		infoButton.setGraphic(infoImg);
		infoButton.setStyle("-fx-background-color: #8AED9D");
		
		EventHandler<ActionEvent> infoView = new EventHandler<ActionEvent>() { 				
			public void handle(ActionEvent e) 
			{ 
				compareViewInstructionsPopUp();
			}
		};
		
		HBox hbox = new HBox(load1, load2);
		hbox.setSpacing(HBOX_LOAD_SPACING);
		hbox.setAlignment(Pos.TOP_CENTER);
		border.setBottom(hbox);
		displayHbox.setSpacing(SCENEW/10);//make screensize/2
		displayHbox.setPadding(new Insets(INSET_ZERO, INSET_ZERO, INSET_ZERO, LEFT_PADDING));

		infoButton.setOnAction(infoView);
		border.setTop(designButtonHbox);
		border.setCenter(displayHbox);		

		Scene sc = new Scene(border, SCENEW, SCENEH);

		s.setScene(sc);
		s.show();
		compareViewInstructionsPopUp(); 
	}
	
	/**
	 * getter method for getting c flag
	 * @return cFlag boolean for cFlag
	 */
	public boolean getcFlag() {
		return cFlag;
	}

	
	/**
	 * setter method for c flag
	 * @param cFlag boolean for cFlag
	 */
	public void setcFlag(boolean cFlag) {
		this.cFlag = cFlag;
	}


}
