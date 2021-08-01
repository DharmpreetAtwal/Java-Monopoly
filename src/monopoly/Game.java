package monopoly;

import java.util.HashMap;
import java.util.Iterator;

import gameSpaces.Chance;
import gameSpaces.Extra;
import gameSpaces.GameSpace;
import gameSpaces.Go;
import gameSpaces.Jail;
import gameSpaces.Property;
import gameSpaces.Railroad;
import gameSpaces.Tax;
import gameSpaces.Treasure;
import gameSpaces.Utility;

public class Game {
	private GameSpace[] spaces = new GameSpace[40];
	private Player[] players;
	private Player currentPlayer;
	
	public Game(int startMoney, String...playerNames) {
		players = new Player[playerNames.length];
		for (int i=0; i<playerNames.length; i++) {
			Player player = new Player(startMoney, playerNames[i]);
			players[i] = player;
		}
		
		this.currentPlayer = players[0];
		
		Go go = new Go(0, "BEIGE");
		Property brwn1 = new Property("BROWN", 60, 50, "Mediterranean Avenue", 2, 10, 30, 90, 160, 250, 30, 1);
		Treasure treasure1 = new Treasure(2, "AQUA");
		Property brwn2 = new Property("BROWN",60, 50, "Baltic Avenue", 4, 20, 60, 180, 320, 450, 30, 3);
		Tax tx1 = new Tax(4, "GOLDENROD");
		Railroad rlrd1 = new Railroad("BLACK", "Reading railroad", 5);
		Property lghtblu1 = new Property("LIGHTBLUE", 100, 50, "Oriental Avenue", 6, 20, 90, 270, 400, 550, 50, 6);
		Chance chance1 = new Chance(7, "CORAL");
		Property lghtblu2 = new Property("LIGHTBLUE",100, 50, "Vermont Avenue", 6, 20, 90, 270, 400, 550, 50, 8);
		Property lghtblu3 = new Property("LIGHTBLUE",120, 50, "Connecticut Avenue", 8, 40, 100, 300, 450, 600, 60, 9); 
		Jail jail1 = new Jail(10, "BLUE");
		
		Property pink1 = new Property("PINK",140, 100, "St. Charles Place", 10, 50, 150, 450, 625, 750, 70, 11);
		Utility elec = new Utility("GREY", "Electric Company", 12);
		Property pink2 = new Property("PINK",140, 100, "States Avenue", 10, 50, 150, 450, 625, 750, 70, 13);
		Property pink3 = new Property("PINK",160, 100, "Virginia Avenue", 12, 60, 180, 500, 700, 900, 80, 14);
		Railroad rlrd2 = new Railroad("BLACK", "Pennsylvania railroad", 15);
		Property orange1 = new Property("ORANGE",180, 100, "St. James Place", 14, 70, 200, 550, 750, 950, 90, 16);
		Treasure treasure2 = new Treasure(17, "AQUA");
		Property orange2 = new Property("ORANGE",180, 100, "Tennessee Avenue", 14, 70, 200, 550, 750, 950, 90, 18);
		Property orange3 = new Property("ORANGE",200, 100, "New York Avenue", 16, 80, 220, 600, 800, 1000, 100, 19);
		Extra extra = new Extra(20, "PURPLE");

		Property red1 = new Property("RED", 220, 150, "Kentucky Avenue", 18, 90, 250, 700, 875, 1050, 110, 21);
		Chance chance2 = new Chance(22, "CORAL");
		Property red2 = new Property("RED", 220, 150, "Indiana Avenue", 18, 90, 250, 700, 875, 1050, 110, 23);
		Property red3 = new Property("RED", 240, 150, "Illinois Avenue", 20, 100, 300, 750, 925, 1100, 120, 24);
		Railroad rlrd3 = new Railroad("BLACK", "B&O railroad", 25);
		Property yellow1 = new Property("GOLD", 260, 150, "Atlantic Avenue", 22, 100, 330, 800, 975, 1150, 130, 26);
		Property yellow2 = new Property("GOLD", 260, 150, "Ventnor Avenue", 22, 100, 330, 800, 975, 1150, 130, 27);
		Utility water = new Utility("GREY", "Water Works", 28);
		Property yellow3 = new Property("GOLD", 280, 150, "Marvin Gardens", 24, 120, 360, 850, 1025, 1200, 140, 29);
		Jail jail2 = new Jail(30, "BLUE");
		
		
		Property green1 = new Property("GREEN", 300, 200, "Pacific Avenue", 26, 130, 390, 900, 1100, 1275, 150, 31);
		Property green2 = new Property("GREEN", 300, 200, "North Carolina Avenue", 26, 130, 390, 900, 1100, 1275, 150, 32);
		Treasure treasure3 = new Treasure(33, "AQUA");
		Property green3 = new Property("GREEN", 320, 200, "Pennsylvania Avenue", 28, 150, 450, 1000, 1200, 1400, 200, 34);
		Railroad rlrd4 = new Railroad("BLACK", "Short Line", 35);
		Chance chance3 = new Chance(36, "CORAL");
		Property blu1 = new Property("DARKBLUE", 350, 200, "Park Place", 35, 175, 500, 1100, 1300, 1500, 175, 37);
		Tax tx2 = new Tax(38, "GOLDENROD");
		Property blu2 = new Property("DARKBLUE", 400, 200, "Boardwalk", 50, 200, 600, 1400, 1700, 2000, 200, 39);

		spaces[0] = go;
		spaces[1] = brwn1;
		spaces[2] = treasure1;
		spaces[3] = brwn2;
		spaces[4] = tx1;
		spaces[5] = rlrd1;
		spaces[6] = lghtblu1;
		spaces[7] = chance1;
		spaces[8] = lghtblu2;
		spaces[9] = lghtblu3;
		spaces[10] = jail1;
		spaces[11] = pink1;
		spaces[12] = elec;
		spaces[13] = pink2;
		spaces[14] = pink3;
		spaces[15] = rlrd2;
		spaces[16] = orange1;
		spaces[17] = treasure2;
		spaces[18] = orange2;
		spaces[19] = orange3;
		spaces[20] = extra;
		spaces[21] = red1;
		spaces[22] = chance2;
		spaces[23] = red2;
		spaces[24] = red3;
		spaces[25]= rlrd3;
		spaces[26] = yellow1;
		spaces[27] = yellow2;
		spaces[28] = water;
		spaces[29] = yellow3;
		spaces[30] = jail2;
		spaces[31] = green1;
		spaces[32] = green2;
		spaces[33] = treasure3;
		spaces[34] = green3;
		spaces[35] = rlrd4;
		spaces[36] = chance3;
		spaces[37] = blu1;
		spaces[38] = tx2;
		spaces[39] = blu2;
	}

	public void nextTurn() {
		int index = 0;
		for (int i =0;i<this.players.length; i++) {
			if (players[i].equals(this.currentPlayer)) {
				index = i;
			}
		}
				
		if (index == this.players.length - 1) {
			index = 0;
		} else {
			index = index + 1;
		}

		this.currentPlayer = players[index];
	}

	public void checkActionLanded(Player player) {
		GameSpace space = spaces[player.getBoardRank()];
		if (space instanceof Property) {
			Property property = (Property)space;
			property.setActionLanded(player);
		}
	}
	
	public GameSpace getGameSpace(int index) {
		return spaces[index];
	}
	
	public GameSpace[] getSpaces() {
		return spaces;
	}

	public void setSpaces(GameSpace[] spaces) {
		this.spaces = spaces;
	}

	public Player[] getPlayers() {
		return this.players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
}
