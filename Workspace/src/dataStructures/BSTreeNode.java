package dataStructures;

public class BSTreeNode<T> extends TreeNode<T> {
	
	public BSTreeNode(T newItem) {
		super(newItem);
		// TODO Auto-generated constructor stub
	}
	
	public BSTreeNode(T newItem,BSTreeNode<T> right,BSTreeNode<T> left) {
		super(newItem,left,right);
		// TODO Auto-generated constructor stub
	}
	
	public void addNode(TreeNode<T> root,TreeNode<T> node){
		if((int)node.getItem()==(int)root.getItem())
			return;
		else if(isLeaf(root)){
			if((int)node.getItem()<(int)root.getItem()){
				root.setLeft(node);
				return;
			}
			else{
				root.setRight(node);
				return;
			}
		}
		else if((int)node.getItem()<(int)root.getItem()&&root.getLeft()==null){
			root.setLeft(node);
			return;
		}
		else if((int)node.getItem()>(int)root.getItem()&&root.getRight()==null){
			root.setRight(node);
			return;
		}
		else if((int)node.getItem()<(int)root.getItem())
			addNode(root.getLeft(),node);
		else
			addNode(root.getRight(),node);
	}

}