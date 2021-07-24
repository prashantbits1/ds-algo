package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LEVEL ORDER BINARY TREE TRAVERSAL
 * 
 * Level order traversal of a tree is Breadth First Search traversal.
 *       1
 *      /  \ 
 *     2     3
 *   /   \  
 *  4     5 
 *  
 * Level order traversal of tree: [1, 2, 3, 4, 5]
 * 
 * Algorithm:
 * 1- Recursion:
 *   In this we need two functions -> one to print level order of traversal(traverseLevelOrder)
 *   and the other one is to traverse all nodes on a given level(traverseCurrentLevel)
 *   
 *   traverseLevelOrder(TreeNode tree):
 *       for(level -> 1 to height(tree)):
 *           traverseCurrentLevel(tree, level)
 *           
 *   traverseCurrentLevel(TreeNode root, int level):
 *        if root==null -> return;
 *        if level==1 -> print(root.data)
 *        else if(level>1):
 *                 traverseCurrentLevel(root.left, level-1)
 *                 traverseCurrentLevel(root.right, level-1)
 *
 *  2- Using Queue
 *  
 *    traverseLevelOrder(tree):
 *       1- Base Condition
 *       2- Create an empty queue q
 *       3- root= tree.root 
 *       4- Loop while(root is not null):
 *                i-) print(root.data)
 *               ii-) Enqueue root's children
 *                    if(root.left!=null) -> q.add(root.left)
 *                    if(root.right!=null) -> q.add(root.right)
 *    
 *  
 *  
 */
public class LevelOrderBST {
	
	// Node of a tree
    static class TreeNode{
    	int data;
    	TreeNode left, right;
    	TreeNode(int data){
    		this.data = data;
    		this.left = null;
    		this.right = null;
    	}
    }
	
	// root
    TreeNode root;
    LevelOrderBST(){
    	root = null;
    }
    
    // height of tree
    public int heightOfTree(TreeNode root) {
    	if(root==null) return 0;
    	// compute the height of each subtree
    	int leftHeight = heightOfTree(root.left);
    	int rightHeight = heightOfTree(root.right);
    	
    	// use the larger height
    	if(leftHeight>rightHeight) {return leftHeight+1;}
    	else {return rightHeight+1;}
    }
    
    // Level Order Traversal using recursion
    public List<Integer> traverseLevelOrder(TreeNode root){
    	if(root==null) return Arrays.asList();
    	List<Integer> levelOrderTraversal = new ArrayList<Integer>();
    	int heightOfTree = heightOfTree(root);
    	for (int level = 1; level <=heightOfTree ; level++) {
    		traverseCurrentLevel(root, level, levelOrderTraversal);
		}
    	return levelOrderTraversal;
    }
    
    /**
     * Recursion trace
     * 
     * when we call for level=3: traverseCurrentLevel(rootNode, 3, result) --> tCL(root, 3, r)
     *
     *                            tCL(root, 3, r)
     *                            /              \
     *             tCL(root.left, 2, r)             tCL(root.right, 2, r)
     *                  /           \                   /             \
     *                 /             \    tCL(root.right.left, 1, r) tCL(root.right.right, 1, r)
     * tCL(root.left.left, 1, r)  tCL(root.left.right, 1, r)
     * 
     *   Replacing nodes by values:
                                  tCL(1, 3, r)
     *                            /           \
     *                 tCL(2, 2, r)           tCL(3, 2, r)
     *                 /           \           /          \
     *                /             \   tCL(null, 1, r)  tCL(null, 1, r)
     *           tCL(4, 1, r)    tCL(5, 1, r)
     * 
     * @param root
     * @param level
     * @param result
     */
    private void traverseCurrentLevel(TreeNode root, int level, List<Integer> result) {
    	// handle null scenario while recursion
    	if(root==null) return;
    	if(level == 1) result.add(root.data);
    	else if(level>1) {
    		traverseCurrentLevel(root.left, level-1, result);
    		traverseCurrentLevel(root.right, level-1, result);
    	}
    }
    
    // Level Order traversal using Queue
    public List<Integer> levelOrderTraversalUsingQueue(TreeNode root){
    	List<Integer> traversedList = new ArrayList<Integer>();
    	if(root==null) return Arrays.asList();
    	Queue<TreeNode> queue = new LinkedList<LevelOrderBST.TreeNode>();
    	queue.add(root);
    	while(!queue.isEmpty()) {
    		TreeNode currentNode = queue.poll();
    		traversedList.add(currentNode.data);
    		if(currentNode.left!=null) {queue.add(currentNode.left);}
    		if(currentNode.right!=null) {queue.add(currentNode.right);}
    	}
    	
    	return traversedList;
    }
    
    public static void main(String[] args) {
		LevelOrderBST lob = new LevelOrderBST();
		lob.root = new TreeNode(1);
		lob.root.left = new TreeNode(2);
		lob.root.right = new TreeNode(3);
		lob.root.left.left = new TreeNode(4);
		lob.root.left.right = new TreeNode(5);
		System.out.println("Level order traversal using recursion: "+ lob.traverseLevelOrder(lob.root));
		System.out.println("Level order traversal using queue: "+ lob.levelOrderTraversalUsingQueue(lob.root));
	}
}
