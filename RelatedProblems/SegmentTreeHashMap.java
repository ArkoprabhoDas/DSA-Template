//Leetcode 2080   -> https://leetcode.com/problems/range-frequency-queries/
class SegmentTree{
    class Node{
        Map<Integer,Integer> hm;
        int startInterval;
        int endInterval;
        Node left;
        Node right;
        
        public Node(int startInterval,int endInterval){
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }
    }
    Node root;
    public SegmentTree(int[] nums){
        this.root = constructTree(nums,0,nums.length - 1);
    }
    private Map<Integer,Integer> merge(Map<Integer,Integer> hm1,Map<Integer,Integer> hm2){
        Map<Integer,Integer> hm = new HashMap<>();
        for(Map.Entry<Integer,Integer> ele : hm1.entrySet()){
            int key = ele.getKey();
            int value = ele.getValue();
            hm.put(key,hm.getOrDefault(key,0) + value);
        }
        for(Map.Entry<Integer,Integer> ele : hm2.entrySet()){
            int key = ele.getKey();
            int value = ele.getValue();
            hm.put(key,hm.getOrDefault(key,0) + value);
        }
        return hm;
    }
    private Node constructTree(int[] nums,int start,int end){
        if(start == end){
            Node node = new Node(start,end);
            node.hm = new HashMap<>();
            node.hm.put(nums[start],1);
            
            return node;
        }
        Node node = new Node(start,end);
        int mid = (start + end) / 2;
        node.left = constructTree(nums,start,mid);
        node.right = constructTree(nums,mid + 1,end);
        node.hm = merge(node.left.hm,node.right.hm);
        //System.out.println(node.hm + " " + start + " " + end);
        return node;
    }
    public int query(int left, int right, int value){
        return query(root,left,right,value);
    }
    private int query(Node node,int start,int end,int value){
        if(node.startInterval >= start && node.endInterval <= end){
            return node.hm.getOrDefault(value,0);
        }
        else if(node.startInterval > end || node.endInterval < start){
            return 0;
        }
        else{
            return query(node.left,start,end,value) + query(node.right,start,end,value);
        }
    }
}
