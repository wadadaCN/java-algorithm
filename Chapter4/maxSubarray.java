/**
* @version 1.00 2018/6/9
* @author xiekun
*/

public class maxSubarray{
    public static void main(String[] args){
        //Todo
        int[] A = {100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
        //int[] result = linearMethod(A); //返回第result[0]天买入，第result[1]天卖出
        int[] B = doMinus(A);
        //int[] result = recursiveMethod(B, 0, B.length - 1); //返回第result[0]天与第result[1]天之间的收益是最大的，因此应该第result[0]天买入（实际应该是result[0]+1天，但是B的第0天对应A0天买入，1天卖出的收益），在第result[1]+1天卖出
        int[] result = linearMethod2(B);
        for (int item:result) System.out.println(item);
    }
    
    //暴力求解
    public static int[] linearMethod(int[] A){
        int maxProfit = 0;
        int start = 0;
        int end = 0;
        for (int i = 0;i < A.length;i++){
            for (int j = i;j < A.length;j++){
                if (A[j] - A[i] > maxProfit){
                    maxProfit = A[j] - A[i];
                    start = i;
                    end = j;
                }
            }
        }
        int[] result = {start, end, maxProfit};
        return result;
    }
    
    public static int[] doMinus(int[] A){
        int[] B = new int[A.length - 1];
        for (int i = 0;i < B.length;i++){
            B[i] = A[i + 1] - A[i];
        }
        return B;
    }
    
    //递归求解
    public static int[] recursiveMethod(int[] B, int start, int end){
        int[] flags = new int[3];
        if (start == end){
            flags[0] = start;
            flags[1] = end;
            flags[2] = B[start];
        }
        else{
            int mid = Math.floorDiv((start + end), 2);
            int[] left = recursiveMethod(B, start, mid);
            int[] right = recursiveMethod(B, mid + 1, end);
            int[] cross = recursiveMethodCross(B, start, mid, end);
            if (left[2] >= right[2] && left[2] >= cross[2]) flags = left;
            else if (right[2] >= left[2] && right[2] >= cross[2]) flags = right;
            else flags = cross;
        }
        return flags;
    }
    
    public static int[] recursiveMethodCross(int[] B,int start,int mid,int end){
        int leftSum = -2147483647; //-infinite
        int rightSum = -2147483647;
        int sum = 0;
        int leftIndex = mid;
        int rightIndex = mid + 1;
        for (int i = mid;i >= start;i--){
            sum += B[i];
            if (sum > leftSum){
                leftSum = sum;
                leftIndex = i;
            }
        }
        sum = 0;
        for (int i = mid + 1;i <= end;i++){
            sum += B[i];
            if (sum > rightSum){
                rightSum = sum;
                rightIndex = i;
            }
        }
        int[] flags = {leftIndex, rightIndex, leftSum + rightSum};
        return flags;
    }
    
    //线性非递归，O(n)，当sum小于0时意味着不能在之前买，寻找到收益为正的一天作为新的起点，当sum大于maxSum，意味着这一天可以作为卖出时间
    public static int[] linearMethod2(int[] B){
        int sum = 0;
        int maxSum = 0;
        int start = 0;
        int end = 0;
        for (int i = 0;i < B.length;i++){
            if (sum == 0 && B[i] > 0){
                start = i;
                sum += B[i];
            }
            else if (sum > 0){
                sum += B[i];
            }
            else sum = 0;
            if (sum > maxSum){
                end = i;
                maxSum = sum;
            }
        }
        int[] flags = {start, end, maxSum};
        return flags;
    }
}










