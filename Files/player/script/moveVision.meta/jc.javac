public class moveVision extends Component {
  private Vector2 joy,slid;
  private Characterbody ch;
  private float speedJ = 2;
  private float x, y,camx,camy;
  private SpatialObject  cam;
  private float cammin=-80,cammax=80;

  void start() {
    joy = Input.getAxisValue("joy");
    slid =Input.getAxisValue("slid");
    cam =myObject.findChildObject("vision");
    ch = myObject.getPhysics().getPhysicsEntity();
  }

  void repeat() {
    if (key("w") || key("s") || key("a") || key("d")) {
      movekey();
    } else {
      move(joy.x * speedJ, joy.y * speedJ);
    }
    slidCam(slid.x,slid.y);
  }

  private void movekey() {
    float x = 0, y = 0;
    if (key("w")) y = +1;
    if (key("s")) y = -1;
    if (key("a")) x = -1;
    if (key("d")) x = +1;
    move(x * speedJ, -y * speedJ);
  }

  private boolean key(String key) {
    if (Input.keyboard.isKeyPressed(key)) return true;
    return false;
  }

  private void move(float x, float y) {
    ch.setSpeed(-x,-y);
  }

  private void slidCam(float x, float y) {
      camx += x;
      myObject.getRotation().selfLookTo(new Vector3(Math.sin(-camx),0,Math.cos(-camx)));
      camy =Math.clamp(cammin,(camy+=y),cammax);
      cam.getRotation().selfLookTo(new Vector3(0,Math.sin(-camy),Math.cos(-camy)));
  }

  /*
  float shakeAmount = .15f, times = 0;
  public boolean onoff = true;

  private void shake(float value) {
    if (!onoff) return;
    Vector3 mypos = obj.position.copy();
    if (onoff) {
      times += 0.01f;
      float offsetX = (float) Random.range(-shakeAmount, shakeAmount);
      float offsetZ = (float) Random.range(-shakeAmount, shakeAmount);
      obj.setPosition(mypos.x + offsetX, mypos.y, mypos.z + offsetZ);
      if (times > value) {
        obj.setPosition(mypos);
        onoff = false;
        times = 0;
      }
    }
  }*/
} // 0.329f
