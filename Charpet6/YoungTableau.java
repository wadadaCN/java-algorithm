/**
* @version 1.0 2018.6.24
* @author xiekun
*/

public class YoungTableau{
    public static void main(String[] args){
        //Todo
        int[] nums = new int[]{9, 16, 3, 2, 4, 8, 5, 14, 12};
        int[][] A = buildYoungTableau(nums, 4, 4);
        for (int i:nums){
            int[] flags = YoungFind(A, i);
            System.out.print(flags[0]);
            System.out.print(flags[1]);
            System.out.println();
        }
        //int min = ExtractMin(A);
        //System.out.println(min);
        //for (int i = 0;i <= 4;i++){
        //    for (int j = 0;j <= 4;j++){
        //    	System.out.print(A[i][j]);
        //        System.out.print(' ');
        //    }
        //    System.out.println();
        //}
        //int[] result = YoungSort(nums);
        //for (int i = 0;i < result.length;i++){
        //    System.out.println(result[i]);
        //}
    }
    
    private static int upParent(int i, int j){
        return i - 1;
    }
    
    private static int leftParent(int i, int j){
        return j - 1;
    }
    
    private static int downChild(int i, int j){
        return i + 1;
    }
    
    private static int rightChild(int i, int j){
        return j + 1;
    }
    
    private static void minYoungTableau(int[][] A, int i, int j){
        int downChildi = downChild(i, j);
        int downChildj = j;
        int rightChildi = i;
        int rightChildj = rightChild(i, j);
        int smallesti = i;
        int smallestj = j;
        if (rightChildj <= A[i][0] && A[i][rightChildj] < A[i][j]){
            smallesti = i;
            smallestj = rightChildj;
        }
        if (downChildi <= A[0][j] && A[downChildi][j] < A[smallesti][smallestj]){
            smallesti = downChildi;
            smallestj = j;
        }
        if (smallesti != i || smallestj != j){
            int temp = A[smallesti][smallestj];
            A[smallesti][smallestj] = A[i][j];
            A[i][j] = temp;
            minYoungTableau(A, smallesti, smallestj);
        }
    }
    
    private static void delminYoungTableau(int[][] A, int i, int j){
        int downChildi = downChild(i, j);
        int downChildj = j;
        int rightChildi = i;
        int rightChildj = rightChild(i, j);
        int smallesti = i;
        int smallestj = j;
        if (rightChildj <= A[i][0] && A[i][rightChildj] < A[i][j]){
            smallesti = i;
            smallestj = rightChildj;
        }
        if (downChildi <= A[0][j] && A[downChildi][j] < A[smallesti][smallestj]){
            smallesti = downChildi;
            smallestj = j;
        }
        A[i][0]--;
        A[0][j]--;
        if (smallesti != i || smallestj != j){
            int temp = A[smallesti][smallestj];
            A[smallesti][smallestj] = A[i][j];
            A[i][j] = temp;
            A[i][0]++;
            A[0][j]++;
            delminYoungTableau(A, smallesti, smallestj);
        }
    }
    
    //Young矩阵构造
    private static int[][] buildYoungTableau(int[] nums, int m, int n){
        int[][] A = new int[m + 1][n + 1];
        A[0][0] = nums.length;
        if (nums.length > m * n) System.out.println("创建错误");
        else{
            for (int i = 1;i <= m;i++){
                for (int j = 1;j <= n;j++){
                    int k = (i - 1) * n + j - 1;
                    if (k < nums.length) A[i][j] = nums[k];
                    else A[i][j] = 2147483647;
                }
            }
            int line = Math.floorDiv(nums.length, n);
            int row = nums.length - n * line;
            int i = 1;
            while (i <= m){
                if (i <= line) A[i][0] = n;
                else if (i == line + 1) A[i][0] = row;
                else A[i][0] = 0;
                i++;
            }
            i = 1;
            while (i <= n){
                if (i <= row) A[0][i] = line + 1;
                else A[0][i] = line;
                i++;
            }
            
            if (row != 0){
                for (i = line + 1;i >= 1;i--){
                    for (int j = n;j >= 1;j--){
                        minYoungTableau(A, i, j);
                    }
                }
            }
            else{
                for (i = line;i >= 1;i--){
                    for (int j = n;j >= 1;j--){
                        minYoungTableau(A, i, j);
                    }
                }
            }
        }
        return A;
    }
    
    //删除并返回最小值
    public static int ExtractMin(int[][] A){
        if (A[1][1] == 2147483647){
            System.out.println("矩阵为空");
        }
        int min = A[1][1];
        A[1][1] = 2147483647;
        delminYoungTableau(A, 1, 1);
        A[0][0]--;
        return min;
    }
    
    public static void YoungInsert(int[][] A, int key){
        if (A[A.length - 1][A[0].length - 1] != 2147483647){
            System.out.println("矩阵已满");
        }
        A[A.length - 1][A[0].length - 1] = key;
        A[0][0]++;
        A[A.length - 1][0]++;
        A[0][A[0].length - 1]++;
        int i = A.length - 1;
        int j = A[0].length - 1;
        while (true){
            int upParenti = upParent(i, j);
            int upParentj = j;
            int leftParenti = i;
            int leftParentj = leftParent(i, j);
            int largesti = i;
            int largestj = j;
            if (upParenti >= 1 && A[upParenti][upParentj] > A[i][j]){
                largesti = upParenti;
                largestj = upParentj;
            }
            if (leftParentj >= 1 && A[leftParenti][leftParentj] > A[largesti][largestj]){
                largesti = leftParenti;
                largestj = leftParentj;
            }
            if (largesti != i || largestj != j){
                int temp = A[largesti][largestj];
                A[largesti][largestj] = A[i][j];
                A[i][j] = temp;
                if (temp == 2147483647){
                    A[i][0]--;
                    A[0][j]--;
                    A[largesti][0]++;
                    A[0][largestj]++;
                }                
                i = largesti;
                j = largestj;
            }
            else break;
        }
    }
    
    //排序
    public static int[] YoungSort(int[] nums){
        int n = (int) Math.sqrt(nums.length);
        int[][] A = buildYoungTableau(nums, n, n);
        int[] result = new int[nums.length];
        for (int i = 0;i < result.length;i++){
            result[i] = ExtractMin(A);
        }
        return result;
    }
    
    //查找
    public static int[] YoungFind(int[][] A, int key){
        int[] flags = new int[2];
        int i = A.length - 1;
        int j = 1;
        while (true){
            if (i < 1 || j < 1) break;
            if (A[i][j] == key){
                flags[0] = i;
                flags[1] = j;
                break;
            }
            else if (A[i][j] > key){
                i = upParent(i, j);
            }
            else j = rightChild(i, j);
        }
        return flags;
    }
}






















