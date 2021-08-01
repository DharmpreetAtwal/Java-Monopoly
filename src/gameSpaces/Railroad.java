package gameSpaces;

import java.util.HashMap;
import java.util.Iterator;

import monopoly.Player;

public class Railroad extends Property {

	public Railroad(String colour, String name, int boardRank) {
		super(colour, 200, 0, name, 25, 50, 100, 200, 0, 0, 100, boardRank);
		

	}
	
	public void updateTextArea() {
		this.getTextArea().setText(this.getName() + "\n" +
				"Owned by: " +  this.getOwnedBy().getGamePiece()+"\n" +
				"Purchase Cost: " +  this.getPurchaseCost() + "\n" +
				"1 Railroad Owned: " + this.getRentCosts()[0] + "\n" +
				"2 Railroads Owned: " + this.getRentCosts()[1] + "\n" +
				"3 Railroads Owned: " + this.getRentCosts()[2] + "\n" +
				"4 Railroads Owned: " + this.getRentCosts()[3]);	
	}
	
	@Override
	public void setActionLanded(Player player) {
		Player ownedBy = this.getOwnedBy();
		if (ownedBy.getGamePiece() != "NO-ONE" && !ownedBy.equals(player)) {
			HashMap<Property, Integer> properties = ownedBy.getPropertyList();
			Iterator<Property> iterator = properties.keySet().iterator();
			int rlrdCount = -1;
			while (iterator.hasNext()) {
				Property proper = iterator.next();
				if (proper instanceof Railroad) {
					rlrdCount++;
				}
			}
		
			int moneyOwed = this.getRentCosts()[rlrdCount];
			player.setMoney(player.getMoney() - moneyOwed);
			ownedBy.setMoney(ownedBy.getMoney() + moneyOwed);
			System.out.println(player.getGamePiece() + " Payed " + ownedBy.getGamePiece() +  " " + moneyOwed);
	
		}
	}
	
}
