public class moveVision extends Component {
  private Vector2 joy;
  private float speedJ = 2;
  private float x, y;
  public Texture[] spaits = new Texture[12];
  private ModelRenderer model;
  private int anim = 0, AnimFC = 0;
  private float FrameTime = 0, delay = .15f;

  void start() {
    joy = Input.getAxisValue("joy");
    model = WorldController.findObject("model").findComponent("ModelRenderer");
    spaits[10] = rotTexture(spaits[8]);
    spaits[11] = rotTexture(spaits[9]);
  }

  void repeat() {
    if (key("w") || key("s") || key("a") || key("d")) {
      movekey();
    } else move(joy.x * speedJ, joy.y * speedJ);
    animation(joy.x * speedJ, joy.y * speedJ);
  }

  private void movekey() {
    float x = 0, y = 0;
    if (key("w")) y = +1;
    if (key("s")) y = -1;
    if (key("a")) x = -1;
    if (key("d")) x = +1;
    move(x * speedJ, y * speedJ);
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
    if (x == 0 && y == 0) {
      model.material.setAlbedo(spaits[AnimFC]);
      return;
    }
    FrameTime += Time.deltatime();
    if (FrameTime >= delay) {
      FrameTime = 0;
      if ((anim++) >= 3) anim = 0;
    }
    if (Math.abs(y) > Math.abs(x)) {
      if (y > 0) {
        AnimFC = 0;
        model.material.setAlbedo(spaits[(anim == 0) ? 4 : (anim == 1) ? 0 : 5]);
      } else {
        AnimFC = 1;
        model.material.setAlbedo(spaits[(anim == 0) ? 6 : (anim == 1) ? 1 : 7]);
      }
    } else {
      if (x < 0) {
        AnimFC = 2;
        model.material.setAlbedo(spaits[(anim == 0) ? 8 : (anim == 1) ? 2 : 9]);
      } else if (x > 0) {
        AnimFC = 3;
        model.material.setAlbedo(spaits[(anim == 0) ? 10 : (anim == 1) ? 3 : 11]);
      }
    }
  }
  private Texture rotTexture(Texture t) {
    if (t == null) return Texture.White();
    int w = t.getWidth();
    int h = t.getHeight();
    Texture rot = new Texture(w, h, true);

    for (int x = 0; x < w; x++) {
      for (int y = 0; y < h; y++) {
        Color c = t.getPixel(x, y);
        rot.set(w - 1 - x, y, c);
      } 
    }
    rot.apply();
    rot.mipmapEnabled = false;
    // rot.setFilter("Pixel");
    return rot;
  }
}
