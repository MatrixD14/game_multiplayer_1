public class dangeonGeration extends Component {
  public class Cell {
    public boolean vision = false;
    public int[] status = new int[] {2, 2, 2, 2};
  }

  public class Rule extends Component {
    public SpatialObject room;
    public Point2 minPos, maxPos;
    public boolean obrig;

    public int probrabilidade(int x, int z) {
      if (x >= minPos.x && x <= maxPos.x && z >= minPos.y && z <= maxPos.y) {
        return obrig ? 2 : 1;
      }
      return 0;
    }
  }

  @Order(idx = -1)
  public Point2 size = new Point2(), offset = new Point2();
  private int startPos = 0;
  public List<Cell> board;
  public ObjectFile room;
  @Order(idx = 1)
  public VertexFile walls, doors, chao;
  private dangenBer roomber;
  private PerlinNoise noise;
  private int seed;
  private Rule[] rooms = new Rule[2];
  private Color[] cor;
  private UIRotateImage img;
  private Texture map;
  private SpatialObject myplayer;

  void start() {
    map = new Texture(size.x * 2, size.y * 2, true);
    img = WorldController.findObject("minimap").findComponent("RotateImage");
    myplayer = WorldController.findObject("player2d");
    cor = new Color[] {new Color(0, 0, 125), new Color(0, 255, 0), new Color(125, 0, 0), new Color(50, 50, 50)};
    seed = 20;
    roomber = new dangenBer();
    noise = new PerlinNoise(10);
    armGerador();
    img.setTexture(miniMap());
  }

  void repeat() {
    img.setTexture(playermove(myplayer.position));
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

  private Texture miniMap() {
    map.setMipmapEnabled(false);
    for (int x = 0; x < size.x; x++) {
      for (int z = 0; z < size.y; z++) {
        Cell cellTmp = board.get(x + z * size.x);
        int xs = x * 2, zs = (size.y - z - 1) * 2;
        if (cellTmp.vision) map.set(xs, zs, (x == 0 && z == 0) ? cor[0] : (x == (size.x - 1) && z == (size.y - 1)) ? cor[2] : cor[3]);
        else map.set(xs, zs, Color.WHITE());
      }
    }
    map.apply();
    return map;
  }

  public Texture playermove(Vector3 m) {
    int px = (int) (m.x / (offset.x/1)), pz = (int) (m.z / (offset.y/1));
    map.set(px * 2, (size.y - pz - 1) * 2, Color.YELLOW());
    map.apply();
    return map;
  } 

  public void setSeed(int seed) {
    this.seed = seed;
  }

  public int getSeed() {
    return seed;
  }
}
