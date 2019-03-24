/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */
public class MoveNode {

    private int fromPip;
    private int toPip;
    private boolean isHit = false;

    public MoveNode(int fromPip, int toPip) {
        this.fromPip = fromPip;
        this.toPip = toPip;
    }

    public int getFromPip() {
        return fromPip;
    }

    public int getToPip() {
        return toPip;
    }

    public void setFromPip(int fromPip) {
        this.fromPip = fromPip;
    }

    public void setToPip(int toPip) {
        this.toPip = toPip;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
