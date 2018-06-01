/**
* @version 1.00 2018/6/1
* @author xiekun
*/

public class sum2{
	public static void main(String[] args){
		//Todo
		int[] aList = {1, 3, 4, 5};
		System.out.println(sum2(aList, 6));
	}
	
	public static int linearFind(int[] A, int v){
		for (int i = 0;i < A.length + 1;i++){
			if (i == A.length){
				return -1;
			}
			if (A[i] == v){
				return i;
			}
		}
		return -1;
	}
	
	public static boolean sum2(int[] unSorted, int x){
		for (int i = 0;i < unSorted.length;i++){
			int left = x - unSorted[i];
			if (linearFind(unSorted, left) >= 0) 
				return true;
			else 
				return false;
		}
		return false;
	}
}
