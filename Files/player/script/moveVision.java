public class moveVision extends Component {
  private Vector2 joy;
  private float speedJ = 2;
  private float x, y;
  public Texture[] spaits = new Texture[10];
  private ModelRenderer model;
  private SpatialObject obj;
  private int anim = 0, AnimFC = 0;
  private float FrameTime = 0, delay = .15f;
  private int mvSkin = 0;

  void start() {
    joy = Input.getAxisValue("joy");
    obj = myObject.findChildObject("model");
    model = obj.findComponent("ModelRenderer");
  }

  void repeat() {
    if (Input.isKeyDown("mvSkin")) mvSkin++;
    if (mvSkin >= spaits.length) mvSkin = 0;
    model.material.setAlbedo(spaits[mvSkin]);
    if (key("w") || key("s") || key("a") || key("d")) {
      movekey();
    } else {
      move(joy.x * speedJ, joy.y * speedJ);
      animation(joy.x * speedJ, joy.y * speedJ);
    } 
  }

  private void movekey() {
    float x = 0, y = 0;
    if (key("w")) y = +1;
    if (key("s")) y = -1;
    if (key("a")) x = -1;
    if (key("d")) x = +1;
    move(x * speedJ, -y * speedJ);
    animation(x * speedJ, -y * speedJ);
  }

  private boolean key(String key) {
    if (Input.keyboard.isKeyPressed(key)) return true;
    return false;
  }

  private void move(float x, float y) {
    myObject.moveInSeconds(x, 0, y);
    // if (x != 0 || y != 0) Console.log("x: " + x + "y: " + y);
  }

  private void animation(float x, float y) {
    if (Math.abs(x) == 0 && Math.abs(y) == 0) {
      anim = 0;
      atlas(0, AnimFC);
      return;
    }
    FrameTime += Time.deltatime();
    if (FrameTime >= delay) {
      FrameTime = 0;
      if ((anim++) > 1) anim = 0;
    }
    if (Math.abs(y) > Math.abs(x)) {
      AnimFC = (y > 0) ? 0 : 1;
    } else {
      AnimFC = 2;
      if (x < 0) obj.setScale(1, 1, 1);
      else if (x > 0) obj.setScale(-1f, 1, 1);
    }
    atlas(anim, AnimFC);
  }

  public void atlas(int x, int y) {
    model.material.setVector2("AlbedoOffset", new Vector2(x * .329f, y * .3348f));
    model.material.setVector2("AlbedoTilling", new Vector2(0.333333f, .333333f));
  }

  public int getAnimFC() {
    return AnimFC;
  }

  public int getAnim() {
    return anim;
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
