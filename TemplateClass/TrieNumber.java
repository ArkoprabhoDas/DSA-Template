class Trie{
    private Node root;
    public Trie(){
        root = new Node();
    }
    public void insert(int num){
        Node node = root;
        int shift = 32;
        while(shift>0){
            int bit = ((num>>(shift-1))&1);
            if(!node.containsBit(bit)) node.putBit(bit,new Node());
            node = node.getBit(bit);
            shift--;
        }
    }
    //TODO: change calculation 
    public int calculate(int num){
        Node node = root;
        int shift = 32;
        int val = 0;
        while(shift>0){
            int cur = ((num>>(shift-1))&1);
            int bit = cur;
            if(node.containsBit(bit^1)) bit = (bit^1);
            node = node.getBit(bit);
            shift--;
            val+=((cur^bit)<<shift);
        }
        return val;
    }
}

class Node{
    private Node zero;
    private Node one;
    public Node(){}
    public void putBit(int bit,Node node){
        if(bit==0) zero = node;
        else one = node;
    }
    public Node getBit(int bit){
        if(bit==0) return zero;
        else return one;
    }

    public boolean containsBit(int bit){
        if(bit==0) return zero!=null;
        else return one!=null;
    }
}
