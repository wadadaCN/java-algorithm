/*
* @version 1.00 2018.7.1
* @author xiekun
*/

public class quickSort{
    public static void main(String[] args){
        int[] A = new int[]{2, 8, 7, 1, 3, 5, 6, 4};
        quickSort(A, 0, 7);
        for (int i:A) System.out.println(i);
    }
    
    public static void quickSort(int[] A, int p, int r){
        if (p < r){
            int q = partition(A, p, r); //执行一趟partition就确定了一个数的最终位置，因此递归时不再考虑该数
            quickSort(A, p, q - 1);
            quickSort(A, q + 1, r);
        }
    }
    
    private static int partition(int[] A, int p, int r){
        int x = A[r];
        int i = p - 1;
        for (int j = p;j < r;j++){
            if (A[j] <= x){
                i++;
                int temp = A[j];
                A[j] = A[i];
                A[i] = temp;
            }
        }
        A[r] = A[i + 1];
        A[i + 1] = x;
        return i + 1;
    }
}
