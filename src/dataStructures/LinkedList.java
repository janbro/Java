package dataStructures;

public class LinkedList {
	
	private MyNode head;
	private int size;
	
	public LinkedList(){
		this.head = null;
		this.size = 0;
	}
	
	public Object get(int index){
		if(index<0||index>=size)
			throw new java.lang.ArrayIndexOutOfBoundsException(index);
		MyNode place = this.head;
		for(int i=size-1;i>index;i--)
			place = place.getNext();
		return place.getObject();
	}
	
	public void set(int index, Object obj){
		if(index<0||index>=size)
			throw new java.lang.ArrayIndexOutOfBoundsException(index);
		MyNode temp = new MyNode(obj);
		MyNode place = this.head;
		if(index==size-1){
			temp.setNext(this.head.getNext());
			head = temp;
			return;
		}
		for(int i=size-1;i>index+1;i--)
			place = place.getNext();
		temp.setNext(place.getNext().getNext());
		place.setNext(temp);
	}
	
	public void insert(int index,Object obj){
		if(index<0||index>=size)
			throw new java.lang.ArrayIndexOutOfBoundsException(index);
		MyNode node = new MyNode(obj);
		MyNode place = this.head;
		for(int i=size-1;i>index;i--)
			place = place.getNext();
		node.setNext(place.getNext());
		place.setNext(node);
		size++;
	}
	
	public void append(Object obj){
		MyNode node = new MyNode(obj);
		if(size==0){
			this.head = node;
			size++;
			return;
		}
		node.setNext(this.head);
		this.head = node;
		size++;
	}
	
	public Object delete(int index){
		if(index>=size||index<0)
			throw new java.lang.ArrayIndexOutOfBoundsException(index);
		if(index==size-1){
			this.head = this.head.getNext();
			size--;
			return this.head;
		}MyNode node = this.head;
		for(int i = size-1;i>index+1;i--){
			node = node.getNext();
		}
		Object deleted = node.getNext().getObject();
		node.setNext(node.getNext().getNext());
		size--;
		return deleted;
	}
	
	public Object delete(Object obj){
		MyNode node = this.head;
		MyNode prevNode = null;
		for(int i = size-1;i>=0;i--){
			if(obj.equals(node.getObject())){
				prevNode.setNext(node.getNext());
				size--;
				return node;
			}
			prevNode = node;
			node = node.getNext();
		}
		return null;
	}
	
	public boolean contains(Object obj){
		MyNode node = this.head;
		for(int i = size-1;i>=0;i--){
			if(obj.equals(node.getObject())){
				return true;
			}
			node = node.getNext();
		}
		return false;
	}
	
	public String toString(){
		if(size==0)
			return "[]";
		MyNode place = this.head;
		String res="]";
		while(place.getNext()!=null){
			res=","+place.getObject().toString()+res;
			place=place.getNext();
		}
		res="["+place.getObject()+res;
		return res;
	}
	
	public int size(){
		return this.size;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public void clear(){
		this.head = null;
	}
}
