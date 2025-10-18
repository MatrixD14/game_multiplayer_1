public class dangenBer extends Component {
  public ObjectFile walls, doors;

  public void UpdateRoom(boolean[] status, String txt, ObjectFile walls, ObjectFile doors) {
    Console.log(txt + "room Update");
    for (int i = 0; i < status.length; i++) {
      ObjectFile obj = status[i] ? doors : walls;
      float rot = (i == 0) ? -90 : (i == 1) ? 180 : (i == 2) ? 90 : 0;
      SpatialObject ob = myObject.instantiate(obj, new Vector3(0, 0, 0));
      ob.setParent(myObject);
      Quaternion rots = new Quaternion();
      rots.setFromEuler(new Vector3(0, rot, 0));
      ob.setRotation(rots);
    } 
  }
}
