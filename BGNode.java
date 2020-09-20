
public class BGNode {

  private BoardingGroup group;
  private BGNode next;

  public BGNode(BoardingGroup group) {
    this.group = group;
    this.next = null;
  }

  public BGNode(BoardingGroup group, BGNode next) {
    this.group = group;
    this.next = next;
  }

  public BoardingGroup getGroup() {
    return group;
  }

  public BGNode getNext() {
    return next;
  }

  public void setNext(BGNode next) {
    this.next = next;
  }

}
