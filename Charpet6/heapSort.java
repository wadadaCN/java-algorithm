/**
* @version 2.00 2018.6.24
* @author xiekun
*/

public class heapSort{
    public static void main(String[] args){
        int[] B = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        int[] A = new int[B.length + 1];
        A[0] = B.length;
        for (int i = 0;i < B.length;i++) A[i + 1] = B[i];
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
    
    private static void maxHeapify(int[] A, int i){
        int l = left(i);
        int r = right(i);
        int largest = i;
        int heapSize = A[0];
        if (l <= heapSize && A[l] > A[i]) largest = l;
        if (r <= heapSize && A[r] > A[largest]) largest = r;
        if (largest != i){
            int temp = A[largest];
            A[largest] = A[i];
            A[i] = temp;
            maxHeapify(A, largest);
        }
    }
    
    private static void maxHeapifyWithWhile(int[] A, int i){
        int heapSize = A[0];
        while (i <= heapSize/2){
            int l = left(i);
            int r = right(i);
            int largest = i;
            if (A[l] > A[i]) largest = l;
            if (r <= heapSize && A[r] > A[largest]) largest = r;
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
        for (int i = Math.floorDiv(A.length, 2);i >= 1;i--){
            maxHeapifyWithWhile(A, i);
        }
    }
    
    public static void heapSort(int[] A){
        buildMaxHeap(A);
        int heapSize = A[0];
        for (int i = heapSize;i > 1;i--){
            int temp = A[i];
            A[i] = A[1];
            A[1] = temp;
            A[0]--;
            maxHeapify(A, 1);
        }
    }
















