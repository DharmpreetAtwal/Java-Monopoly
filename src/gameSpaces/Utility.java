package gameSpaces;

import java.util.HashMap;
import java.util.Iterator;

import monopoly.Player;

public class Utility extends Property {

	public Utility(String colour, String name, int boardRank) {
		super(colour, 150, 0, name, 25, 50, 100, 200, 0, 0, 100, boardRank);
	}
	
	@Override
	public void setActionLanded(Player player) {
		Player ownedBy = this.getOwnedBy();
		
		if (ownedBy.getGamePiece() != "NO-ONE" && !ownedBy.equals(player)) {
			HashMap<Property, Integer> properties = ownedBy.getPropertyList();
			Iterator<Property> iterator = properties.keySet().iterator();
			int utilCount = 0;
			while (iterator.hasNext()) {
				Property proper = iterator.next();
				if (proper instanceof Utility) {
					utilCount++;
				}
			}
			
			int roll1 = (int) (Math.random() * 6 + 1);
			int roll2 = (int) (Math.random() * 6 + 1);
			int moneyOwed = 0;

			if (utilCount == 1) {
				moneyOwed = (roll1 + roll2) * 4;
			} else if(utilCount == 2) {
				moneyOwed = (roll1 + roll2) * 10;
			}
			
			player.setMoney(player.getMoney() - moneyOwed);
			ownedBy.setMoney(ownedBy.getMoney() + moneyOwed);
			System.out.println(player.getGamePiece() + " Payed " + ownedBy.getGamePiece() +  " " + moneyOwed + " " + (roll1 + roll2));
		}
	}
}
