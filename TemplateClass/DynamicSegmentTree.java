//LeetCode -> 715. Range Module -> https://leetcode.com/problems/range-module/
class SegmentTree{
  class Node{
    int low;
    int high;
    boolean state;
    Node left;
    Node right;
    Node(int low,int d){
      this.low = low;
      this.high = d;
      this.state = false;
    }
  }
  Node rootNode;
  SegmentTree(){
    this.rootNode = new Node(0, (int)(1e9));
  }
  public void addRange(int left,int right){
    update(rootNode,left,right,true);
  }
  public void removeRange(int left,int right){
    update(rootNode, left, right, false);
  }
  public boolean queryRange(int left, int right) {
    return query(this.rootNode,left, right);
  }
  private boolean update(Node node, int left, int right, boolean state) {
    if(node.low >= left && node.high <= right){
      node.state = state;
      node.left = null;
      node.right = null;
      return node.state;
    }
    if(node.low > right || node.high < left) return node.state;
    int mid = node.low + (node.high - node.low) / 2;
    if(node.left == null){
      node.left = new Node(node.low, mid);
      node.left.state = node.state;
      node.right = new Node(mid+1, node.high);
      node.right.state = node.state;
    }
    boolean leftAns = update(node.left, left, right, state);
    boolean rightAns = update(node.right,left, right, state);
    node.state = leftAns & rightAns;
    return leftAns & rightAns;
  } 
  private boolean query(SegmentTree.Node node, int left, int right){
    if(node.low > right || node.high < left) return true;
    if((node.low >= left && node.high <= right) || node.left == null){
      
      return node.state;
    }
    return query(node.left, left, right) && query(node.right, left, right);
  }
}
