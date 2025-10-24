public class dangenBer {
  private HashSet<Long> gerar = new HashSet<Long>();
  private Texture map;

  public void UpdateRoom(int[] status, String txt, SpatialObject objs, VertexFile walls, VertexFile doors, Color color) {
    String[] name = txt.split("\\s+");
    int cellx = Integer.parseInt(name[0]), cellz = Integer.parseInt(name[1]);
    int[][] dirs = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    for (int i = 0; i < status.length; i++) {
      if (status[i] == 0) continue;
      int wx = 2 * cellx + dirs[i][0], wz = 2 * cellz + dirs[i][1];
      long key = codKey(wx, wz);
      // Console.log(key);
      //if (gerar.contains(key)) continue;
      gerar.add(key);

      Vertex obj = Vertex.loadFile(status[i] == 1 ? doors : walls);
      float rot = (i == 0) ? -90 : (i == 1) ? 180 : (i == 2) ? 90 : 0;
      spawObj(rot, new Point2(0, 0), new Point3(1, 1, 1), color, new SpatialObject(i + txt + key, objs), obj);
    }
  }

  public SpatialObject spawObj(float rot, Point2 pos, Point3 scale, Color color, SpatialObject ob, Vertex obj) {
    ob.addComponent(new ModelRenderer(obj));
    ob.addComponent(new Collider(3));
    ModelRenderer model = ob.findComponent("ModelRenderer");
    model.setMaterial(new Material());
    model.material.setShader("PBR/Standard");
    model.material.setReceiveLight(false);
    model.material.setDiffuse(color);
    Collider col = ob.findComponent("Collider");
    col.setVertex(obj);
    Quaternion rots = new Quaternion();
    rots.setFromEuler(new Vector3(0, rot, 0));
    ob.setRotation(rots);
    ob.setPosition(pos.x, 0, pos.y);
    ob.setScale(scale.x, scale.y, scale.z);
    return ob;
  }

  public Texture miniMap(List<dangeonGeration.Cell> board, Point2 size, Color[] cor) {
    map.setMipmapEnabled(false);
    for (int x = 0; x < size.x; x++) {
      for (int z = 0; z < size.y; z++) {
        dangeonGeration.Cell cellTmp = board.get(x + z * size.x);
        int xs = 1 + (size.x - x - 1) * 2, zs = 1 + (size.y - z - 1) * 2;
        if (cellTmp.vision) map.set(xs, zs, (x == 0 && z == 0) ? cor[0] : (x == (size.x - 1) && z == (size.y - 1)) ? cor[2] : cor[3]);
        else map.set(xs, zs, new Color(0, 0, 0, 0));
      }
    }
    map.apply();
    return map;
  }

  private int oldpx = -1, oldpz = -1;

  public Texture playermove(List<dangeonGeration.Cell> board, Vector3 m, Point2 size, Point2 offset, Color[] cor) {
    int px = (int) Math.round(m.x / offset.x), pz = (int) Math.round(m.z / offset.y);
    if (oldpx != -1 && oldpz != -1) {
      dangeonGeration.Cell cellTmp = board.get(oldpx + oldpz * size.x);
      Color cors = oldpx == 0 && oldpz == 0 ? cor[0] : (px == (size.x) && pz == (size.y)) ? cor[2] : (cellTmp.vision ? cor[3] : Color.WHITE());
      map.set(1 + (size.x - oldpx - 1) * 2, 1 + (size.y - oldpz - 1) * 2, cors);
    } 

    map.set(1 + (size.x - px - 1) * 2, 1 + (size.y - pz - 1) * 2, Color.GREEN());
    map.apply();
    oldpx = px;
    oldpz = pz;
    return map;
  }

  public void setTexture(int x, int z) {
    map = new Texture(x, z, true);
  }

  private long codKey(int x, int z) {
    return (((long) x) << 32) | (z & 0xFFFFFFFFL);
  }
}
