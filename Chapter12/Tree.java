/**
* @version 1.00 2018.7.17
* @author xiekun
*/

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
	
	private void treeInsertWithWhile(Tree T, treeNode z){
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
	
	private void treeInsert(Tree T, treeNode z){
		if (T.root == null) T.root = z;
		else T.treeInsert(T.root, z);
	}
	private void treeInsert(treeNode node, treeNode z){
		if (z.val < node.val){
			z.parent = node;
			if (node.left != null) {
				node = node.left;
				treeInsert(node, z);
			}
			else node.left = z;
		}
		else{
			z.parent = node;
			if (node.right != null) {
				node = node.right;
				treeInsert(node, z);
			}
			else node.right = z;
		}
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
	
	private treeNode treeMinimum(treeNode x){
		while (x.left != null){
			x = x.left;
		}
		return x;
	}
	
	private treeNode treeMaximum(treeNode x){
		while (x.right != null){
			x = x.right;
		}
		return x;
	}
	
	private treeNode nodeSuccessor(treeNode x){
		if (x.right != null) return treeMinimum(x.right);
		treeNode y = x.parent;
		while (y != null && x == y.right){
			x = y;
			y = x.parent;
		}
		return y;
	}
	
	private treeNode nodePredecessor(treeNode x){
		if (x.left != null) return treeMaximum(x.left);
		treeNode y = x.parent;
		while (y != null && x == y.left){
			x = y;
			y = x.parent;
		}
		return y;
	}
	
	private void transplant(Tree T, treeNode u, treeNode v){
		if (u.parent == null) T.root = v;
		else if (u == u.parent.left) u.parent.left = v;
		else u.parent.right = v;
		if (v != null) v.parent = u.parent;
	}
	private void treeDelete(Tree T, treeNode z){
		if (z.left == null) transplant(T, z, z.right);
		else if (z.right == null) transplant(T, z, z.left);
		else{
			treeNode y = treeMinimum(z.right);
			if (y.parent != z){
				transplant(T, y, y.right);
				y.right = z.right;
				z.right.parent = y;
			}
			transplant(T, z, y);
			y.left = z.left;
			z.left.parent = y;
		}
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
		int aimKey = 9;
		treeNode result = T.treeSearchWithWhile(T.root, aimKey);
		treeNode predecessor = T.nodePredecessor(result);
		T.treeDelete(T, predecessor);
		T.inorderTreeWalk(T.root);
		for (Integer item:T.inorderList){
		    System.out.println(item);
		}
		//System.out.println(predecessor.val);
		//System.out.println(T.treeMaximum(T.root).val);
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
