package gameSpaces;

import application.Main;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;
import monopoly.Player;

public abstract class GameSpace {
	private final int boardRank;
	private Rectangle image;
	private TextArea textArea;
	private final String colour;
	
	GameSpace(int boardRank, String colour) {
		this.boardRank = boardRank;
		this.colour = colour;
		this.image = new Rectangle(50, 50, javafx.scene.paint.Color.valueOf(colour));
		this.textArea = new TextArea();
		this.textArea.setVisible(false);
		this.textArea.setMinWidth(250);
		this.textArea.setMinHeight(250);
		this.textArea.setEditable(false);
		this.textArea.setStyle("text-area-background: "+ colour + ";" + 
				"-fx-text-fill:white;" +
				"-fx-font-size: 19px");
		this.textArea.setWrapText(true);
		
		this.image.setOnMouseClicked(e ->{
			GameSpace[] spaces = Main.game.getSpaces();
			for (int i=0; i<spaces.length; i++) {
				if (!spaces[i].equals(this) && spaces[i] instanceof Property) {
					Property property = (Property)spaces[i];
					property.getTextArea().setVisible(false);
					property.getBtnPurchase().setVisible(false);
					property.getBtnTrade().setVisible(false);
					property.getBtnMortgage().setVisible(false);
					property.getBtnPurchaseHouse().setVisible(false);
				} else if (!spaces[i].equals(this) && !(spaces[i] instanceof Property)) {
					spaces[i].getTextArea().setVisible(false);
				}
			}
			
			if (this.textArea.isVisible()) {
				this.textArea.setVisible(false);
			} else {
				this.textArea.setVisible(true);
			}
		});
	}

	public abstract void setActionLanded(Player player);
	
	public int getBoardRank() {
		return boardRank;
	}

	public Rectangle getImage() {
		return image;
	}

	public void setImage(Rectangle image) {
		this.image = image;
	}

	public TextArea getTextArea() {
		return this.textArea;
	}

	public void setText(String text) {
		this.textArea.setText(text);
	}
	
	public String getColour() {
		return this.colour;
	}
	
}
