/**
* @version 1.00 2018.8.22
* @author xiekun
*/

public class radixSort{
	public static void main(String[] args){
		//int[] unSorted = {2,5,3,0,2,3,0,3};
		//int[] sorted = new int[unSorted.length];
		//countSort(unSorted, sorted);
		//for (int item:sorted) System.out.println(item);
		int[] unSorted = {329, 457, 657, 3, 839, 436, 720, 355, 53};
		int[] sorted = radixSort(unSorted);
		for (int item:sorted) System.out.println(item);
	}
	
	private static void countSort(int[] unSorted, int[] sorted){
		int max = 0;
		for (int i = 0;i < unSorted.length;i++){
			if (max < unSorted[i]) max = unSorted[i];
		}
		int[] temp = new int[max + 1];
		for (int j = 0;j < temp.length;j++){
			temp[j] = 0;
		}
		for (int i = 0;i < unSorted.length;i++){
			temp[unSorted[i]] += 1;
		}
		for (int j = 1;j < temp.length;j++){
			temp[j] += temp[j - 1];
		}
		for (int i = unSorted.length - 1;i >= 0;i--){
			sorted[temp[unSorted[i]] - 1] = unSorted[i];
			temp[unSorted[i]] -= 1;
		}
	}
	
	private static int[] radixSort(int[] unSorted){
		int max = 0;
		for (int i = 0;i < unSorted.length;i++){
			if (max < unSorted[i]) max = unSorted[i];
		}
		int exp = 10;
		int[] partSorted = new int[unSorted.length];
		int[] sorted = new int[unSorted.length];
		while (max / exp >= 0){
			for (int i = 0;i < unSorted.length;i++){
				partSorted[i] = (unSorted[i] % exp) / (exp/10);
			}
			countSort(unSorted, partSorted, sorted);
			exp *= 10;
			if (max * 10 / exp == 0) break;
		}
		return sorted;
	}
	
	private static void countSort(int[] unSorted, int[] partSorted, int[] sorted){
		int max = 0;
		for (int i = 0;i < partSorted.length;i++){
			if (max < partSorted[i]) max = partSorted[i];
		}
		int[] temp = new int[max + 1];
		for (int j = 0;j < temp.length;j++){
			temp[j] = 0;
		}
		for (int i = 0;i < partSorted.length;i++){
			temp[partSorted[i]] += 1;
		}
		for (int j = 1;j < temp.length;j++){
			temp[j] += temp[j - 1];
		}
		for (int i = partSorted.length - 1;i >= 0;i--){
			sorted[temp[partSorted[i]] - 1] = unSorted[i];
			temp[partSorted[i]] -= 1;
		}
		for (int i = 0;i < unSorted.length;i++) unSorted[i] = sorted[i];
	}
}
