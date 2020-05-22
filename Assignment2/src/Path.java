
/**
 * This class will try to find a path from the starting cell to the destination
 * cell.
 * 
 * @author Nicole Ni
 * @param cityMap references the object representing the city map
 */
public class Path {
	Map cityMap;

	/**
	 * constructor for the class
	 */
	public Path(Map theMap) {
		cityMap = theMap;
	}

	/**
	 * this method will look for a path from the staring cell the the destination
	 * cell
	 */
	public void findPath() {
		int pathNum = 0;
		ArrayStack<MapCell> pathStack = new ArrayStack(100, 20, 20); // new stack to store objects from the class
																		// MapCell
		MapCell lastCell = cityMap.getStart(); // get the starting cell
		pathStack.push(lastCell);
		lastCell.markInStack();
		do {
			lastCell = nextCell(lastCell);
			if (lastCell != null) {
				pathStack.push(lastCell);
				lastCell.markInStack();
			}
			// When there are no unmarked cell neighbours
			while (lastCell == null) {
				try {
					lastCell = pathStack.peek();
					lastCell.markOutStack();
					pathStack.pop();
					lastCell = pathStack.peek();
				} catch (EmptyStackException e) {
					System.out.println("There is no path from the starting point to the destination.");
					return;
				}
			}
		} while (!lastCell.isDestination());
		pathNum = pathStack.size();
		System.out.println("It took " + pathNum + " cells from the starting point to the destionation.");
	}

	/**
	 * this method returns the best map cell to continue the path from the current
	 * one
	 */
	private MapCell nextCell(MapCell cell) {
		MapCell currentCell = cell;
		MapCell theNeighbour;
		for (int i = 0; i <= 3; i++) {
			theNeighbour = currentCell.getNeighbour(i);
			if (theNeighbour == null) {
				continue;
			}
			if (!theNeighbour.isMarked() && !theNeighbour.equals("null")) {
				// check if the neighbour cell is a destination
				if (theNeighbour.isDestination() && currentCell.isStart()
						|| theNeighbour.isDestination() && currentCell.isIntersection()
						|| theNeighbour.isDestination() && currentCell.isNorthRoad() && i == 0
						|| theNeighbour.isDestination() && currentCell.isEastRoad() && i == 1
						|| theNeighbour.isDestination() && currentCell.isSouthRoad() && i == 2
						|| theNeighbour.isDestination() && currentCell.isWestRoad() && i == 3) {

					return theNeighbour;

					// check if the neighbour cell is a intersection
				} else if (theNeighbour.isIntersection() && currentCell.isIntersection()
						|| theNeighbour.isIntersection() && currentCell.isStart()
						|| theNeighbour.isIntersection() && currentCell.isSouthRoad() && i == 2
						|| theNeighbour.isIntersection() && currentCell.isWestRoad() && i == 3
						|| theNeighbour.isIntersection() && currentCell.isNorthRoad() && i == 0
						|| theNeighbour.isIntersection() && currentCell.isEastRoad() && i == 1) {

					return theNeighbour;

					// check if the neighbour cell is a North road
				} else if (theNeighbour.isNorthRoad() && i == 0 && currentCell.isIntersection()
						|| theNeighbour.isNorthRoad() && i == 0 && currentCell.isNorthRoad()) {

					return theNeighbour;

					// check if the neighbour cell is a East road
				} else if (theNeighbour.isEastRoad() && i == 1 && currentCell.isEastRoad()
						|| theNeighbour.isEastRoad() && i == 1 && currentCell.isIntersection()) {

					return theNeighbour;

					// check if the neighbour cell is a South road
				} else if (theNeighbour.isSouthRoad() && i == 2 && currentCell.isSouthRoad()
						|| theNeighbour.isSouthRoad() && i == 2 && currentCell.isIntersection()) {

					return theNeighbour;

					// check if the neighbour cell is a West road
				} else if (theNeighbour.isWestRoad() && i == 3 && currentCell.isWestRoad()
						|| theNeighbour.isWestRoad() && i == 3 && currentCell.isIntersection()) {

					return theNeighbour;
				}
			}
		}
		return null;
	}
}
