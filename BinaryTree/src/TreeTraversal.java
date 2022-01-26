
public class TreeTraversal<E>{
    private static class Node<E> {
        E   data;
        Node<E> left;
        Node<E> right;

        public Node(E item){
            data = item;
            left = null;
            right = null;
        }
    }

    private Node<E> root = null;
    private boolean addReturn = false;
    private E deleteReturn = null;
    private int rightHeight = 0;
    private int leftHeight = 0;


    private void printGreaterThanInorder(Node<E> node, E target){
        if (node == null)
            return;
        printGreaterThanInorder(node.left, target);
        if ((Integer)node.data >= (Integer) target)
            System.out.print(node.data + " ");
        printGreaterThanInorder(node.right, target);
    }

    public void printGreaterThanInorder(E target){
        printGreaterThanInorder(root, target);
    }

    public Node<E> add(Node<E> localRoot, E item){
        if (localRoot == null){
            addReturn = true;
            return new Node<>(item);
        }else {
            int comp = ((Integer)item).compareTo((Integer) localRoot.data);
            if (comp == 0){
                addReturn = false;
                return localRoot;
            }else if (comp < 0){
                localRoot.left = add(localRoot.left, item);
                return localRoot;
            }else {
                localRoot.right = add(localRoot.right, item);
                return localRoot;
            }
        }
    }

    public boolean add(E item){
        root = add(root, item);
        return addReturn;
    }

    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {
            deleteReturn = null;
            return localRoot;
        }

        int compResult = ((Integer)item).compareTo((Integer) localRoot.data);
        if (compResult < 0) {
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (compResult > 0) {
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                return localRoot.right;
            } else if (localRoot.right == null) {
                return localRoot.left;
            } else {
                if (localRoot.left.right == null) {
                    localRoot.data = localRoot.left.data;
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    localRoot.data = findPredessor(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    private E findPredessor(Node<E> parent) {
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findPredessor(parent.right);
        }
    }

    public void printInorder(){
        printInorder(root);
    }

    public void printInorder(Node <E> node){
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    public int countGreaterThan(E target){
        return countGreaterThan(root, target);
    }

    public int countGreaterThan(Node<E> localRoot, E target){
        if (localRoot == null)
            return 0;
        int comp = ((Integer)target).compareTo((Integer)localRoot.data);
        if (comp >= 0)
            return 0 + countGreaterThan(localRoot.right, target);
        else
            return 1 + countGreaterThan(localRoot.left, target) + countGreaterThan(localRoot.right, target);
    }


    public static void main(String[] args){
        TreeTraversal tree = new TreeTraversal();


        tree.add(56);
        tree.add(46);
        tree.add(36);
        tree.add(66);
        /*tree.add(50);
        tree.add(32);
        tree.add(60);
        tree.add(70);
        tree.add(55);
        tree.add(59);
        tree.add(72);
        tree.add(69);*/

        tree.printInorder();
        System.out.println();
        System.out.println("count greater than 41 is: " + tree.countGreaterThan(41));
        System.out.print("the numbers which greater than 41: ");
        tree.printGreaterThanInorder(41);
        System.out.println();
        tree.delete(20);
        tree.printInorder();
    }
}
