public class dangenBer {
  public ObjectFile walls, doors;

  public void UpdateRoom(boolean[] status, String txt, SpatialObject objs, VertexFile walls, VertexFile doors) {
    for (int i = 0; i < status.length; i++) {
      VertexFile obj = status[i] ? doors : walls;
      float rot = (i == 0) ? -90 : (i == 1) ? 180 : (i == 2) ? 90 : 0;
      SpatialObject ob = new SpatialObject("name" + rot);
      if (i == 3 || i == 2) {
        ob.setTag("wall");
        ob.getPhysics().setPhysicsEntity(new Staticbody());
      }
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
      if (i == 3) ob.addComponent(new checkPhysicsL());
      if (i == 2) ob.addComponent(new checkPhysicsR());
    }
  }

  public class checkPhysicsL extends Component {
    float t = 0;

    void repeat() {
      if ((t += 0.01f) > .5f) return;
      Vector3 my = myObject.globalPosition;
      Vector3 myp = myObject.position;
      LaserHit hit = new Laser().trace(new Vector3(my.x, my.y + 2, my.z + 5), new Vector3(myp.x + 1, myp.y, myp.z), 8f);
      if (hit == null || "wall".equals(hit.getObject().tag)) return;
      Console.log(hit.getObject().name);
      myObject.destroy();
    } 
  }

  public class checkPhysicsR extends Component {
    float t = 0;

    void repeat() {
      if ((t += 0.01f) > .5f) return;
      Vector3 my = myObject.globalPosition;
      LaserHit hit = new Laser().trace(new Vector3(my.x + 5, my.y + 2, my.z), myObject.right(), 8f);
      //if (hit == null || hit.getObject().tag.equals(hit.getObject().tag)) return;
      if (hit == null || "wall".equals(hit.getObject().tag)) return;
      Console.log(hit.getObject().getName());
      myObject.destroy();
    }
  }
}
