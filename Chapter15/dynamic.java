/** 
* @version 1.00 2018.10.5
* @author xiekun
*/

public class dynamic{
	public static void main(String[] args){
		int[] price = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30, 31, 35, 37, 40, 56, 60};
		int[] result = new int[price.length];
		for (int i = 1;i < price.length;i++){
			System.out.print(i + "	");
		}
		System.out.println();
		long startTime = System.nanoTime();
		for (int i = 1;i < price.length;i++){
			System.out.print(cutRod(price, i) + "	");
		}
		long endTime = System.nanoTime();
		System.out.println();
		System.out.println("非动态规划运行时间：" + (endTime - startTime) + "ns");
		startTime = System.nanoTime();
		System.out.println(bottomUpCutRod(price, price.length - 1));
		endTime = System.nanoTime();
		System.out.println("动态规划运行时间：" + (endTime - startTime) + "ns");
	}
	
	public static int cutRod(int[] price, int n){
		if (n == 0) return 0;
		int q = -2147483648;
		for (int i = 1;i <= n;i++){
			q = Math.max(q, price[i] + cutRod(price, n - i));
		}
		return q;
	}
	
	public static int bottomUpCutRod(int[] price, int n){
		int[] result = new int[n + 1];
		int[] slice = new int[n + 1];
		for (int j = 1;j <= n;j++){
			int q = -2147483648;
			for (int i = 1;i <= j;i++){
				if (q < price[i] + result[j - i]){
					q = price[i] + result[j - i];
					slice[j] = i;
				}
			}
			result[j] = q;
		}
		for (int i = 1;i < slice.length;i++){
			System.out.print(slice[i] + "	");
		}
		System.out.println();
		return result[n];
	}
}
