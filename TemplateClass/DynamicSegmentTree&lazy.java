// 2569. Handling Sum Queries After Update -> https://leetcode.com/problems/handling-sum-queries-after-update/description/
class SegmentTree{
    class Node{
        long one;
        long zero;
        int pending;
        int startInterval;
        int endInterval;
        Node left;
        Node right;
        Node(int startInterval,int endInterval){
            this.startInterval = startInterval;
            this.endInterval = endInterval;
            this.pending = 0;
            this.one = 0;
            this.zero = 0;
        }
    }
    Node root;
    SegmentTree(int[] nums){
        this.root = constructTree(nums,0,nums.length - 1);
    }
    private Node constructTree(int[] nums,int left,int right){
        if(left == right){
            Node node = new Node(left,right);
            if(nums[left] == 1){
                node.one = 1;
            }
            if(nums[left] == 0){
                node.zero = 1;
            }
            return node;
        }
        Node node = new Node(left,right);
        int mid = (left + right) / 2;
        node.left = constructTree(nums,left,mid);
        node.right = constructTree(nums,mid + 1,right);
        node.one = node.left.one + node.right.one;
        node.zero = node.left.zero + node.right.zero;
        return node;
    }
    public void update(int left,int right){
        update(root,left,right);
    }
    private Node update(Node node,int left,int right){
        if(node.pending > 0){
            if(node.pending % 2 == 0){
                int a = 0;
            }
            else{
                long temp = node.zero;
                node.zero = node.one;
                node.one = temp;
            }
            if(node.left != null){
                node.left.pending += node.pending;
                node.right.pending += node.pending;
            }
            node.pending = 0;
        }
        if(node.startInterval >= left && node.endInterval <= right){
            long temp = node.zero;
            node.zero = node.one;
            node.one = temp;
            if(node.left != null){
                node.left.pending += 1;
                node.right.pending += 1;
            }
            return node;
        }
        if(node.startInterval > right || node.endInterval < left) return node;
        Node leftAns = update(node.left,left,right);
        Node rightAns = update(node.right,left,right);
        node.one = node.left.one + node.right.one;
        node.zero = node.left.zero + node.right.zero;
        return node;
    }
}
