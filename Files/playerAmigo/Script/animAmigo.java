public class animAmigo {    
  public void PlayAnimation(SpatialObject obj, Texture sprite, int animX, int animY) {
    if (obj == null || sprite == null) return;
    ModelRenderer model = obj.findComponent("ModelRenderer");
    if (model != null && model.material != null) {
      model.material.setAlbedo(sprite);
      atlas(model, animX, animY);
    } 
  }

  private void atlas(ModelRenderer model, int x, int y) {
    model.material.setVector2("AlbedoOffset", new Vector2(x * .329f, y * .3348f));
    model.material.setVector2("AlbedoTilling", new Vector2(0.333333f, .333333f));
  }
}
