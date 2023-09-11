//Leetcode -> 307. Range Sum Query - Mutable -> https://leetcode.com/problems/range-sum-query-mutable/
class SegmentTree{
    class Node{
        int data;
        int startInterval;
        int endInterval;
        Node left;
        Node right;
        Node(int startInterval,int endInterval){
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }
    }
    Node root;
    public SegmentTree(int[] nums){
        this.root = constructTree(nums,0,nums.length - 1);
    }
    private Node constructTree(int[] nums,int start,int end){
        if(start == end){
            Node node = new Node(start,end);
            node.data = nums[start];
            return node;
        }
        Node node = new Node(start,end);
        int mid = (start + end) / 2;
        node.left = constructTree(nums,start,mid);
        node.right = constructTree(nums,mid + 1,end);
        node.data = node.left.data + node.right.data;
        return node;
    }
    public int query(int start,int end){
        return query(this.root,start,end);
    }
    private int query(Node node,int start,int end){
        if(node.startInterval >= start && node.endInterval <= end){
            return node.data;
        }
        else if(node.startInterval > end || node.endInterval < start){
            return 0;
        }
        else{
            return query(node.left,start,end) + query(node.right,start,end);
        }
    }
    public void update(int index,int value){
        this.root.data = update(this.root,index,value);
    }
    private int update(Node node,int index,int value){
        if(node.startInterval <= index && node.endInterval >= index){
            if(node.startInterval == index && node.endInterval == index){
                node.data = value;
                return node.data;
            }
            else{
                int leftAns = update(node.left,index,value);
                int rightAns = update(node.right,index,value);
                node.data = leftAns + rightAns;
                return node.data;
            }
        }
        return node.data;
    } 
