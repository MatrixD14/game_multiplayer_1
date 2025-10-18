public class dangenBer {
  public ObjectFile walls, doors;

  public void UpdateRoom(boolean[] status, String txt, SpatialObject objs, VertexFile walls, VertexFile doors) {
    for (int i = 0; i < status.length; i++) {
      VertexFile obj = status[i] ? doors : walls;
      float rot = (i == 0) ? -90 : (i == 1) ? 180 : (i == 2) ? 90 : 0;
      SpatialObject ob = new SpatialObject();
      ob.addComponent(new ModelRenderer());
      ob.addComponent(new Collider(3));
      ModelRenderer model = ob.findComponent("ModelRenderer");
      model.setMaterial(new Material());
      model.material.setShader("PBR/Standard");
      model.setModelFile(obj);
      model.material.setReceiveLight(false);
      Collider col = ob.findComponent("Collider");
      col.setVertexFile(obj);
      ob.setParent(objs);
      Quaternion rots = new Quaternion();
      rots.setFromEuler(new Vector3(0, rot, 0));
      ob.setRotation(rots);
    } 
  }
}
