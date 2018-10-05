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
		
		
		int[] p = {30, 35, 15, 5, 10, 20, 25};
		int[][] s = matrixChainOrder(p);
		printOptimalParens(s, 1, 6);
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
	
	public static int[][] matrixChainOrder(int[] p){
		int n = p.length - 1;
		int[][] m = new int[p.length][p.length];
		int[][] s = new int[p.length][p.length];
		for (int i = 1;i <= n;i++){
			m[i][i] = 0;
		}
		for (int l = 2;l <= n;l++){
			for (int i = 1;i <= n - l + 1;i++){
				int j = i + l - 1;
				m[i][j] = 2147483647;
				for (int k = i;k <= j - 1;k++){
					int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
					if (q < m[i][j]){
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
		return s;
	}
	//根据输出的乘法结合律方案可以构造矩阵乘法顺序
	public static void printOptimalParens(int[][] s, int i, int j){
		if (i == j) System.out.print("A" + i);
		else{
			System.out.print("(");
			printOptimalParens(s, i, s[i][j]);
			printOptimalParens(s, s[i][j] + 1, j);
			System.out.print(")");
		}
	}
	//untest
	public static int[][] matrixMultiply(int[][] A, int[][] B){
		if (A[0].length != B.length) new Exception("incompatible dimentions");
		else{
			int[][] C = new int[A.length][B[0].length];
			for (int i = 0;i < A.length;i++){
				for (int j = 0;j < B[0].length;j++){
					for (int k = 0;k < A[0].length;k++){
						c[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
		return C;
	}
}

















