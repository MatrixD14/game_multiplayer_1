public class dangenBer {
  private HashSet<Long> gerar = new HashSet<Long>();

  public void UpdateRoom(int[] status, String txt, SpatialObject objs, VertexFile walls, VertexFile doors) {
    String[] name = txt.split("\\s+");
    int cellx = Integer.parseInt(name[0]), cellz = Integer.parseInt(name[1]);
    int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0} };
    for (int i = 0; i < status.length; i++) {
      if (status[i] == 0) continue;
      int wx = 2 * cellx + dirs[i][0], wz = 2 * cellz + dirs[i][1];
      long key = codKey(wx, wz);
      Console.log(key);
      if (gerar.contains(key)) continue;
      gerar.add(key);

      Vertex obj = Vertex.loadFile(status[i] == 1 ? doors : walls);
      float rot = (i == 0) ? -90 : (i == 1) ? 180 : (i == 2) ? 90 : 0;
      SpatialObject ob = new SpatialObject(txt + key, objs);
      ob.addComponent(new ModelRenderer(obj));
      ob.addComponent(new Collider(3));
      ModelRenderer model = ob.findComponent("ModelRenderer");
      model.setMaterial(new Material());
      model.material.setShader("PBR/Standard");
      model.material.setReceiveLight(false);
      Collider col = ob.findComponent("Collider");
      col.setVertex(obj);
      Quaternion rots = new Quaternion();
      rots.setFromEuler(new Vector3(0, rot, 0));
      ob.setRotation(rots);
    }
  }

  private long codKey(int x, int z) {
    return (((long) x) << 32) | (z & 0xFFFFFFFFL);
  }
}
