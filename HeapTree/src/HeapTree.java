public class HeapTree {
    private int[] heap;
    private int rear;
    private int capacity;

    public HeapTree(int capacity){
        this.capacity = capacity;
        rear = -1;
        heap = new int[capacity];
    }

    public int size(){
        return rear + 1;
    }

    public int getParent(int index){
        return (index - 1) / 2;
    }

    public void swapItems(int first, int second){
        int temp;
        temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;
    }

    public void insert(int item){
        if (size() >= capacity)
            return;
       heap[++rear] = item;
       int currentIndex = rear;
       int parentIndex = getParent(currentIndex);
       while (heap[currentIndex] < heap[parentIndex]){
           swapItems(currentIndex, parentIndex);
           currentIndex = parentIndex;
           parentIndex = getParent(currentIndex);
       }
    }

    public boolean isLeaf(int index){
        return (index >= size() / 2 && index <= size());
    }

    public int getLeftChild(int index){
        return ((2 * index) + 1);
    }

    public int getRightChild(int index){
        return getLeftChild(index) + 1;
        //return 2 * index + 2;
    }


    private void heapify(int index){
        if (!isLeaf(index)){
            if (heap[index] > heap[getLeftChild(index)] || heap[index] > heap[getRightChild(index)]){
                if (heap[getLeftChild(index)] < heap[getRightChild(index)]){
                    swapItems(index, getLeftChild(index));
                    heapify(getLeftChild(index));
                }else{
                    swapItems(index, getRightChild(index));
                    heapify(getRightChild(index));
                }
            }
        }
    }

    public int remove(){
        int hand = heap[0];
        heap[0] = heap[rear--];
        heapify(0);
        return hand;
    }

    public void printHeap(){

        for (int i = 0; i < size() / 2; i++){
            int left = getLeftChild(i);
            int right = getRightChild(i);
            System.out.print("parent: " + heap[i] + " ");
            if (left < size())
                System.out.print("left child: " +  heap[left] + " ");
            if (right < size())
                System.out.print("right child: " + heap[right] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args){
        HeapTree heap = new HeapTree(10);
        heap.insert(12);
        heap.insert(24);
        heap.insert(3);
        heap.insert(67);
        heap.insert(89);
        heap.insert(90);
        heap.insert(7);

        heap.printHeap();
        heap.remove();
        System.out.println("--------------------------------------------------");
        heap.printHeap();



    }
}
