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
            node.insertPrefix();
        }
        node.insertEnd();
    }
    
    public int search(String word) {
        Node node = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(!node.containsKey(ch)) return 0;
            node = node.get(ch);
        }  
        return node.getEnd();      
    }
    
    public int startsWith(String prefix) {
        Node node = root;
        for(int i=0;i<prefix.length();i++){
            char ch = prefix.charAt(i);
            if(!node.containsKey(ch)) return 0;
            node = node.get(ch);
        }  
        return node.getPrefix();        
    }
    public boolean erase(String word) {
    	if(search(word)==0) return false;
        Node node = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            node = node.get(ch);
            node.deletePrefix();
        }
        node.deleteEnd();
        return true;
    }
}
class Node {
     private Node links[];
     private int prefixCnt;
     private int endCnt;
    public Node(){
        this.links = new Node[26];
        prefixCnt = 0;
        endCnt = 0;
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
    public void insertEnd(){
    	endCnt++;
    }
    public void deleteEnd() {
    	endCnt--;
    }
    public void insertPrefix() {
    	prefixCnt++;
    }
    public void deletePrefix() {
    	prefixCnt--;
    }
    public int getPrefix() {
    	return prefixCnt;
    }
    public int getEnd(){
        return endCnt;
    }
}
