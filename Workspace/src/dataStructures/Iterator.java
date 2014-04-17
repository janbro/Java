package dataStructures;

public class Iterator {
	
	MyNode prevPrevNode;
	MyNode prevNode;
	MyNode node;
	
	public Iterator(MyNode node){
		this.prevPrevNode = null;
		this.prevNode = null;
		this.node = node;
	}
	
	public boolean hasNext(){
		return this.node.getNext()!=null;
	}
	
	public MyNode next(){
		if(this.node==null)
			throw new java.util.NoSuchElementException();
		this.prevPrevNode = this.prevNode;
		this.prevNode = this.node;
		this.node = node.getNext();
		return prevNode;
	}
	
	public MyNode remove(){
		if(prevNode==null&&prevPrevNode==null){
			MyNode temp = node;
			node = node.getNext();
			return temp;
		}
		if(prevPrevNode==null){
			prevNode.setNext(node.getNext());
			return prevNode;
		}
		this.prevPrevNode.setNext(this.node);
		this.prevNode.setNext(null);
		return prevNode;
	}

}