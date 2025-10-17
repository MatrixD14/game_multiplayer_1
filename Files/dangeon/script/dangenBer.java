public class dangenBer extends Component {
  public SpatialObject[] walls = new SpatialObject[4], doors = new SpatialObject[4];

  public void UpdateRoom(boolean[] status) {
    for (int i = 0; i < status.length; i++) {
      doors[i].setEnabled(status[i]);
      walls[i].setEnabled(!status[i]);
    } 
  }
}
