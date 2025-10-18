public class dangenBer extends Component {
  // public SpatialObject[] walls = new SpatialObject[4], doors = new SpatialObject[4];
  public ObjectFile walls, doors;

  /* private void Obj() {
    for (int i = 0; i < 4; i++) {
      walls[i] = myObject.findChildObject("parede" + (i + 1));
      doors[i] = myObject.findChildObject("door" + (i + 1));
    }
  }*/

  public void UpdateRoom(boolean[] status, String txt, ObjectFile walls, ObjectFile doors) {
    // Obj();
    Console.log(txt + "room Update");
    for (int i = 0; i < status.length; i++) {
      Console.log(status[i]);
      /*this.doors[i].setEnabled(status[i]);
      this.walls[i].setEnabled(!status[i]);*/
      ObjectFile obj = status[i] ? doors : walls;
      int rot = (i == 0) ? 270 : (i == 1) ? 180 : (i == 2) ? 90 : 0;
      SpatialObject ob = myObject.instantiate(obj, new Vector3(0, 0, 0));
      ob.setParent(myObject);
      ob.setRotation(0, (float) rot, 0);
    } 
  }
}
