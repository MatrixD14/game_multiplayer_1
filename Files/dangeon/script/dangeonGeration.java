public class dangeonGeration extends Component {
  public class Cell {
    public boolean vision = false;
    public boolean[] status = new boolean[4];
    public Point2 position;

    public Cell(int x, int z) {
      position = new Point2(x, z);
    }
  }

  public Point2 size = new Point2();
  public int startPos = 0;
  public List<Cell> board;
  public ObjectFile room;
  public Point2 offset = new Point2();

  void start() {
    armGerador();
  }

  public void gerationDange() {
    for (int x = 0; x < size.x; x++) {
      for (int z = 0; z < size.y; z++) {
        int index = x + z * size.x;
        Cell cellTmp = board.get(index);
        if (cellTmp.vision) {
          SpatialObject newRoom = myObject.instantiate(room, new Vector3(x * offset.x, 0, -z * offset.y));
          // if (newRoom.findComponent("dangenBer")==null) newRoom.addComponent(new dangenBer());
          dangenBer roomber = newRoom.findComponent("dangenBer");
          if (roomber != null) roomber.UpdateRoom(cellTmp.status);
        }
      }
    }
  }

  public void armGerador() {
    board = new LinkedList<Cell>();
    for (int z = 0; z < size.y; z++) {
      for (int x = 0; x < size.x; x++) {
        board.add(new Cell(x, z));
      }
    }
    int currentCell = startPos;
    Deque<Integer> path = new ArrayDeque<Integer>();
    int k = 0;
    while (k < 1000) {
      k++;
      board.get(currentCell).vision = true;
      List<Integer> neighbors = checkNeighbors(currentCell);
      if (neighbors.isEmpty()) {
        if (path.isEmpty()) break;
        currentCell = path.pop();
        continue;
      }
      path.push(currentCell);
      int newCell = neighbors.get(Random.range(0, neighbors.size()-1));
      if (newCell > currentCell) {
        if (newCell - 1 == currentCell) {
          board.get(currentCell).status[1] = true;
          currentCell = newCell;
          board.get(currentCell).status[3] = true;
        } else {
          board.get(currentCell).status[2] = true;
          currentCell = newCell;
          board.get(currentCell).status[0] = true;
        }
      } else {
        if (newCell + 1 == currentCell) {
          board.get(currentCell).status[3] = true;
          currentCell = newCell;
          board.get(currentCell).status[1] = true;
        } else {
          board.get(currentCell).status[0] = true;
          currentCell = newCell;
          board.get(currentCell).status[2] = true;
        }
      }
    }
    gerationDange();
  }

  public List<Integer> checkNeighbors(int cell) {
    List<Integer> neighbors = new LinkedList<Integer>();
    Point2 pos = board.get(cell).position;

    if (pos.y > 0 && !board.get(cell - size.x).vision) neighbors.add(cell - size.x);

    if (pos.y < size.y - 1 && !board.get(cell + size.x).vision) neighbors.add(cell + size.x);

    if (pos.x < size.x - 1 && !board.get(cell + 1).vision) neighbors.add(cell + 1);

    if (pos.x > 0 && !board.get(cell - 1).vision) neighbors.add(cell - 1);

    return neighbors;
  } 
}
