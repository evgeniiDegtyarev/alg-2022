package bynarytree;
 
public class BynaryTree {
    Node root = null;
    public String toString(){
        return root + " ";
    }
    public int height(){
        return height(root);
    }
    public int height(Node node){
        if(node == null){
            return 0;
        }
        else{
            return Math.max(height(node.left), height(node.right)) + 1;
        }
    }
    public static void main(String[] args) {
        BynaryTree Tree = new BynaryTree();
        Tree.root = new Node(3);
        Tree.root.addLeft(2).addLeft(1);
        Tree.root.addRight(5).addRight(6);
        Tree.root.addLeft(2).addRight(8);
        Tree.root.addRight(5).addLeft(4);
        System.out.println(Tree.toString());
        System.out.print(Tree.height());
    }
}
 
 
 
 
 
package bynarytree;
 
public class Node {
    public Object value;
    public Node left;
    public Node right;
    public Node(Object val){
        value = val;
    }
    public Node(Object val, Node L, Node R){
        value = val;
        left = L;
        right = R;
    }
    public Node addRight(Object val){
        Node node = new Node(val);
        right = node;
        return node;
    }
    public Node addLeft(Object val){
        Node node = new Node(val);
        left = node;
        return node;
    }
    public String toString(){
        String s = value + " ";
        s += "(" + left + ", " + right + ")";
        return s;
    }
    public int height(){
        int hL = (left == null) ? 0: left.height();
        int hR = (right == null) ? 0:  right.hashCode();
        return Math.max(hR,hL) + 1;
    }
}