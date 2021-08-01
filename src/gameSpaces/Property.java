package gameSpaces;

import monopoly.Player;
import application.Main;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

public class Property extends GameSpace {
	private final String name;
	private final int purchaseCost;
	private final int costPerHouse;
	private final int[] rentCosts = new int[6];	
	private final Rectangle[] houses = new Rectangle[5];
	private final int mortgageValue;
	private Player ownedBy;
	private Button btnPurchase;
	private Button btnMortgage;
	private Button btnTrade;
	private Button btnPurchaseHouse;
	
//	SET IT UP SO THAT THE USER CAN HOVER OVER PROPERTY TO FIND INFO
	public Property(String colour, int purchaseCost, int costPerHouse, String name,
			int rent, int oneHouse, int twoHouse, int threeHouse, int fourHouse, int hotel, 
			int mortgageValue, int boardRank) {
		super(boardRank, colour);
		this.name = name;
		this.purchaseCost = purchaseCost;
		this.costPerHouse = costPerHouse;
		this.rentCosts[0] = rent;
		this.rentCosts[1] = oneHouse;
		this.rentCosts[2] = twoHouse;
		this.rentCosts[3] = threeHouse;
		this.rentCosts[4] = fourHouse;
		this.rentCosts[5] = hotel;
		
		for (int i=0; i<this.houses.length - 1; i++) {
			this.houses[i] = new Rectangle(8, 8);
			this.houses[i].setFill(javafx.scene.paint.Color.DARKGREEN);
			this.houses[i].setStroke(javafx.scene.paint.Color.valueOf("white"));
			this.houses[i].setStrokeWidth(1);
			this.houses[i].setVisible(false);
		}
		
		this.houses[4] = new Rectangle(15, 15);
		this.houses[4].setFill(javafx.scene.paint.Color.DARKRED);
		this.houses[4].setStroke(javafx.scene.paint.Color.valueOf("white"));
		this.houses[4].setStrokeWidth(1);
		this.houses[4].setVisible(false);
		
		this.mortgageValue = mortgageValue;
		this.ownedBy = new Player();
		
		this.btnPurchase = new Button("Purchase");
		this.btnPurchase.setTextFill(javafx.scene.paint.Color.valueOf("white"));
		this.btnPurchase.setStyle("-fx-background-color: "+ colour + ";");
		this.btnPurchase.setVisible(false);
		this.btnPurchase.setMinSize(80, 25);
		this.btnPurchase.setTranslateX(-85);
		this.btnPurchase.setTranslateY(140);
		
		this.btnTrade = new Button("Trade");
		this.btnTrade.setTextFill(javafx.scene.paint.Color.valueOf("white"));
		this.btnTrade.setStyle("-fx-background-color: "+ colour + ";");
		this.btnTrade.setVisible(false);
		this.btnTrade.setMinSize(80, 25);
		this.btnTrade.setTranslateY(140);

		this.btnMortgage = new Button("Mortgage");
		this.btnMortgage.setTextFill(javafx.scene.paint.Color.valueOf("white"));
		this.btnMortgage.setStyle("-fx-background-color: "+ colour + ";");
		this.btnMortgage.setVisible(false);
		this.btnMortgage.setMinSize(80, 25);
		this.btnMortgage.setTranslateX(85);
		this.btnMortgage.setTranslateY(140);	
		
		this.btnPurchaseHouse = new Button("Buy House");
		this.btnPurchaseHouse.setTextFill(javafx.scene.paint.Color.valueOf("white"));
		this.btnPurchaseHouse.setStyle("-fx-background-color: "+ colour + ";" 
										+ "-fx-text-alignment: center;");
		this.btnPurchaseHouse.setVisible(false);
		this.btnPurchaseHouse.setMinSize(80, 50);
		this.btnPurchaseHouse.setTranslateY(180);
		this.btnPurchaseHouse.setWrapText(true);
		
		this.getImage().setOnMouseClicked(e ->{
			updateTextArea();
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
			
			if (this.getTextArea().isVisible()) {
				this.getTextArea().setVisible(false);
				this.btnPurchase.setVisible(false);
				this.btnTrade.setVisible(false);
				this.btnMortgage.setVisible(false);
				this.btnPurchaseHouse.setVisible(false);
			} else {
				this.getTextArea().setVisible(true);
				this.btnPurchase.setVisible(true);
				this.btnTrade.setVisible(true);
				this.btnMortgage.setVisible(true);
				this.btnPurchaseHouse.setVisible(true);
			}
		});
	}

	public void updateTextArea() {
		this.setText(name + "\n" +
				"Owned by: " +  this.ownedBy.getGamePiece()+"\n" +
				"Purchase Cost: " +  this.purchaseCost + "\n" +
				"Rent: " + this.rentCosts[0] + "\n" +
				"1 House: " + this.rentCosts[1] + "\n" +
				"2 House: " + this.rentCosts[2] + "\n" +
				"3 House: " + this.rentCosts[3] + "\n" +
				"4 House: " + this.rentCosts[4] + "\n" +
				"Hotel: " + this.rentCosts[5] + "\n");	
	}
	
	@Override
	public void setActionLanded(Player player) {
		Player ownedBy = this.getOwnedBy();
		if (ownedBy.getGamePiece() != "NO-ONE" && !ownedBy.equals(player)) {
			int moneyOwed = this.getRentCosts()[ownedBy.getPropertyList().get(this)];
			player.setMoney(player.getMoney() - moneyOwed);
			ownedBy.setMoney(ownedBy.getMoney() + moneyOwed);
			System.out.println(player.getGamePiece() + " Payed " + ownedBy.getGamePiece() +  " " + moneyOwed);
		}
		
	}
	
	public String getName() {
		return name;
	}

	public int getCostPerHouse() {
		return costPerHouse;
	}

	public int[] getRentCosts() {
		return rentCosts;
	}

	public int getMortgageValue() {
		return mortgageValue;
	}

	public Player getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(Player ownedBy) {
		this.ownedBy = ownedBy;
	}

	public Button getBtnPurchase() {
		return btnPurchase;
	}

	public void setBtnPurchase(Button btnPurchase) {
		this.btnPurchase = btnPurchase;
	}

	public Button getBtnMortgage() {
		return btnMortgage;
	}

	public void setBtnMortgage(Button btnMortgage) {
		this.btnMortgage = btnMortgage;
	}

	public Button getBtnTrade() {
		return btnTrade;
	}

	public void setBtnTrade(Button btnTrade) {
		this.btnTrade = btnTrade;
	}

	public int getPurchaseCost() {
		return purchaseCost;
	}

	public Button getBtnPurchaseHouse() {
		return btnPurchaseHouse;
	}

	public void setBtnPurchaseHouse(Button btnPurchaseHouse) {
		this.btnPurchaseHouse = btnPurchaseHouse;
	}

	public Rectangle[] getHouses() {
		return houses;
	}

}


