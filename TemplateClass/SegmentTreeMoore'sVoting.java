//Leetcode -> 1157. Online Majority Element In Subarray -> https://leetcode.com/problems/online-majority-element-in-subarray/description/
class SegmentTree{
    static class Pair{
        int val;
        int freq;
        Pair(int val,int freq){
            this.val = val;
            this.freq = freq;
        }
    }
    class Node{
        Pair info;
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
    SegmentTree(int[] nums){
        this.root = constructTree(nums,0,nums.length - 1);
    }
    private Pair mergePair(Pair temp1,Pair temp2){
        if(temp1.val == temp2.val){
            return new Pair(temp1.val,(temp1.freq + temp2.freq));
        }
        if(temp1.freq > temp2.freq){
            return new Pair(temp1.val,(temp1.freq - temp2.freq));
        }
        return new Pair(temp2.val,(temp2.freq - temp1.freq));
    }
    private Node constructTree(int[] nums,int start,int end){
        if(start == end){
            Node node = new Node(start,end);
            node.info = new Pair(nums[start],1);
            return node;
        }
        Node node = new Node(start,end);
        int mid = (start + end) / 2;
        node.left = constructTree(nums,start,mid);
        node.right = constructTree(nums,mid + 1,end);
        node.info = mergePair(node.left.info,node.right.info);
        return node;
    }
    public Pair query(int left, int right){
        return query(root,left,right);
    }
    private Pair query(Node node,int start,int end){
        if(node.startInterval >= start && node.endInterval <= end){
            return node.info;
        }
        else if(node.startInterval > end || node.endInterval < start){
            return new Pair(0,0);
        }
        else{
            return mergePair(query(node.left,start,end), query(node.right,start,end));
        }
    }
}
class MajorityChecker {
    SegmentTree sg;
    Map<Integer,List<Integer>> map; 
    public MajorityChecker(int[] arr) {
        sg = new SegmentTree(arr);
        this.map = new HashMap<>();
        for(int i = 0;i < arr.length; i++){
            List<Integer> temp = map.get(arr[i]);
            if(temp == null){
                temp = new ArrayList<>();
                map.put(arr[i],temp);
                
            }
            temp.add(i);
            //map.put(arr[i],temp);
        }
    }
    
    public int query(int left, int right, int threshold) {
        SegmentTree.Pair ans = sg.query(left,right);
        if(ans.freq >= threshold){
            return ans.val;
        }
        if(ans.val == 0) return -1;
        List<Integer> temp = map.get(ans.val);
        int leftInd = Collections.binarySearch(temp,left);
        int rightInd = Collections.binarySearch(temp,right);
        if(leftInd < 0){
            leftInd = -leftInd - 1;
        }
        if(rightInd < 0){
            rightInd = -rightInd - 2;
        }
        if(rightInd - leftInd + 1 >= threshold) return ans.val;
        return -1;
    }
}
