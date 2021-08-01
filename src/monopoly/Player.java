package monopoly;

import java.util.ArrayList;
import java.util.HashMap;

import application.Main;
import gameSpaces.GameSpace;
import gameSpaces.Property;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * 
 * @param propertyList A HashMap that maps a property to an integer that represents the number of houses built on it 
 */
public class Player {
	private int money;
	private boolean turnRolled;
	private int boardRank;
	private String gamePiece;
	private HashMap<Property, Integer> propertyList;
	private Circle image = new Circle(7);
	
	public Player(int money, String gamePiece) {
		this.money = money;
		this.turnRolled = false;
		this.boardRank = 0;
		this.gamePiece = gamePiece;
		this.propertyList = new HashMap<Property, Integer>();
//		String[] colours = {"VIOLET","PURPLE","DARKGREEN","MAROON","THISTLE","INDIGO"};
		this.image.setFill(Color.color(Math.random(), Math.random(), Math.random(), 1));
		this.image.setStroke(javafx.scene.paint.Color.valueOf("white"));
		this.image.setStrokeWidth(1);
	}
	
	public Player() {
		this.money = 0;
		this.boardRank = -1;
		this.gamePiece = "NO-ONE";
		this.propertyList = new HashMap<Property, Integer>();
	}

	public int rollTurn() {
		this.turnRolled = true;
		
		int roll1 = (int) (Math.random() * 6 + 1);
		int roll2 = (int) (Math.random() * 6 + 1);
//		
//		int roll1 = 1;
//		int roll2 = 0;
		
		if (this.boardRank + roll1 + roll2 >= 40) {
			this.boardRank = (this.boardRank + roll1 + roll2) % 40;
			this.money = this.money + 200;
			System.out.println(this.gamePiece + " Collected go money");
		} else {
			this.boardRank = this.boardRank + roll1 + roll2;
		}
				
		return this.boardRank;
	}

	public void purchaseProperty(Property property) {
		if (property.getBoardRank() == this.getBoardRank() && property.getOwnedBy().getGamePiece() == "NO-ONE") {
			if (this.getMoney() >= property.getPurchaseCost() && this.isTurnRolled()) {
				this.money = this.money - property.getPurchaseCost();
				property.setOwnedBy(this);
				this.propertyList.put(property, 0);
				System.out.println("Bought: " + property.getName() + " " + property.getPurchaseCost() + " " + this.getGamePiece() 
				+ " " + this.getMoney());
			}
		}
	}

	public void purchaseHouse(Property property) {
		GameSpace[] spaces = Main.game.getSpaces();
		boolean colourMonopolyOwned = false;
		boolean houseBuyable = true;
		
		// Algorithm for determining if player is stacking houses, and if the player owns a coloured monopoly
		for (int j=0; j<spaces.length; j++) {
			if (spaces[j] instanceof Property && spaces[j].getColour().equals(property.getColour())) {
				if (((Property)spaces[j]).getOwnedBy().equals(Main.game.getCurrentPlayer())) {
					colourMonopolyOwned = true;
					if (this.propertyList.get(property) > this.propertyList.get(((Property)spaces[j]))) {
						houseBuyable = false;
						break;
					}
				} else {
					colourMonopolyOwned = false;
					break;
				}				
			}
		}
		
		if (property.getOwnedBy().equals(this) && property.getCostPerHouse() <= this.money) {
			if (colourMonopolyOwned && houseBuyable) {
				int currentHouseCount = this.propertyList.get(property);
				this.propertyList.put(property, currentHouseCount + 1);
				this.money = this.money - property.getCostPerHouse();
				if (currentHouseCount < 4) {
					property.getHouses()[currentHouseCount].setVisible(true);
				} else {
					property.getHouses()[0].setVisible(false);
					property.getHouses()[1].setVisible(false);
					property.getHouses()[2].setVisible(false);
					property.getHouses()[3].setVisible(false);
					property.getHouses()[4].setVisible(true);
				}
			}
		}
	}
	
	public void payRent(HashMap<Property, Integer> opponentProperties) {
		Property property = (Property) Main.game.getSpaces()[this.boardRank];
		int moneyOwed = property.getRentCosts()[opponentProperties.get(property)];
		
		this.money = this.money - moneyOwed;
		property.getOwnedBy().setMoney(property.getOwnedBy().getMoney() + moneyOwed);
	}
	
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getGamePiece() {
		return gamePiece;
	}

	public void setGamePiece(String gamePiece) {
		this.gamePiece = gamePiece;
	}

	public HashMap<Property, Integer> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(HashMap<Property, Integer> propertyList) {
		this.propertyList = propertyList;
	}

	public int getBoardRank() {
		return boardRank;
	}

	public void setBoardRank(int boardRank) {
		this.boardRank = boardRank;
	}

	public Circle getImage() {
		return image;
	}

	public void setImage(Circle image) {
		this.image = image;
	}

	public boolean isTurnRolled() {
		return turnRolled;
	}

	public void setTurnRolled(boolean turnRolled) {
		this.turnRolled = turnRolled;
	}

}
