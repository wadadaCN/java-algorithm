package xiekun.me

/**
* @version 1.00 2018/6/1
* @author xiekun
*/

public class find{
	public static void main(String[] args){
		//Todo
		int[] aList = {1, 2, 4, 5};
		int v = 5;
		dichFind(aList, 0, aList.length - 1, v);
		linearFind(aList, v);
	}
	
	public static void dichFind(int[] sorted, int h, int f, int v){
		if (h <= f){
			int mid = Math.floorDiv((h + f), 2);
			if (sorted[mid] == v) System.out.println(mid);
			else if (sorted[mid] > v) dichFind(sorted, h, mid - 1, v);
			else dichFind(sorted, mid + 1, f, v);
		}
		else System.out.println(-1);
	}

	public static void linearFind(int[] A, int v){
		for (int i = 0;i < A.length + 1;i++){
			if (i == A.length){
				System.out.println(-1);
				break;
			}
			if (A[i] == v){
				System.out.println(i);
				break;
			}
		}
	}
}
