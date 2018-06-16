/**
* @version 1.00 2018.6.16
* @author xiekun
*/

public class heapSort{
    public static void main(String[] args){
        //Todo
        //int[] A = {16, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        //maxHeapifyWithWhile(A, 1);
        int[] A = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        //buildMaxHeap(A);
        heapSort(A);
        for (int i = 0;i < A.length;i++) System.out.println(A[i]);
    }
    
    private static int parent(int i){
        return Math.floorDiv(i, 2);
    }
    
    private static int left(int i){
        return i * 2;
    }
    
    private static int right(int i){
        return i * 2 + 1;
    }
    
    private static void maxHeapify(int[] A, int i, int heapSize){
        int l = left(i + 1);
        int r = right(i + 1);
        int largest = i;
        if (l <= heapSize && A[l - 1] > A[i]) largest = l - 1;
        if (r <= heapSize && A[r - 1] > A[largest]) largest = r - 1;
        if (largest != i){
            int temp = A[largest];
            A[largest] = A[i];
            A[i] = temp;
            maxHeapify(A, largest, heapSize);
        }
    }
    
    private static void maxHeapifyWithWhile(int[] A, int i, int heapSize){
        while (i <= heapSize/2 -1){
            int l = left(i + 1);
            int r = right(i + 1);
            int largest = i;
            if (A[l - 1] > A[i]) largest = l - 1;
            if (r <= heapSize && A[r - 1] > A[largest]) largest = r - 1;
            if (largest != i){
                int temp = A[largest];
                A[largest] = A[i];
                A[i] = temp;
                i = largest;
            }
            else break;
        }
    }
    
    private static void buildMaxHeap(int[] A){
        int heapSize = A.length;
        for (int i = Math.floorDiv(A.length, 2);i >= 1;i--){
            maxHeapify(A, i - 1, heapSize);
        }
    }
    
    public static void heapSort(int[] A){
        buildMaxHeap(A);
        int heapSize = A.length;
        for (int i = A.length;i > 1;i--){
            int temp = A[i - 1];
            A[i - 1] = A[0];
            A[0] = temp;
            heapSize--;
            maxHeapify(A, 0, heapSize);
        }
    }
}
















