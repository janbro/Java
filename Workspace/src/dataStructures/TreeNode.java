package dataStructures;

public class TreeNode<T> {
	
	private T item;
	private TreeNode<T> rightChild;
	private TreeNode<T> leftChild;
	
	public TreeNode(T newItem){
		item = newItem;
		leftChild = null;
		rightChild =  null;
	}
	
	public TreeNode(T newItem,TreeNode<T> left, TreeNode<T> right){
		item = newItem;
		leftChild = left;
		rightChild =  right;
	}
	
	public Object getItem(){
		return item;
	}
	
	public void setItem(T newItem){
		item = newItem;
	}
	
	public TreeNode<T> getRight(){
		return rightChild;
	}
	
	public TreeNode<T> getLeft(){
		return leftChild;
	}
	
	public void setLeft(TreeNode<T> node){
		leftChild = node;
	}
	
	public void setRight(TreeNode<T> node){
		rightChild = node;
	}

	public boolean isLeaf(TreeNode<T> node){
		if(node==null)
			return false;
		return node.getRight()==null&&node.getLeft()==null;
	}
	
	public int sumTree(TreeNode<T> root){
		if(isLeaf(root))
			return ((int)root.getItem());
		if(root.getRight()==null)
			return ((int)root.getItem())+((int)root.getLeft().getItem())+((int)sumTree(root.getLeft()));
		else if(root.getLeft()==null)
			return ((int)root.getItem())+((int)root.getRight().getItem())+((int)sumTree(root.getRight()));
		return ((int)root.getItem()+sumTree(root.getRight())+sumTree(root.getLeft()));
	}
	
	public int max(TreeNode<T> root,int max){
		if(isLeaf(root)){
			if(((int)root.getItem())>max)
				return ((int)root.getItem());
			else
				return max;
		}
		if(root.getRight()==null){
			if(max>((int)root.getItem()))
				return max(root.getLeft(),((int)root.getItem()));
			else
				return max(root.getLeft(),max);
		}
		else if(root.getLeft()==null){
			if(max>((int)root.getItem()))
				return max(root.getRight(),((int)root.getItem()));
			else
				return max(root.getRight(),max);
		}
		if(max(root.getLeft(),max)>max(root.getRight(),max))
			return max(root.getLeft(),max);
		else
			return max(root.getRight(),max);
	}
	
	public int depth(TreeNode<T> root){
		if(isLeaf(root))
			return 0;
		if(1+depth(root.getRight())>1+depth(root.getLeft()))
			return 1+depth(root.getRight());
		else
			return 1+depth(root.getLeft());
	}
	
	public String printPreOrder(TreeNode<T> root){
		if(isLeaf(root))
			return root.getItem().toString();
		if(root.getRight()==null)
			return root.getItem().toString()+"\n"+printPreOrder(root.getLeft());
		else if(root.getLeft()==null)
			return root.getItem().toString()+"\n"+printPreOrder(root.getRight());
		return root.getItem().toString()+"\n"+printPreOrder(root.getLeft())+"\n"+printPreOrder(root.getRight());
	}
	
	public String printInOrder(TreeNode<T> root){
		if(isLeaf(root))
			return root.getItem().toString();
		if(root.getRight()==null)
			return root.getItem().toString()+"\n"+printInOrder(root.getLeft());
		else if(root.getLeft()==null)
			return root.getItem().toString()+"\n"+printInOrder(root.getRight());
		return printInOrder(root.getLeft())+"\n"+root.getItem().toString()+"\n"+printInOrder(root.getRight());
	}
	
	public String printPostOrder(TreeNode<T> root){
		if(isLeaf(root))
			return root.getItem().toString();
		if(root.getRight()==null)
			return root.getItem().toString()+"\n"+printPostOrder(root.getLeft());
		else if(root.getLeft()==null)
			return root.getItem().toString()+"\n"+printPostOrder(root.getRight());
		return printPostOrder(root.getLeft())+"\n"+printPostOrder(root.getRight())+"\n"+root.getItem().toString();
	}

}
