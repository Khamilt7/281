public class LinkedDeque {      
    Node firstnode;
    Node lastnode;
    int size = 0;
    public LinkedDeque () {
        this.firstnode = null;
        this.lastnode = this.firstnode;
    }   
    public void insertLeft ( Object o ) {
        if(size == 0) {
            firstnode = new Node(o);
            lastnode = firstnode;
        } else {
            firstnode = new Node(o,  null, firstnode);
            firstnode.right.left = firstnode;
        }
        size++;
    }
    
    public void insertRight ( Object o ) {
        if(size == 0) {
            lastnode = new Node(o);
            firstnode = lastnode;
        } else {
            lastnode = new Node(o, lastnode, null);
            lastnode.left.right = lastnode;
        }       
        size++;
    }
    
    public void deleteLeft () {
        if(size == 0) {
            throw new IllegalArgumentException();
        }
        firstnode = firstnode.right;
        firstnode.setLeft(null);
        size--;
    }
    
    public void deleteRight () {
        if(size == 0) {
            throw new IllegalArgumentException();
        } else {
            lastnode = lastnode.left;
            lastnode.setRight(null); 
                    size--;
        }
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
    
    private static int attempts = 0;
    private static int successes = 0;
	//method to assist in testing carried out in "main"
	private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    public static void main ( String[] args ) {
        LinkedDeque emptyDeck = new LinkedDeque();
        LinkedDeque testDeque = new LinkedDeque();
        testDeque.insertLeft("dog");
        testDeque.insertLeft("cat");
        testDeque.insertRight("dogcat");
        testDeque.insertRight("catdog");
        System.out.println("Testing Constructor");
        try {
            displaySuccessIfTrue(emptyDeck.size == 0);         
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(testDeque.size == 4);         
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }        
        System.out.println("Testing insertLeft");
        try {
            displaySuccessIfTrue(testDeque.left() == "cat");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        } 
        LinkedDeque threeDeck = new LinkedDeque();
        threeDeck.insertLeft("cat");
        threeDeck.insertRight("dogcat");
        threeDeck.insertRight("catdog");
        threeDeck.insertRight(17);
        try {
            displaySuccessIfTrue(threeDeck.left() == "cat");         
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }       
        LinkedDeque fourDeck = new LinkedDeque();
        LinkedDeque fiveDeck = new LinkedDeque();
        fourDeck.insertLeft("cat");
        fourDeck.insertLeft("dog");
        fourDeck.insertLeft("lizard");
        fourDeck.insertLeft("snake");
        fourDeck.insertLeft("giraffe");
        fiveDeck.insertRight("donkey");
        try {
            displaySuccessIfTrue(fourDeck.left() == "giraffe");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }  
        try {
            displaySuccessIfTrue(fourDeck.toString().equals("[giraffe][snake][lizard][dog][cat]"));  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }           
        System.out.println("Testing insertRight");
        try {
            displaySuccessIfTrue(fiveDeck.right() == "donkey");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }        
        LinkedDeque sixDeck = new LinkedDeque();
        sixDeck.insertRight("first");
        sixDeck.insertRight("middle");
        sixDeck.insertRight("last");
        try {
            displaySuccessIfTrue(sixDeck.right() == "last");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        } 
        try {
            displaySuccessIfTrue(sixDeck.toString().equals("[first][middle][last]")); 
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }      
        System.out.println("Testing deleteLeft");
        testDeque.deleteLeft();
        try {
            displaySuccessIfTrue(testDeque.left() == "dog");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }     
        fourDeck.deleteLeft();
        try {
            displaySuccessIfTrue(fourDeck.left() == "snake");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }  
        try {
            displaySuccessIfTrue(fourDeck.toString().equals("[snake][lizard][dog][cat]"));
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        } 
        fourDeck.deleteLeft();
        try {
            displaySuccessIfTrue(fourDeck.left() == "lizard");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        } 
        try {
            emptyDeck.deleteLeft();
            displaySuccessIfTrue(false);  
        } catch (Exception e) {
            displaySuccessIfTrue(true);
        }          
        System.out.println("Testing deleteRight");
        threeDeck.deleteRight();
        try {
            displaySuccessIfTrue(threeDeck.right() == "catdog");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }         
        threeDeck.deleteRight();
        try {
            displaySuccessIfTrue(threeDeck.right() == "dogcat");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            emptyDeck.deleteRight();
            displaySuccessIfTrue(false);  
        } catch (Exception e) {
            displaySuccessIfTrue(true);
        }    
        System.out.println("Testing left");
        LinkedDeque leftDeck = new LinkedDeque();
        leftDeck.insertLeft("cat");
        leftDeck.insertLeft("kitten"); 
        leftDeck.insertLeft("mothercat");        
        try {
            displaySuccessIfTrue(leftDeck.left() == "mothercat");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        } 
        try {
            displaySuccessIfTrue(testDeque.left() == "dog");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        } 
        System.out.println("Testing Right");
        LinkedDeque rightDeck = new LinkedDeque();
        rightDeck.insertRight("cat");
        rightDeck.insertRight("kitten");
        rightDeck.insertRight("mothercat");
        try {
            displaySuccessIfTrue(rightDeck.right() == "mothercat");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }   
        try {
            displaySuccessIfTrue(threeDeck.right() == "dogcat");  
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }            
        System.out.println("Testing Size");
        try {
            displaySuccessIfTrue(emptyDeck.size == 0);         
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
        try {
            displaySuccessIfTrue(testDeque.size == 3);         
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }        
        try {
            displaySuccessIfTrue(leftDeck.size == 3);         
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }       
        try {
            displaySuccessIfTrue(rightDeck.size == 3);         
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }         
        System.out.println("Testing toString");
        try {
            displaySuccessIfTrue(rightDeck.toString().equals("[cat][kitten][mothercat]")); 
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }  
        try {
            displaySuccessIfTrue(leftDeck.toString().equals("[mothercat][kitten][cat]")); 
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }        
            System.out.println(successes + "/" + attempts + " tests passed.");
    }
    class Node {
        Object information;
        Node left = null;
        Node right = null;      
    
    public Node(Object information) {
        this.information = information;
    }
    public Node(Object information,Node left,Node right) {
        this.left = left;
        this.right = right;
        this.information = information;
    } 
    public void setLeft(Node leftnode) {
       this.left = leftnode;
    }
    public void setRight(Node rightnode) {
        this.right = rightnode;
    }
    public String toString(String str) {
        str += "[" + information + "]";
        return (right == null? str:right.toString(str));
    }
 }
}
