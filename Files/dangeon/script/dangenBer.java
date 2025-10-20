public class dangenBer {
  public ObjectFile walls, doors;
  private Staticbody st;
  public void UpdateRoom(boolean[] status, String txt, SpatialObject objs, VertexFile walls, VertexFile doors) {
    StringBuilder name = new StringBuilder();
    for (int i = 0; i < status.length; i++) {
      name.setLength(0);
      VertexFile obj = status[i] ? doors : walls;
      float rot = (i == 0) ? -90 : (i == 1) ? 180 : (i == 2) ? 90 : 0;
      name.append(txt).append(" / ").append(rot);
      SpatialObject ob = new SpatialObject(name.toString());
      ob.addComponent(new ModelRenderer());
      ob.addComponent(new Collider(3));
      ob.getPhysics().setPhysicsEntity(new Staticbody());
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
      st = ob.getPhysics().getPhysicsEntity();
      PhysicsLayer phy =null;
      if (i == 3 || i == 2) {
        phy = new PhysicsLayer("wall");
        phy.addIgnoreLayer(phy);
        st.setPhysicsLayer(phy);
      }  else st.setPhysicsLayer(new PhysicsLayer("repeat"));

      if (i == 3) ob.addComponent(new checkPhysics(3));
      if (i == 2) ob.addComponent(new checkPhysics(2));
    }
  }

  public class checkPhysics extends Component {
    private Vector3 diretion, trag;
    private Vector3 my, mys;
    private int n;
    private float t = 0;
    private Laser l;
    PhysicsLayer phys;

    public checkPhysics(int n) {
      this.n = n;
    }

    void start() {
      l = new Laser();
      my = myObject.globalPosition;
      mys = myObject.position;
      phys = new PhysicsLayer("wall");
    }

    void repeat() {
      if ((t += Time.deltatime()) < 2f) return;
      if (my == null || mys == null) return;
      diretion = (n == 3) ? new Vector3(mys.x + 1, mys.y, mys.z) : myObject.right();
      trag = (n == 3) ? new Vector3(my.x + 8f, my.y + 2.5f, my.z + 5) : new Vector3(my.x + 5, my.y + 2.5f, my.z - 8f);
      LaserHit hit = l.trace(trag, diretion, 1f);
      if (hit == null) return;
      Staticbody st = hit.getColliderObject().getPhysics().getPhysicsEntity();
      if (st == null || st.getPhysicsLayer() == phys) return;
      Console.log(hit.getColliderObject().name);
      myObject.destroy();
    }
  }
}
