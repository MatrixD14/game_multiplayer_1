public class dangenBer {

  public void UpdateRoom(int[] status, String txt, SpatialObject objs, VertexFile walls, VertexFile doors) {
    StringBuilder name = new StringBuilder();
    for (int i = 0; i < status.length; i++) {
      name.setLength(0);
      Vertex obj;
      try {
        obj = Vertex.loadFile(status[i] == 1 ? doors : status[i] == 2 ? walls : null);
      } catch (Exception e) {
        continue;
      } 

      float rot = (i == 0) ? -90 : (i == 1) ? 180 : (i == 2) ? 90 : 0;
      name.append(txt).append(" / ").append(rot);
      SpatialObject ob = new SpatialObject(name.toString(), objs);
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
}
