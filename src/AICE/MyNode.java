package AICE;

public class MyNode<T>{
	
	private MyNode<T> pointer;
	private Object obj;
	
	public MyNode(Object obj){
		this.obj = obj;
	}
	
	public void setNext(MyNode<T> myNode){
		pointer = myNode;
	}
	
	public MyNode<T> getNext(){
		return pointer;
	}
	
	public Object getObject(){
		return obj;
	}
	
	public void setObject(Object obj){
		this.obj = obj;
	}
	
}