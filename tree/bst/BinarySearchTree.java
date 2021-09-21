package tree.bst;

import java.util.ArrayList;
import java.util.List;


/**
 * A Binary Search Tree is a rooted binary tree, in which the root node stores the data and the address of
 * left and right child, and to satisfy the BST property the left node must me smaller than the parent node, 
 * the right node must be greater than the parent node.
 * 
 * Basic core entity of BST:
 *                   
 *                       node1
 *                       /   \
 *   (leftNode < node1) /     \ (rightNode > node1)
 *                     /       \
 *                leftNode    rightNode
 * 
 * 
 * BST operations:
 *   - search
 *   - traverse
 *   - insertion
 *   - delete
 * 
 * 
 * @author Prashant Singh
 *
 */
public class BinarySearchTree {
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
		//Step-1: Base condition, when value is insert from root
		if (currentNode == null) {
			return new TreeNode(value);
		}
		//Step-2: If currentNode value is lesser than the insert value,
		// traverse from root(using recursion) and update the left child(not returning, it always return root)
		if (value < currentNode.value) {
			currentNode.left = addRecursive(currentNode.left, value);
		//Step-3: If currentNode value is greater than the insert value,
		// traverse from root(using recursion) and update the right child(not returning, it always return root)
		} else if (value > currentNode.value) {
			currentNode.right = addRecursive(currentNode.right, value);
		} else {
		// value already exist
			return currentNode;
		}
		// return after the left/right child update
		return currentNode;
	}
	
	/**
	 * Insert data into BST.
	 * 
	 * @param value
	 */
	public void add(int value) {
		root = addRecursive(root, value);
	}
    
	private TreeNode addIterative(TreeNode currentNode, int value) {
		// Step-1: Base condition, when value is insert from root
		if (currentNode == null) {
			return new TreeNode(value);
		}
		// Step-2: Create a node to tell the correct place of insertion.
		TreeNode parentNode = currentNode;
		TreeNode tempNode = currentNode;
		// Step-3: Traverse all node to find the correct place of insertion
		while (tempNode != null) {
			parentNode = tempNode;
			// if the currentNode values is less than the left, go left other right
			tempNode = value < tempNode.value ? tempNode.left : tempNode.right;
		}
		// Step-4: Once we find the correct parentNode, check value goes to left or right child.
		if (value < parentNode.value) {
			parentNode.left = new TreeNode(value);
		} else {
			parentNode.right = new TreeNode(value);
		}
		// return after the left/right child update
		return currentNode;
	}
	/**
	 * Insert data into BST using iterative approach.
	 * 
	 * @param value
	 */
	public void addIterative(int value) {
		root = addIterative(root, value);
	}
	
	/**
	 * TRAVERSAL: POST-ORDER-TRAVERSAL
	 * 
	 * Policy: L(traverse left node)-R(right node)-D(root node)
	 * In this traversal, we have to execute postOrderTraversal policy(LRD) at each node. 
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> postOrderTraversal(TreeNode root) {
		List<Integer> postOrderTraverseVal = new ArrayList<Integer>();
		if (root == null) {
			return postOrderTraverseVal;
		}
		return processPostOrderTraversal(root, postOrderTraverseVal);
	}

	private List<Integer> processPostOrderTraversal(TreeNode root, List<Integer> processedList) {
		if (root != null) {
			processPostOrderTraversal(root.left, processedList);
			processPostOrderTraversal(root.right, processedList);
			processedList.add(root.value);
		}
		return processedList;
	}

	/**
	 * TRAVERSAL: PRE-ORDER-TRAVERSAL
	 *
	 * Policy: D(root node)-L(traverse left node)-R(right node)
	 * In this traversal, we have to execute preOrderTraversal policy(DLR) at each node.
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> preOrderTraversal(TreeNode root) {
		List<Integer> preOrderTraverseVal = new ArrayList<Integer>();
		if (root == null) {
			return preOrderTraverseVal;
		}
		return processPreOrderTraversal(root, preOrderTraverseVal);
	}

	private List<Integer> processPreOrderTraversal(TreeNode root, List<Integer> processedList) {
		if (root != null) {
			processedList.add(root.value);
			processPreOrderTraversal(root.left, processedList);
			processPreOrderTraversal(root.right, processedList);

		}
		return processedList;
	}

	/**
	 * TRAVERSAL: IN-ORDER-TRAVERSAL
	 * 
	 * Policy: L(traverse left node)-D(root node)-R(right node)
	 * In this traversal, we have to execute inOrderTraversal policy(LDR) at each node.
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> inOrderTraversal(TreeNode root) {
		List<Integer> inOrderTraverseVal = new ArrayList<Integer>();
		if (root == null) {
			return inOrderTraverseVal;
		}
		return processInOrderTraversal(root, inOrderTraverseVal);
	}

	private List<Integer> processInOrderTraversal(TreeNode root, List<Integer> processedList) {
		if (root != null) {
			processInOrderTraversal(root.left, processedList);
			processedList.add(root.value);
			processInOrderTraversal(root.right, processedList);
		}
		return processedList;
	}

	// SEARCH: 1-RECURSIVE  2-ITERATIVE
	public TreeNode searchRecursive(TreeNode root, int searchData) {
		// Step-1: Base condition
		if (root == null)
			return null;
		if (root.value == searchData)
			return root;
		// Step-2: Recur based on condition
		if (searchData < root.value) {
			return searchRecursive(root.left, searchData);
		}
		return searchRecursive(root.right, searchData);
	}

	public TreeNode searchIterative(TreeNode root, int searchData) {
		TreeNode currentNode = root;
		while (currentNode != null) {
			if (searchData == currentNode.value) {
				return currentNode;
			}
			if (searchData < currentNode.value) {
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
			}
		}
		return null;
	}

	// DELETE: 1-RECURSIVE 2-ITERATIVE
	public TreeNode deleteNodeRecursive(TreeNode root, int deleteData) {
	    // Step-1: Base condition deleteData is not found
		if(root == null) return null;
		// Step-2: Search the node which matched the deleteData using recursive approach
		//if the deleteData is less than currentNode recur the left, otherwise right
		if (deleteData < root.value) {
			root.left = deleteNodeRecursive(root.left, deleteData);
		} else if(deleteData > root.value){
			root.right = deleteNodeRecursive(root.right, deleteData);
		}
		// key found enter in else block
		else {
			// Step-3: DELETE NODE, 3 types: delete leaf node, single child node, both child node
			// case-1: Delete node that has no child node(leaf node)
			if(root.left == null && root.right == null) {
				return null; // update root to null
			}
			// case-2: Delete node that has both child
	    	else if(root.left != null && root.right != null) {
	    		/**
	    		 *           root
	    		 *         /      \
	    		 *        /        \ 
	    		 *      N1          N2 <---delete(1- find the maxNode(N12) of leftChild, 2- Update N2 data by maxNode data,
	    		 *     / \          / \           3-Delete maxNode)
	    		 *    /   \        /    \
	    		 *   N3   N4      N5     N6 
	    		 *  / \   / \    /  \    / \
	    		 * N7 N8 N9 N10 N11 N12 N13 N14
	    		 *          
	    		 */
				// find the maxNode
				TreeNode maxNode = getMaxTreeNode(root.left);
				// update the currentNode data by maxNode data
				root.value = maxNode.value;
				// delete maxNode
				root.left = deleteNodeRecursive(root.left, maxNode.value); // already have the logic to delete leaf node
			}
			// case-3: Delete the node that has one child
	    	/**
		     *           root
			 *         /      \
			 *        /        \ 
			 *      N1          N2 <---delete(1-Find the child node of currentNode(N2)
			 *     / \            \           2- Initialize the parentNode(which is root) by child node)
			 *    /   \            \
			 *   N3   N4            N6 
			 *  / \   / \          /  \
			 * N7 N8 N9 N10      N13  N14
	    	 */
			else {
				// find child node of currentNode
				TreeNode childNode = root.left != null ? root.left : root.right;
				root = childNode;
			}
		}
		return root;
	}
	
	private TreeNode getMaxTreeNode(TreeNode currentNode) {
		while (currentNode.right != null) {
			currentNode = currentNode.right;
		}
		return currentNode;
	}
	
	public TreeNode deleteNodeIterative(TreeNode root, int deleteData) {
		// Step-1: Create pointers to store the currentNode and parent of currentNode
		TreeNode currentNode = root;
		TreeNode parentNode = null;

		// Step-2: Search the node which matched the deleteData and set it's parentNode
		while (currentNode != null && currentNode.value != deleteData) {
			// update parentNode
			parentNode = currentNode;
			// if the given data is less than currentNode, goto left child otherwise right child node
			currentNode = deleteData < currentNode.value ? currentNode.left : currentNode.right;
		}
		// Step-3: Check if the deleteData is not found in BST
		if (currentNode == null) {
			return root;
		}
		// Step-4: DELETE NODE, 3 types: delete leaf node, single child node, both child node
		// case-1: Delete node that has no child node(leaf node)
		if (currentNode.left == null && currentNode.right == null) {
			// check if the node is root or not
			if (currentNode != root) {
				// node is not root, set it's parent left/right child node to null
				if (parentNode.left == currentNode) { // check the currentNode is left or right of parentNode
					parentNode.left = null; // removing reference
				} else {
					parentNode.right = null;
				}
			} else {
				return null;
			}
		}
    	// case-2: Delete node that has both child
    	else if(currentNode.left != null && currentNode.right != null) {
    		/**
    		 *           root
    		 *         /      \
    		 *        /        \ 
    		 *      N1          N2 <---delete(1- find the minNode(N13) of rightChild, 2- Update N2 data by minNode data,
    		 *     / \          / \           3-Delete minNode)
    		 *    /   \        /    \
    		 *   N3   N4      N5     N6 
    		 *  / \   / \    /  \    / \
    		 * N7 N8 N9 N10 N11 N12 N13 N14
    		 *          
    		 */
			// find the minNode
			TreeNode minNode = getMinTreeNode(currentNode.right);
			int minNodeData = minNode.value;
			// delete minNode
			deleteNodeIterative(root, minNode.value); // already have the logic to delete leaf node
			// update the currentNode by minNode data
			currentNode.value = minNodeData;
		}
    	// case-3: Delete the node that has one child
    	/**
	     *           root
		 *         /      \
		 *        /        \ 
		 *      N1          N2 <---delete(1-Find the child node of currentNode(N2) 2-Check for root node
		 *     / \            \           3- Initialize the parentNode by child node)
		 *    /   \            \
		 *   N3   N4            N6 
		 *  / \   / \          /  \
		 * N7 N8 N9 N10      N13  N14
    	 */
		else {
			// find child node of currentNode
			TreeNode childNode = currentNode.left != null ? currentNode.left : currentNode.right;
			// check for root node
			if (currentNode != root) {
				// set the parentNode
				if (currentNode == parentNode.left) {
					parentNode.left = childNode;
				} else {
					parentNode.right = childNode;
				}
			} else {
				root = childNode;
			}
		}
    	return root;
    }
	
    private TreeNode getMinTreeNode(TreeNode currentNode) {
    	while(currentNode.left != null) {
    		currentNode = currentNode.left;
    	}
    	return currentNode;
    }
	// runner
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		
		// insert operation
		int inputs[] = {50, 22, 3, 6, 44, 9};
		for (int input : inputs) {
			bst.addIterative(input);
		}
		
		// traverse
		List<Integer> inOrderTraversals = bst.inOrderTraversal(bst.root);
		System.out.println(inOrderTraversals);
		
		// search
		System.out.println(bst.searchRecursive(bst.root, 44).value);
		System.out.println(bst.searchIterative(bst.root, 50).value);
		
		// delete
		//bst.root = bst.deleteNodeRecursive(bst.root, 9);
		//bst.root = bst.deleteNodeRecursive(bst.root, 50);
		//bst.root = bst.deleteNodeRecursive(bst.root, 3);
		//System.out.println(bst.inOrderTraversal(bst.root));
		bst.root = bst.deleteNodeIterative(bst.root, 50);
		//bst.root = bst.deleteNodeIterative(bst.root, 44);
		//bst.root = bst.deleteNodeIterative(bst.root, 3);
		System.out.println(bst.inOrderTraversal(bst.root));
		
	}
}
