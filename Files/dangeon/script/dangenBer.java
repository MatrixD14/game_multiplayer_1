public class dangenBer extends Component {
  public SpatialObject[] walls = new SpatialObject[4], doors = new SpatialObject[4];

  private void Obj() {
    for (int i = 0; i < 4; i++) {
      walls[i] = myObject.findChildObject("parede" + (i + 1));
      doors[i] = myObject.findChildObject("door" + (i + 1));
    }
  }

  public void UpdateRoom(boolean[] status, String txt) {
    Obj();
    Console.log(txt + "room Update");
    for (int i = 0; i < status.length; i++) {
      Console.log(status[i]);
      this.doors[i].setEnabled(status[i]);
      this.walls[i].setEnabled(!status[i]);
    } 
  }
}
