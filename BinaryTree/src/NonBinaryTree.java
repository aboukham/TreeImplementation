import java.util.ArrayList;
import java.util.List;

public class NonBinaryTree<E> {
    public static class Node<E>{
        private E data;
        private List<Node<E>> children;
        private Node<E> parent;

        public Node(E data){
            this.data = data;
            this.children = new ArrayList<>();
            parent = null;
        }
    }

    Node<E> root = null;
    private int size = 0;
    //private boolean addReturn = false;

    public void addFirst(Node<E> node){
        if (size == 0) {
            root = node;
            size++;
        }
    }

    public void addChild(Node<E> node, Node<E> newNode){
        node.children.add(newNode);
       size++;
    }

    public static void main(String[] args){
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");
        Node<String> nodeF = new Node<>("F");

        NonBinaryTree tree = new NonBinaryTree();

        tree.addFirst(nodeA);
        tree.addChild(tree.root, nodeB);
        tree.addChild(tree.root, nodeC);
        tree.addChild(nodeB, nodeD);
        tree.addChild(nodeB, nodeE);
        tree.addChild(nodeD, nodeF);

        System.out.println(tree.root.children.get(0));
    }
}
