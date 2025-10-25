public class dangeonGeration extends Component {
  public class Cell {
    public boolean vision = false;
    public int[] status = new int[] {2, 2, 2, 2};
  }

  public class Rule extends Component {
    public ObjectFile room;
    public Point2 minPos, maxPos;
    public boolean obrig = false;

    public Rule(ObjectFile room, Point2 minPos, Point2 maxPos, boolean obrig) {
      this.obrig = obrig;
      this.minPos = minPos;
      this.maxPos = maxPos;
      this.room = room;
    }

    public int probrabilidade(int x, int z) {
      if (x >= minPos.x && x <= maxPos.x && z >= minPos.y && z <= maxPos.y) {
        return obrig ? 2 : 1;
      }
      return 0;
    }
  }

  @Order(idx = -1)
  public Point2 size = new Point2(5, 5), offset = new Point2(65, 65);
  private int startPos = 0;
  public List<Cell> board;
  public ObjectFile room;
  public ObjectFile[] roomRand = new ObjectFile[2];
  @Order(idx = 1)
  public VertexFile walls, doors, chao;
  private dangenBer roomber;
  private PerlinNoise noise;
  private int seed;
  private Rule[] rooms;
  private Color[] cor;
  private UIRotateImage img;
  private SpatialObject myplayer;

  void start() {
    img = WorldController.findObject("minimap").findComponent("RotateImage");
    myplayer = WorldController.findObject("player2d");
    cor = new Color[] {new Color(0, 0, 125), new Color(0, 255, 0), new Color(125, 0, 0), new Color(50, 50, 50)};
    roomber = new dangenBer();
    noise = new PerlinNoise(10);
    roomber.setTexture(1 + size.x * 2, 1 + size.y * 2);
    armGerador();
    img.setTexture(roomber.miniMap(board, size, cor));
  }

  void repeat() {
    if (img != null) img.setTexture(roomber.playermove(board, myplayer.position, size, offset, cor));
  }

  public void gerationDange() {
    rooms();
    StringBuilder name = new StringBuilder(size.x + size.y);
    for (int x = 0; x < size.x; x++) {
      for (int z = 0; z < size.y; z++) {
        Cell cellTmp = board.get(x + z * size.x);

        if (cellTmp.vision) {
          name.setLength(0);
          /*int randomRoom = -1;
          List<Integer> avalieRooms = new LinkedList<Integer>();
          for (int i = 0; i < rooms.length; i++) {
            int p = rooms[i].probrabilidade(x, z);
            if (p == 2) {
              randomRoom = i;
              break;
            } else if (p == 1) avalieRooms.add(i);
          }
          if (randomRoom == -1) {
            if (avalieRooms.size() > 0) randomRoom = avalieRooms.get(Random.range(0, avalieRooms.size()));
            else randomRoom = 0;
          }*/
          SpatialObject newRoom = myObject.instantiate(room, new Vector3(x * offset.x, 0, z * offset.y));
          // spawObj.spawObj(0, new Point2(), new Point3(1, 1, 1), cor[randomRoom], SpatialObject.loadFile(roomRand[randomRoom]), Vertex.loadPrimitive(0));
          name.append(x).append(" ").append(z);
          if (roomber != null) roomber.UpdateRoom(cellTmp.status, name.toString(), newRoom, walls, doors, (x == 0 && z == 0) ? cor[0] : (x == (size.x - 1) && z == (size.y - 1)) ? cor[2] : cor[3]);
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

      if (neighbors.size() > 0) {
        float noises = noise.noise(currentCell + seed + k, 0);
        int idx = (int) (((noises + 1) * .5f) * neighbors.size());
        if (idx >= neighbors.size()) idx = neighbors.size() - 1;
        if (idx < 0) idx = 0;
        if (neighbors.size() == 0) {
          if (path.size() == 0) break;
          else currentCell = path.pop();
        } else {
          path.push(currentCell);
          int newCell = neighbors.get(idx);
          if (newCell > currentCell) {
            if (newCell - 1 == currentCell) {
              board.get(currentCell).status[3] = 1;
              currentCell = newCell;
              board.get(currentCell).status[1] = 1;
            } else {
              board.get(currentCell).status[0] = 1;
              currentCell = newCell;
              board.get(currentCell).status[2] = 1;
            }
          } else {
            if (newCell + 1 == currentCell) {
              board.get(currentCell).status[1] = 1;
              currentCell = newCell;
              board.get(currentCell).status[3] = 1;
            } else {
              board.get(currentCell).status[2] = 1;
              currentCell = newCell;
              board.get(currentCell).status[0] = 1;
            }
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

  private void rooms() {
    rooms = new Rule[] {new Rule(roomRand[0], new Point2(), new Point2(5, 5), false), new Rule(roomRand[1], new Point2(), new Point2(3, 3), false)};
  }

  public void setSeed(int seed) {
    this.seed = seed;
  }

  public int getSeed() {
    return seed;
  }
}
