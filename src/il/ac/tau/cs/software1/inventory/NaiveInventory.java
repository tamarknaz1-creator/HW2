package il.ac.tau.cs.software1.inventory;

/*
 * A simple array-based Inventory (Question 6).
 */
public class NaiveInventory implements Inventory {

	private static final int INITIAL_CAPACITY = 100;

	// TODO: add the fields:
	//       - an array of Collectibles (it may be larger than the number of items actually stored)
	//       - an int that counts how many items are actually stored
	private Collectible[] collectibles;
	private int currentCount;

	public NaiveInventory() {
		this.collectibles = new Collectible[INITIAL_CAPACITY];
		this.currentCount = 0;
	}

	@Override
	public void collect(Collectible collectible) {
		if (currentCount >= collectibles.length) {
			// Resize the array if needed
			Collectible[] newCollectibles = new Collectible[collectibles.length * 2];
			System.arraycopy(collectibles, 0, newCollectibles, 0, collectibles.length);
			collectibles = newCollectibles;
		}
		collectibles[currentCount] = collectible;
		currentCount++;
	}



	@Override
	public float getTotalWeight() {
		float totalWeight = 0.f;
		for (int i = 0; i < currentCount; i++) {
			totalWeight += collectibles[i].getWeight();
		}
		return totalWeight;
	}

	@Override
	public int getCurrentCount() {
		return currentCount;
	}

	// Returns the array of Collectibles that is stored in the field of this class
	public Collectible[] getCollArr() {
		return collectibles;
	}
}
