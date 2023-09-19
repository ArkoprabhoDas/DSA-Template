class DisjointSet{
    int[] parent;
    //int[] rank;
    int[] size;
    int len;
    public DisjointSet(int n){
        this.len = n+1;
        this.parent = new int[len];
        //this.rank = new int[size];
        this.size = new int[len];
        for(int i=0;i<len;i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    public boolean union(int n1,int n2){
        int p1 = findParent(n1);
        int p2 = findParent(n2);
        if(p1==p2) return true;
        if(size[p1]>=size[p2]){
            size[p1] += size[p2];
            parent[p2] = p1;
        }else{
            size[p2] += size[p1];
            parent[p1] = p2;
        }
        // if(rank[p1]>=rank[p2]){
        //     if(rank[p1]==rank[p2]) rank[p1]++;
        //     parent[p2] = p1;
        // }else{
        //     parent[p1] = p2;
        // }
        return false;
    }
    public int findParent(int node){
        if(node==parent[node]) return node;
        return parent[node] = findParent(parent[node]);
    }
}
