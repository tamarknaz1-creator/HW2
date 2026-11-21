package il.ac.tau.cs.software1.pacman;

import il.ac.tau.cs.software1.core.*;
import il.ac.tau.cs.software1.inventory.Collectible;
import il.ac.tau.cs.software1.inventory.InventoryComponent;
import il.ac.tau.cs.software1.components.ImageComponent;
import il.ac.tau.cs.software1.math.Vector2;

public class PacmanPlayer extends PacmanGameObject {
		
	protected int score = 0;
	
	// TODO (Question 9): add a private field of type InventoryComponent, and initialize it
	private InventoryComponent inventoryComponent = new InventoryComponent();


	public PacmanPlayer(PacmanGridComponent initialPosition) {
		super(initialPosition);
		imageComponent = new ImageComponent("player");
//		transformComponent.scale.multiply(2.f);
	}

	@Override
	public void update() {		
		gridComponent.x += (int)velocityComponent.velocity.x;
		gridComponent.y += (int)velocityComponent.velocity.y;
		if (gridComponent.x < 0) gridComponent.x = PacmanConstants.gridWidth - 1;
		if (gridComponent.x >= PacmanConstants.gridWidth) gridComponent.x = 0;
		if (gridComponent.y < 0) gridComponent.y = PacmanConstants.gridHeight - 1;
		if (gridComponent.y >= PacmanConstants.gridHeight) gridComponent.y = 0;
		this.snapTransformToGrid();
	}
	
	@Override
	public void onHit(GameObject other) {
		// No matter what, stop moving in a penetrating direction
		// After making a step and entering into collision, return one step back - and stop moving		
		if (other instanceof PacmanObstacle) {
			velocityComponent.velocity.x *= -1.f;
			velocityComponent.velocity.y *= -1.f;
			this.update();
			velocityComponent.velocity = new Vector2();
		}
		
		else if (other instanceof PacmanBonus) {
			PacmanBonus bonus = (PacmanBonus) other;
			
			// TODO (Question 9): replace the two lines below.
			// Instead of eating (destroying) the bonus, the player should stash it in the inventory,
			// but only if it is not too heavy to carry. See the assignment for the exact rules.
			
			if (inventoryComponent.getInventory().getTotalWeight() + bonus.getWeight() <= inventoryComponent.getMaxWeight()) {
				inventoryComponent.getInventory().collect(bonus);
				addScore(bonus.bonusScore);
				bonus.getTransformComponent().position = inventoryComponent.getInventoryPosition().copy();

			}
			
		}
	}
	
	public InventoryComponent getInventoryComponent() {
		return inventoryComponent;
	}
	
	public void addScore(int bonus) {
		score += bonus;
		System.out.println("Score: " + String.valueOf(score));
	}
	
	public int getScore() {
		return score;
	}
}
