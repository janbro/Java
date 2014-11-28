package dataStructures;

import AICE.MyNode;

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
		this.prevPrevNode = this.prevNode;
		this.prevNode = this.node;
		this.node = node.getNext();
		return this.node;
	}
	
	public MyNode remove(){
		if(prevNode==null&&prevPrevNode==null){
			if(node.getNext()==null)
				return null;
			else{
				MyNode temp = node;
				node = node.getNext();
				return temp;
			}
		}
		if(prevPrevNode==null){
			prevNode.setNext(null);
			return prevNode;
		}
		this.prevPrevNode.setNext(this.node);
		this.prevNode.setNext(null);
		return prevNode;
	}

}