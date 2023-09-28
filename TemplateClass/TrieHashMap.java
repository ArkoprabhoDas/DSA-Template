//Leetcode -> 208. Implement Trie (Prefix Tree) -> https://leetcode.com/problems/implement-trie-prefix-tree/description/
class Node{
    Map<Character,Node> map = new HashMap<>();
    boolean flag = false;
    public Node(){}

    public boolean containsKey(char ch){
        return map.containsKey(ch);
    }

    public Node get(char ch){
        return map.get(ch);
    }

    public void put(char ch,Node node){
        map.put(ch,node);
    }

    public void setEnd(){
        flag = true;
    }

    public boolean getEnd(){
        return flag;
    }
}
class Trie {
    Node root; 
    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        Node node = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(!node.containsKey(ch)){
                node.put(ch,new Node());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }
    public boolean search(String word) {
        Node node = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(!node.containsKey(ch)){
                return false;
            }
            node = node.get(ch);
        }
        return node.getEnd();
    }
    
    public boolean startsWith(String prefix) {
        Node node = root;
        for(int i = 0; i < prefix.length(); i++){
            char ch = prefix.charAt(i);
            if(!node.containsKey(ch)){
                return false;
            }
            node = node.get(ch);
        }
        return true;
    }
}
