import java.util.ArrayList;

public class Tree{
	private treeNode root;
	private ArrayList<Integer> inorderList;
	private ArrayList<Integer> preorderList;
	private ArrayList<Integer> postorderList;
	
	public Tree(){
		this.root = null;
		this.inorderList = new ArrayList<Integer>();
		this.preorderList = new ArrayList<Integer>();
		this.postorderList = new ArrayList<Integer>();
	}
	
	private void inorderTreeWalk(treeNode node){
		if (node != null){
			inorderTreeWalk(node.left);
			inorderList.add(node.val);
			inorderTreeWalk(node.right);
		}
	}

    private void preorderTreeWalk(treeNode node){
        if (node != null){
			preorderList.add(node.val);
			preorderTreeWalk(node.left);
			preorderTreeWalk(node.right);
		}
    }
    
    private void postorderTreeWalk(treeNode node){
        if (node != null){
			postorderTreeWalk(node.left);
			postorderTreeWalk(node.right);
			postorderList.add(node.val);
		}
    }
	
	private void treeInsert(Tree T, treeNode z){
		treeNode y = null;
		treeNode x = T.root;
		while (x != null){
			y = x;
			if (z.val < x.val) x = x.left;
			else x = x.right;
		}
		z.parent = y;
		if (y == null) T.root = z;
		else if (z.val < y.val) y.left = z;
		else y.right = z;
	}
	
	private void buildTree(Tree T, int x){
		treeNode node = new treeNode(x);
		treeInsert(T, node);
	}
	
	private treeNode treeSearch(treeNode node, int k){
	    treeNode result = null;
	    if (node == null || k == node.val) result = node;
	    else if (k < node.val) result = treeSearch(node.left, k);
	    else result = treeSearch(node.right, k);
	    return result;
	}
	
	private treeNode treeSearchWithWhile(treeNode node, int k){
	    while (node != null && node.val != k){
	        if (k < node.val) node = node.left;
	        else node = node.right;
	    }
	    return node;
	}
	
	private treeNode treeMinimum(){
		treeNode x = this.root;
		while (x.left != null){
			x = x.left;
		}
		return x;
	}
	
	private treeNode treeMaximum(){
		treeNode x = this.root;
		while (x.right != null){
			x = x.right;
		}
		return x;
	}
	
	public static void main(String[] args){
		Tree T = new Tree();
		int[] nums = {12, 5, 2, 9, 18, 15, 13, 17, 19};
		for (int item:nums) {
			T.buildTree(T, item);
		}
		T.inorderTreeWalk(T.root);
		for (Integer item:T.inorderList){
		    System.out.println(item);
		}
		//int aimKey = 6;
		//treeNode result = T.treeSearchWithWhile(T.root, aimKey);
		//if (result != null){
		//    System.out.println(result.val);
		//}
		System.out.println(T.treeMaximum().val);
	}
}

class treeNode{
	int val;
	treeNode left;
	treeNode right;
	treeNode parent;
	public treeNode(int x) {
		val = x;
	}
}
