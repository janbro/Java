package dataStructures;

public class MyNode {
	
	private MyNode pointer;
	private Object obj;
	
	public MyNode(Object obj){
		this.obj = obj;
	}
	
	public void setNext(MyNode myNode){
		pointer = myNode;
	}
	
	public MyNode getNext(){
		return pointer;
	}
	
	public Object getObject(){
		return obj;
	}
	
	public void setObject(Object obj){
		this.obj = obj;
	}
	
}