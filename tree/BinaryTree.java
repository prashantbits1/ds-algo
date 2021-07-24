package tree;

import java.util.ArrayList;
import java.util.List;


public class BinaryTree {
	class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		public TreeNode(int value) {
			this.value = value;
			left = null;
			right = null;
		}

	}

	// initialize the root with null
	public TreeNode root;

	public TreeNode addRecursive(TreeNode currentNode, int value) {
		// base condition, when value is insert from root
		if (currentNode == null) {
			return new TreeNode(value);
		}
		// if currentNode value is lesser than the insert value,
		// traverse from root(using recursion) and update the left child(not returning,
		// it always return root)
		if (value < currentNode.value) {
			currentNode.left = addRecursive(currentNode.left, value);
			// if currentNode value is greater than the insert value,
			// traverse from root(using recursion) and update the right child(not returning,
			// it always return root)
		} else if (value > currentNode.value) {
			currentNode.right = addRecursive(currentNode.right, value);
		} else {
			// value already exist
			return currentNode;
		}
		// return after the left/right child update
		return currentNode;
	}

	public void add(int value) {
		root = addRecursive(root, value);
	}

	private List<Integer> processPostOrderTraversal(TreeNode root, List<Integer> processedList) {
		if (root != null) {
			processPostOrderTraversal(root.left, processedList);
			processPostOrderTraversal(root.right, processedList);
			processedList.add(root.value);
		}
		return processedList;
	}

	public List<Integer> postOrderTraversal(TreeNode root) {
		List<Integer> postOrderTraverseVal = new ArrayList<Integer>();
		if (root == null) {
			return postOrderTraverseVal;
		}
		return processPostOrderTraversal(root, postOrderTraverseVal);
	}

	private List<Integer> processPreOrderTraversal(TreeNode root, List<Integer> processedList) {
		if (root != null) {
			processedList.add(root.value);
			processPreOrderTraversal(root.left, processedList);
			processPreOrderTraversal(root.right, processedList);

		}
		return processedList;
	}

	public List<Integer> preOrderTraversal(TreeNode root) {
		List<Integer> preOrderTraverseVal = new ArrayList<Integer>();
		if (root == null) {
			return preOrderTraverseVal;
		}
		return processPreOrderTraversal(root, preOrderTraverseVal);
	}

	private List<Integer> processInOrderTraversal(TreeNode root, List<Integer> processedList) {
		if (root != null) {
			processInOrderTraversal(root.left, processedList);
			processedList.add(root.value);
			processInOrderTraversal(root.right, processedList);
		}
		return processedList;
	}

	public List<Integer> inOrderTraversal(TreeNode root) {
		List<Integer> inOrderTraverseVal = new ArrayList<Integer>();
		if (root == null) {
			return inOrderTraverseVal;
		}
		return processInOrderTraversal(root, inOrderTraverseVal);
	}
	
}
