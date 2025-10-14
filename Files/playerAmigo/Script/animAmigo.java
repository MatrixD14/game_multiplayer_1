public class animAmigo {
  private int maxPlayer;

  public animAmigo(int Maxplayer) {
    this.maxPlayer = Maxplayer;
  }

  public void PlayAnimation(SpatialObject obj, Texture sprite, int animY, int id, boolean isMove, int frame) {
    if (obj == null || sprite == null) return;
    ModelRenderer model = obj.findComponent("ModelRenderer");
    if (model != null && model.material != null) {
      model.material.setAlbedo(sprite);
      if (isMove) atlas(model, frame, animY);
      else atlas(model, 0, animY);
    }
  } 

  private void atlas(ModelRenderer model, int x, int y) {
    model.material.setVector2("AlbedoOffset", new Vector2(x * .329f, y * .3348f));
    model.material.setVector2("AlbedoTilling", new Vector2(0.333333f, .333333f));
  }
}
