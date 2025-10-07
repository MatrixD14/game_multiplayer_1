public class handleProtocolo {
   public void handleRot(String txt,int myId,float[][] rotCache,int[] remoteId,int maxPlayer){
     String[] p = txt.split(":");
    int id = Integer.parseInt(p[1]);
    if (id == myId) return;
    float x = Float.parseFloat(p[2]);
    float y = Float.parseFloat(p[3]);
    float z = Float.parseFloat(p[4]);

    for (int i = 0; i < maxPlayer; i++) {
      if (remoteId[i] == id) {
        rotCache[i][0] = x;
        rotCache[i][1] = y;
        rotCache[i][2] = z;
        break;
      }
    }
  } 
  public void handlePos(String txt,int myId,float[][] posCache,int[] remoteId,int maxPlayer){
     String[] p = txt.split(":");
    int id = Integer.parseInt(p[1]);
    if (id == myId) return;
    float x = Float.parseFloat(p[2]);
    float y = Float.parseFloat(p[3]);
    float z = Float.parseFloat(p[4]);

    for (int i = 0; i < maxPlayer; i++) {
      if (remoteId[i] == id) {
        posCache[i][0] = x;
        posCache[i][1] = y;
        posCache[i][2] = z;
        break;
      }
    }
  }
}