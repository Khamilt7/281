public class LinkedDeque {      
    Node firstnode; 
    Node lastnode;
    int size = 0;
    public LinkedDeque () {
        
    }
    
    public void insertLeft ( Object o ) {
        firstnode = new Node(o, null, firstnode);
        size++;
    }
    
    public void insertRight ( Object o ) {
        lastnode = new Node(o,lastnode, null);
        size++;
    }
    
    public void deleteLeft () {
        firstnode = firstnode.right;
        firstnode.left = null;
        size--;
    }
    
    public void deleteRight () {
        lastnode = lastnode.left;
        lastnode.right = null;
        size--;
    }
    
    public Object left () {
        return firstnode.information;
    }
    
    public Object right () {
        return lastnode.information;
    }
    
    public int size () {
        return size;
    }
    
    public String toString () {
        if(firstnode == null) {
            return "";
        }
        return firstnode.toString("");
    }
    
    public static void main ( String[] args ) {
        LinkedDeque testDeque = new LinkedDeque();
        //testDeque.insertLeft("dog");
        //testDeque.insertLeft("cat");
        //testDeque.insertLeft("dogcat");
        testDeque.insertRight("catdog");
        System.out.println(testDeque.toString());
        //testDeque.deleteRight();
        System.out.println(testDeque.size());
        System.out.println(testDeque.toString());
        System.out.println(testDeque.right());
    }
    class Node {
        Object information;
        Node left;
        Node right;      
    
    public Node(Object information) {
        this.information = information;
    }
    public Node(Object information,Node left,Node right) {
        this.left = left;
        this.right = right;
        this.information = information;
        if(left != null) {
            left.right = this; 
        }
        if(right != null) {
            right.left = this;
        }
    }
    public String toString(String str) {
        str += "[" + information + "]";
        return (right == null? str:right.toString(str));
    }
 }
}
