import java.util.*;

public class BinaryTree implements Iterable {
    private int size = 0;
    public Node root; 
    public Node cursor;
    public BinaryTree() {
        this.root = null;
        this.cursor = this.root;
    }
    public BinaryTree(Object obj) {
        this.root = new Node(obj);
        this.cursor = root;
        size = 1;
    }
    public boolean contains(Object obj) {
        if(this.isEmpty()) {
            return false;
        }
        for (Object n : this) {
            if(n.equals(obj)) {
                return true;
            }
        }
        return false;
    }
    public boolean similar(Object obj) {
        if(!(obj instanceof BinaryTree) || ((BinaryTree)obj).size() != this.size()) {
            return false;
        } 
        Iterator IT1 = this.NodeIterator();
        Iterator IT2 = ((BinaryTree)obj).NodeIterator();
        while(IT1.hasNext()) {
            Node n1 = (Node)IT1.next();
            Node n2 = (Node)IT2.next();
            if((n1.lson != null && n2.lson == null) || (n1.lson == null && n2.lson != null)) {
                return false;
            }
            if((n1.rson != null && n2.rson == null) || (n1.rson == null && n2.rson != null)) {
                return false;
            }
        }
        return true;
    }
    public boolean equals(Object obj) {
        if(!(obj instanceof BinaryTree) || ((BinaryTree)obj).size() != this.size()) {
            return false;
        } 
        Iterator IT1 = this.iterator();
        Iterator IT2 = ((BinaryTree)obj).iterator();
        while(IT1.hasNext()) {
            Object o1 = IT1.next();
            Object o2 = IT2.next();
            if((o1 == null && o1 != o2) || !(o1.equals(o2))) {
                return false;    
            }
        }
        IT1 = this.inOrder();
        IT2 = ((BinaryTree)obj).inOrder();
        while(IT1.hasNext()) {
            Object o1 = IT1.next();
            Object o2 = IT2.next();
            if((o1 == null && o1 != o2) || !(o1.equals(o2))) {
                return false;    
            }
        }
        return true;
    }
    public boolean isEmpty() {
        return(size == 0);
    }
    public int size() {
        return size;
    }
    public int hashCode() {
        return super.hashCode();
    }
    public Iterator NodeIterator() {
        return new NodeIterator(this);
    }
    private class NodeIterator implements Iterator {
        Stack<Node> s = new Stack<Node>();
        Node current;
        Node temp;
        public NodeIterator(BinaryTree BT) {
            current = BT.root;
        }   
        public boolean hasNext() {
            return(current != null);
        }    
        public Object next() {           
            temp = current;
            if(current.lson != null && current.rson != null) {
                s.push(current);
                current = current.lson;
            } else if (current.lson != null && current.rson == null) {
                current = current.lson;
            } else if(current.lson == null && current.rson != null) {
                current = current.rson;
            } else {
                if(!s.empty()) {
                    current = ((Node)s.pop()).rson;
                } else {
                    current = null;
                }
            }
            return temp;
        }
        public void remove() {
            throw new UnsupportedOperationException();
            }
    }    
    public Iterator iterator() {
        return new preOrderIterator(this);
    }
    private class preOrderIterator implements Iterator {
        Stack<Node> s = new Stack<Node>();
        Node current;
        Node temp;
        public preOrderIterator(BinaryTree BT) {
            current = BT.root;
        }   
        public boolean hasNext() {
            return(current != null);
        }    
        public Object next() { 
        if(!hasNext()) {
            throw new NoSuchElementException();
        }           
            temp = current;
            if(current.lson != null && current.rson != null) {
                s.push(current);
                current = current.lson;
            } else if (current.lson != null && current.rson == null) {
                current = current.lson;
            } else if(current.lson == null && current.rson != null) {
                current = current.rson;
            } else {
                if(!s.empty()) {
                    current = ((Node)s.pop()).rson;
                } else {
                    current = null;
                }
            }
            return temp.getData();
        }
        public void remove() {
            throw new UnsupportedOperationException();
            }
    }
     public Iterator inOrder() {
         return new inOrderIterator(this);
     } 
    class inOrderIterator implements Iterator {    
        Stack<Node> s = new Stack<Node>();
        Node current;
        Node temp;  
        BinaryTree BT;
        int count;
        public inOrderIterator(BinaryTree BT) {
            this.BT = BT;
        }
        public boolean hasNext() {
             return count != BT.size();
        }    
        public Object next() { 
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
            if(current == null) {
                current = BT.root;
            }
            while(true) {
                if(!s.empty() && s.peek() == current) {
                    if(current.rson != null) {
                        current = current.rson;
                        continue;
                    } 
                    current = current.dad;
                    continue;
                }
                if(!s.empty() && s.peek() == current.lson) {
                    s.pop();
                    s.push(current);
                    count++;
                    return current.getData();
                }
                if(!s.empty() && s.peek() == current.rson) {
                    s.pop();
                    current = current.dad;
                    continue;
                }
            if(current.lson != null) {
                current = current.lson;
                continue;
            }
            count++;
            s.push(current);
            return current.getData();
        }
        }
        public void remove() {
            throw new UnsupportedOperationException();
            }
        }
     public boolean putCursorAtRoot() {
        if(root != null) {
            cursor = root;
                return true;
        } else {
            return false;
        }
     }
     public boolean putCursorAtLeftSon() {
         if(cursor.lson != null) {
            cursor = cursor.getlSon();
                return true; 
         } else {
             return false;
         }
     }
     public boolean putCursorAtRightSon() {
         if(cursor.rson != null) {
            cursor = cursor.getrSon();
                return true; 
         } else {
             return false;
         }       
     }
     public boolean putCursorAtFather() {
        if(cursor.dad != null) {
            cursor = cursor.getDad();
            return true;
        } else {
            return false;
        }
     }
     public boolean attachLeftSonAtCursor(Object obj) {
        if(cursor.lson != null) {
            return false;
        } else {
            cursor.lson = new Node(obj);
            cursor.lson.dad = cursor;
            size++;
            return true; 
        }
     }
     public boolean attachRightSonAtCursor(Object obj) {
        if(cursor.rson != null) {
            return false;
        } else {
            cursor.rson = new Node(obj);
            cursor.rson.dad = cursor; 
            size++;
            return true;
        }
     }
     public boolean pruneFromCursor() {
        if(size == 0) {
            return false;
        }
        if(this.cursor.getDad() == null) {
            this.root = null;
            this.cursor = root;
            size = 0;
            return true;
        }
        Node p = this.cursor.dad; 
            if(this.cursor.dad.lson == this.cursor) {
                p.lson = null;               
            }  else {
                p.rson = null;
            }
            cursor = this.root;
            int count = 0;
            for(Object n : this) {
                count++;
            }
            size = count;
        return true;
     }
    private static int attempts = 0;
    private static int successes = 0;
	//method to assist in testing carried out in "main"
	private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }
     public static void main ( String[] args ) {
        System.out.println("Testing BinaryTree Constructors..."); 
try {
             BinaryTree testTree = new BinaryTree();
            displaySuccessIfTrue(testTree.root == null && testTree.cursor == null && testTree.size() == 0);
         } catch (Exception e) {
             e.printStackTrace();
         }
   try {
       BinaryTree testTree = new BinaryTree("Map");
        displaySuccessIfTrue(testTree.root.getData().equals("Map") && testTree.cursor.getData().equals("Map")&& testTree.size() == 1);
    } catch (Exception e) {
         e.printStackTrace();
    }
     System.out.println("Testing BinaryTree Cursor...");
     try {
         BinaryTree tree = new BinaryTree("Dogs");
        tree.attachLeftSonAtCursor("Maps");
        displaySuccessIfTrue(tree.root.lson.getData().equals("Maps") && tree.size() == 2);
     } catch (Exception e) {
       e.printStackTrace();
    }
     try {
         BinaryTree tree = new BinaryTree("Dogs");
        tree.attachRightSonAtCursor("Maps");
        displaySuccessIfTrue(tree.root.rson.getData().equals("Maps") && tree.size() == 2);
     } catch (Exception e) {
       e.printStackTrace();
    }
  try {
      BinaryTree tree = new BinaryTree("Dogs");
        tree.attachLeftSonAtCursor("Maps");
      tree.putCursorAtLeftSon();
       displaySuccessIfTrue(tree.cursor.getData().equals("Maps"));
    } catch (Exception e) {
       e.printStackTrace();
    }
  try {
      BinaryTree tree = new BinaryTree("Dogs");
      tree.attachRightSonAtCursor("Maps");
      tree.putCursorAtRightSon();
       displaySuccessIfTrue(tree.cursor.getData().equals("Maps"));
    } catch (Exception e) {
       e.printStackTrace();
    }
     try {
      BinaryTree tree = new BinaryTree("Dogs");
       tree.attachRightSonAtCursor("Pat");
       tree.putCursorAtRightSon();
       tree.attachRightSonAtCursor("Money");
        tree.putCursorAtRightSon();
        displaySuccessIfTrue(tree.cursor.getData().equals("Money"));
        } catch (Exception e) {
       e.printStackTrace();
   }
     try {
      BinaryTree tree = new BinaryTree("Dogs");
       tree.attachLeftSonAtCursor("Pat");
       tree.putCursorAtLeftSon();
       tree.attachLeftSonAtCursor("Money");
        tree.putCursorAtLeftSon();
        displaySuccessIfTrue(tree.cursor.getData().equals("Money"));
        } catch (Exception e) {
       e.printStackTrace();
   }
    try {
         BinaryTree tree = new BinaryTree("Zebras");
        tree.attachLeftSonAtCursor("Toast");
        tree.putCursorAtLeftSon();
        tree.attachLeftSonAtCursor("Money");
        tree.putCursorAtLeftSon();
        tree.putCursorAtFather();
        displaySuccessIfTrue(tree.cursor.getData().equals("Toast"));
    } catch (Exception e) {
       e.printStackTrace();
    }
    try {
        BinaryTree tree = new BinaryTree("Cats");
        tree.attachLeftSonAtCursor("are");
        tree.putCursorAtLeftSon();
        tree.attachLeftSonAtCursor("sick");
        tree.putCursorAtLeftSon();
        displaySuccessIfTrue(tree.cursor.dad.getData().equals("are"));
     } catch (Exception e) {
        e.printStackTrace();
        System.out.println(successes + "/" + attempts + " tests passed.");
     }
     System.out.println("Testing BinaryTree PreOrderIterator...");
        try {
            BinaryTree tree = new BinaryTree(50L);
            tree.attachLeftSonAtCursor(25L);
            tree.attachRightSonAtCursor(75L);
            tree.putCursorAtLeftSon();
            tree.attachLeftSonAtCursor(1L);
            tree.attachRightSonAtCursor(12L);
            tree.putCursorAtRoot();
            tree.putCursorAtRightSon();
            tree.attachLeftSonAtCursor(62L);
            tree.attachRightSonAtCursor(100L);
            Iterator test = tree.iterator();
            Long[] number = {50L, 25L, 1L, 12L, 75L, 62L, 100L};
            for (int i = 0; i < tree.size(); i++) {
                displaySuccessIfTrue(test.next().equals(number[i]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    System.out.println("Testing yung prune..");
    try {
        BinaryTree tree = new BinaryTree(50L);
        tree.attachLeftSonAtCursor(25L);
        tree.attachRightSonAtCursor(75L);
        tree.putCursorAtLeftSon();
        tree.attachLeftSonAtCursor(1L);
        tree.attachRightSonAtCursor(12L);
        tree.putCursorAtRoot();
        tree.putCursorAtRightSon();
        tree.attachLeftSonAtCursor(62L);
        tree.attachRightSonAtCursor(100L);
        tree.putCursorAtRoot();
        tree.putCursorAtLeftSon();
        tree.pruneFromCursor();
        Iterator test = tree.iterator();
        Long[] number = {50L, 75L, 62L, 100L};
        for (int i = 0; i < tree.size(); i++) {
            displaySuccessIfTrue(test.next().equals(number[i]));
       }
    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println("Testing InOrder");
    try {
        BinaryTree tree = new BinaryTree(50L);
        tree.attachLeftSonAtCursor(25L);
        tree.attachRightSonAtCursor(75L);
        tree.putCursorAtLeftSon();
        tree.attachLeftSonAtCursor(1L);
        tree.attachRightSonAtCursor(12L);
        tree.putCursorAtRoot();
        tree.putCursorAtRightSon();
        tree.attachLeftSonAtCursor(62L);
        tree.attachRightSonAtCursor(100L);
        Iterator test = tree.inOrder();
        Long[] number = {1L, 25L, 12L, 50L, 62L, 75L, 100L};
        for (int i = 0; i < tree.size(); i++) { 
            displaySuccessIfTrue(test.next().equals(number[i]));
            }   
      } catch (Exception e) {
       e.printStackTrace();
    }
            
     }
        class Node {
            Object data = null;
            Node lson = null;
            Node rson = null;
            Node dad = null;
        public Node(Object data) {
            this.data = data;
        }
        public Node(Object data,Node lson,Node rson,Node dad) {
            this.dad = dad;
            this.rson = rson;
            this.lson = lson;
            this.data = data;
        }
        public void setLeft(Node n) {
            n = lson;
        }
        public void setRight(Node n) {
            n = rson;
        }
        public void setDad(Node n) {
            n = dad;
        }
        public Node getlSon() {
            return lson;
        }
        public Node getrSon() {
            return rson;
        }
        public Node getDad() {
            return dad;
        }
        public Object getData() {
            return data;
        }
     }
}
