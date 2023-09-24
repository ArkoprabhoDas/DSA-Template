class Trie {
    Node root;
    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        Node node = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(!node.containsKey(ch)) node.put(ch,new Node());
            node = node.get(ch);
        }
        node.changeEnd(true);
    }
    
    public boolean search(String word) {
        Node node = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(!node.containsKey(ch)) return false;
            node = node.get(ch);
        }  
        return node.isEnd();      
    }
    
    public boolean startsWith(String prefix) {
        Node node = root;
        for(int i=0;i<prefix.length();i++){
            char ch = prefix.charAt(i);
            if(!node.containsKey(ch)) return false;
            node = node.get(ch);
        }  
        return true;        
    }
}
class Node {
     private Node links[];
     private boolean flag;
    public Node(){
        this.links = new Node[26];
        flag = false;
    }
    public boolean containsKey(char ch){
        return links[ch-'a'] != null;
    }
    public Node get(char ch){
        return links[ch-'a'];
    }
    public void put(char ch,Node node){
        links[ch-'a'] = node;
    }
    public void changeEnd(boolean bool){
        flag = bool;
    }
    public boolean isEnd(){
        return flag;
    }
}
