class Trie{
    private Node root;
    public Trie(){
        root = new Node();
    }
    public void insert(String num){
        Node node = root;
        for(int i=0;i<num.length();i++){
            char ch = num.charAt(i);
            if(!node.containsKey(ch)) node.put(ch,new Node());
            node = node.get(ch);
        }
        node.setEnd();
    }
 
    public List<Integer> sort(){
        List<Integer> list = new ArrayList<>();
        sort(root,list,"0");
        return list;
    }

    private void sort(Node node,List<Integer> list,String num){
        if(node.isEnd()) list.add(Integer.parseInt(num));
        for(char i='0';i<='9';i++){
            if(node.containsKey(i)) sort(node.get(i),list,num+i);
        }
    }
}

class Node{
    private Node[] links;
    private boolean flag;
    public Node(){
        links = new Node[10];
        flag = false;
    }
    public boolean containsKey(char num){
        return links[num-'0']!=null;
    }
    public void put(char num,Node node){
        links[num-'0'] = node;
    }
    public Node get(char num){
        return links[num-'0'];
    }
    public void setEnd(){
        flag = true;
    }
    public boolean isEnd(){
        return flag;
    }
}
