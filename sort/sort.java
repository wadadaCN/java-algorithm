/**
* @version 1.00 2018/5/31
* @author xiekun
*/

public class sort{
	public static void main(String[] args){
		int[] aList = {5,2,1,4,3};
		//InsertionSort(aList);
		//SelectionSort(aList);
		MergeSort(aList, 0, 4);
		for(int num:aList) System.out.print(num);
	}
	
	public static void InsertionSort(int[] unSorted){
		for (int j = 1;j < unSorted.length;j++){
			int key = unSorted[j];
			int i = j - 1;
			while (i >= 0 && unSorted[i] > key){
				unSorted[i + 1] = unSorted[i];
				i--;
			}
			unSorted[i + 1] = key;
		}
	}
	
	public static void SelectionSort(int[] unSorted){
		for (int i = 0;i < unSorted.length - 1;i++){
			int min = unSorted[i];
			for (int j = i + 1;j < unSorted.length;j++){
				if (min > unSorted[j]){
					unSorted[i] = unSorted[j];
					min = unSorted[j];
					unSorted[j] = min;
				}
			}
		}
	}
	
	private static void Merge(int[] unSorted, int h, int m, int f){
		int n1 = m - h + 1;
		int n2 = f - m;
		int[] L = new int[n1];
		int[] R = new int[n2];
		for (int i = 0;i < n1;i++){
			L[i] = unSorted[h + i];
		}
		for (int j = 0;j < n2;j++){
			R[j] = unSorted[m + j + 1];
		}
		int i = 0;
		int j = 0;
		for (int k = h;k < f + 1;k++){
			if (i < n1 && L[i] <= R[j] && j != n2){
				unSorted[k] = L[i];
				i++;
			}
			else if (j < n2){
				unSorted[k] = R[j];
				j++;
			}
			else{
				unSorted[k] = L[i];
				i++;
			}
		}
	}
	
	public static void MergeSort(int[] unSorted, int h, int f){
		if (h < f){
			int m = Math.floorDiv((h + f),2);
			MergeSort(unSorted, h, m);
			MergeSort(unSorted, m + 1, f);
			Merge(unSorted, h, m, f);
		}
	}
}

































