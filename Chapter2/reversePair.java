/**
* @version 1.00 2018/6/2
* @author xiekun
*/

public class reversePair{
	public static void main(String[] args){
		//Todo
		int[] aList = {3, 2, 4, 5, 1};
		int[] bList = new int[aList.length + 1];
		for (int i = 0;i < aList.length;i++){
			bList[i] = aList[i];
		}
		bList[aList.length] = 0;
		count(bList, 0, aList.length - 1);
		System.out.println(bList[aList.length]);
	}
	
	public static void countPair(int[] A, int h, int m, int f){
		int n1 = m - h + 1;
		int n2 = f - m;
		int[] L = new int[n1 + 1];
		int[] R = new int[n2 + 1];
		for (int i = 0;i < n1 + 1;i++){
			if (i != n1) L[i] = A[h + i];
			else L[i] = 2147483647;
		}
		for (int j = 0;j < n2 + 1;j++){
			if (j != n2) R[j] = A[m + j + 1];
			else R[j] = 2147483647;
		}
		int i = 0;
		int j = 0;
		int count = 0;
		for (int k = h;k <= f;k++){
			if (L[i] <= R[j]){
				A[k] = L[i];
				i++;
			}
			else{
				A[k] = R[j];
				j++;
				if (L[i] != 2147483647) count = count + n1 - i;
			}
		}
		A[A.length - 1] = A[A.length - 1] + count;
	}
	
	public static void count(int[] A, int h, int f){
		if (h < f){
			int mid = Math.floorDiv((h + f), 2);
			count(A, h, mid);
			count(A, mid + 1, f);
			countPair(A, h, mid, f);
		}
	}
}
