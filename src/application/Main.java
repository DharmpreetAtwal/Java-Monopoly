package application;
	
import java.util.ArrayList;

import gameSpaces.GameSpace;
import gameSpaces.Property;
import javafx.application.Application;
import javafx.stage.Stage;
import monopoly.Game;
import monopoly.Player;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;


public class Main extends Application {
	BorderPane root = new BorderPane();
	StackPane center = new StackPane();
	public static Game game;
	Button roll;
	Button endTurn;
	
	public void start(Stage primaryStage) {
		Scene scene = new Scene(root,550,550);
        scene.getStylesheets().add("back.css");
        game = new Game(1500, "Dharm", "Preet");
		center.setLayoutX(275);
		center.setLayoutY(275);
		root.getChildren().add(center);
	
		initBoardImages();
		initButtonAction();
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}	
	
	public void initBoardImages() {
		GameSpace[] space = game.getSpaces();
		Player[] players = game.getPlayers();
		
		for (int i=0; i<space.length; i++) {
			root.getChildren().add(space[i].getImage());
			center.getChildren().add(space[i].getTextArea());
			
			if (i>=0 && i<=10) {
				space[i].getImage().setLayoutX(50 * i);
				space[i].getImage().setLayoutY(0);
			} else if (i>=11 && i<=20) {
				space[i].getImage().setLayoutX(500);
				space[i].getImage().setLayoutY(50 * (i - 10));
			} else if (i>=21 && i<=30) {
				space[i].getImage().setLayoutX(550 + (-50 * (i - 19)));
				space[i].getImage().setLayoutY(500);
			} else if (i>=31 && i<=39) {
				space[i].getImage().setLayoutX(0);
				space[i].getImage().setLayoutY(550 + (-50 * (i - 29)));
			}
			
			if (space[i] instanceof Property) {
				Property property = ((Property) space[i]);
				center.getChildren().add(property.getBtnPurchase());
				center.getChildren().add(property.getBtnTrade());
				center.getChildren().add((property.getBtnMortgage()));
				center.getChildren().add(property.getBtnPurchaseHouse());
				
				for (int j=0; j<property.getHouses().length - 1  ; j++) {
					property.getHouses()[j].setLayoutX(2 + property.getImage().getLayoutX() + (12 * j));
					property.getHouses()[j].setLayoutY(property.getImage().getLayoutY() + 35);
					root.getChildren().add(property.getHouses()[j]);
				}
				property.getHouses()[4].setLayoutX(property.getImage().getLayoutX() + (20));
				property.getHouses()[4].setLayoutY(property.getImage().getLayoutY() + 35);
				root.getChildren().add(property.getHouses()[4]);
			}
		}
		
		for (int i=0; i<players.length; i++) {
			root.getChildren().add(players[i].getImage());
			reorientPlayerImage(players, players[i]);
		}
		
		roll = new Button("Roll");
		roll.setMinWidth(80);
		roll.setMinHeight(25);
		roll.setTranslateX(-85);
		roll.setTranslateY(175);
		
		endTurn = new Button("End Turn");
		endTurn.setMinWidth(80);
		endTurn.setMinHeight(25);
		endTurn.setTranslateX(85);
		endTurn.setTranslateY(175);

		center.getChildren().addAll(roll, endTurn);
	}
	
	public void reorientPlayerImage(Player[] players, Player player) {
		int index = 0;
		for (int i=0; i<players.length; i++) {
			if (players[i].equals(player)) {
				index = i;
			}
		}
				
		Circle image = player.getImage();
		if (index <= 2) {
			image.setLayoutX((15 * index) + 10 + image.getLayoutX());
			image.setLayoutY(15 + image.getLayoutY());
		} else {
			image.setLayoutX((15 * (index % 3)) + 10 + image.getLayoutX());
			image.setLayoutY(30 + image.getLayoutY());
		}
	}
	
	public void initButtonAction() {
		roll.setOnAction(e->{			
			Player currentPlayer = game.getCurrentPlayer();
			
			if (!currentPlayer.isTurnRolled()) {
				int currentPlayerBoardRank = currentPlayer.rollTurn();
				
				GameSpace space = game.getSpaces()[currentPlayerBoardRank];
				currentPlayer.getImage().setLayoutX(space.getImage().getLayoutX());
				currentPlayer.getImage().setLayoutY(space.getImage().getLayoutY());
				reorientPlayerImage(game.getPlayers(), currentPlayer);
				game.checkActionLanded(currentPlayer);
			}

		});
		
		endTurn.setOnAction(e->{
			game.getCurrentPlayer().setTurnRolled(false);
			game.nextTurn();
			System.out.println("It is now " + game.getCurrentPlayer().getGamePiece());
		});
		
		GameSpace[] spaces = game.getSpaces();
		for (int i=0; i<spaces.length; i++) {
			if (spaces[i] instanceof Property) {
				Property property = ((Property) spaces[i]);
				
				property.getBtnPurchase().setOnMouseClicked(e->{
					game.getCurrentPlayer().purchaseProperty(property);
					property.updateTextArea();
				});;
				
				property.getBtnPurchaseHouse().setOnMouseClicked(e->{					
					game.getCurrentPlayer().purchaseHouse(property);
					property.updateTextArea();
				});
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
