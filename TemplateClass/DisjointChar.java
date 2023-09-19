class DisjointSet{
    char[] parent;
    int[] size;
    int len;
    public DisjointSet(){
        this.len = 1+'z';
        this.parent = new char[len];
        this.size = new int[len];
        for(char i='a';i<='z';i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    public void union(char n1,char n2){
        char p1 = findParent(n1);
        char p2 = findParent(n2);
        if(p1==p2) return;
        if(size[p1]>=size[p2]){
            size[p1] += size[p2];
            parent[p2] = p1;
        }else{
            size[p2] += size[p1];
            parent[p1] = p2;
        }
    }
    public char findParent(char node){
        if(node==parent[node]) return node;
        return parent[node] = findParent(parent[node]);
    }
}
