public class dangeonGeration extends Component {
  public class Cell {
    public boolean vision = false;
    public int[] status = new int[] {2, 2, 2, 2};
  }

  public Point2 size = new Point2();
  public int startPos = 0;
  public List<Cell> board;
  public ObjectFile room;
  public Point2 offset = new Point2();
  @Order(idx = 1)
  public VertexFile walls, doors;
  private dangenBer roomber;

  void start() {
    roomber = new dangenBer();
    armGerador();
  }

  public void gerationDange() {
    StringBuilder name = new StringBuilder(size.x + size.y);
    for (int x = 0; x < size.x; x++) {
      for (int z = 0; z < size.y; z++) {
        Cell cellTmp = board.get(x + z * size.x);

        if (cellTmp.vision) {
          name.setLength(0);
          SpatialObject newRoom = myObject.instantiate(room, new Vector3(x * offset.x, 0, -z * offset.y));
          name.append(x).append(" ").append(z);
          if (roomber != null) roomber.UpdateRoom(cellTmp.status, name.toString(), newRoom, walls, doors);
          newRoom.setName(name.toString());
        }
      }
    } 
  }

  public void armGerador() {
    board = new LinkedList<Cell>();
    for (int z = 0; z < size.y; z++) {
      for (int x = 0; x < size.x; x++) {
        board.add(new Cell());
      }
    }
    int currentCell = startPos;
    Deque<Integer> path = new ArrayDeque<Integer>();
    int k = 0;
    while (k < 1000) {
      k++;
      board.get(currentCell).vision = true;
      if (currentCell == board.size() - 1) break;
      List<Integer> neighbors = checkNeighbors(currentCell);
      if (neighbors.size() == 0) {
        if (path.size() == 0) break;
        else currentCell = path.pop();
      } else {
        path.push(currentCell);
        int newCell = neighbors.get(Random.range(0, neighbors.size() - 1));
        if (newCell > currentCell) {
          if (newCell - 1 == currentCell) {
            board.get(currentCell).status[3] = 1;
            currentCell = newCell;
            board.get(currentCell).status[1] = 1;
          } else {
            board.get(currentCell).status[2] = 1;
            currentCell = newCell;
            board.get(currentCell).status[0] = 1;
          }
        } else {
          if (newCell + 1 == currentCell) {
            board.get(currentCell).status[1] = 1;
            currentCell = newCell;
            board.get(currentCell).status[3] = 1;
          } else {
            board.get(currentCell).status[0] = 1;
            currentCell = newCell;
            board.get(currentCell).status[2] = 1;
          }
        }
      }
    }
    gerationDange();
  }

  public List<Integer> checkNeighbors(int cell) {
    List<Integer> neighbors = new LinkedList<Integer>();

    if ((cell - size.x) > 0 && !board.get(cell - size.x).vision) neighbors.add(cell - size.x);

    if ((cell + size.x) < board.size() && !board.get(cell + size.x).vision) neighbors.add(cell + size.x);

    if (((cell + 1) % size.x) != 0 && !board.get(cell + 1).vision) neighbors.add(cell + 1);

    if ((cell % size.x) != 0 && !board.get(cell - 1).vision) neighbors.add(cell - 1);
    return neighbors;
  }
}
