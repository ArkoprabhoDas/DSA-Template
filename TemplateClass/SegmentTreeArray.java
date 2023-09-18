class NumArray {
    int[] tree;
    int[] nums;
    public NumArray(int[] nums) {
        this.tree = new int[4*nums.length];
        this.nums = nums; 
        populate(0,0,nums.length-1);
    }

    public void populate(int index,int start,int end){
        if(start==end){
            tree[index] = nums[start];
        }else{
            int left = 2*index+1;
            int right = 2*index+2;
            int mid = (start+end)/2;
            populate(left,start,mid);
            populate(right,mid+1,end);
            tree[index] = tree[left]+tree[right];
        }
    }
    
    public void update(int index, int val) {
        int prev = nums[index];
        update(0,0,nums.length-1,index,val);
    }
    public void update(int id,int start,int end,int index,int val){
        if(start==end){
            nums[index] = val;
            tree[id] = val;
        }else{
            int left = 2*id+1;
            int right = 2*id+2;
            int mid = (start+end)/2;
            if(index<=mid) update(left,start,mid,index,val);
            else update(right,mid+1,end,index,val);
            tree[id] = tree[left]+tree[right];
        }
    }
    
    public int sumRange(int left, int right) {
        return sumRange(0,0,nums.length-1,left,right);
    }
    public int sumRange(int id,int start,int end,int left,int right){
        if(start>=left && end<=right) return tree[id];
        if(right<start||left>end) return 0;
        int l = 2*id+1;
        int r = 2*id+2;
        int mid = (start+end)/2;
        int sl =  sumRange(l,start,mid,left,right);
        int sr =  sumRange(r,mid+1,end,left,right);
        return sl+sr;
    }
}
