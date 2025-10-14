public class handleProtocolo {
  public void handleRot(String txt, int myId, Vector3Buffer rotBufferCache, int[] remoteId, int maxPlayer) {
    String[] p = txt.split(":");
    int id = Integer.parseInt(p[1]);
    if (id == myId) return;
    float x = Float.parseFloat(p[2]);
    float y = Float.parseFloat(p[3]);
    float z = Float.parseFloat(p[4]);

    for (int i = 0; i < maxPlayer; i++) {
      if (remoteId[i] == id) {
        rotBufferCache.set(i, x, y, z);
        break;
      }
    }
  }

  public void handlePos(String txt, int myId, Vector3Buffer posBufferCache, int[] remoteId, int maxPlayer) {
    String[] p = txt.split(":");
    int id = Integer.parseInt(p[1]);
    if (id == myId) return;
    float x = Float.parseFloat(p[2]);
    float y = Float.parseFloat(p[3]);
    float z = Float.parseFloat(p[4]);

    for (int i = 0; i < maxPlayer; i++) {
      if (remoteId[i] == id) {
        posBufferCache.set(i, x, y, z);
        break;
      }
    }
  }

  public void handleAnim(String txt, int myId, IntBuffer animBufferCache, IntBuffer DirBufferCache, IntBuffer moveBufferCache, int[] remoteId, int maxPlayer) {
    String[] p = txt.split(":");
    int id = Integer.parseInt(p[1]);
    if (id == myId) return;
    int anim = Integer.parseInt(p[2]);
    int dir = Integer.parseInt(p[3]);
    int move = Integer.parseInt(p[4]);
    for (int i = 0; i < maxPlayer; i++) {
      if (remoteId[i] == id) {
        animBufferCache.put(i, anim);
        DirBufferCache.put(i, dir);
        moveBufferCache.put(i, move);
        break;
      } 
    }
  }
}
