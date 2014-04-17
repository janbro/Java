package dataStructures;

public class ArrayList {
	
	private static final int DEFAULT_CAPACITY = 16;
	private Object[] objArray = {};
	private int numOfElements;
	
	public ArrayList(){
		numOfElements = 0;
		objArray = new Object[DEFAULT_CAPACITY];
	}
	
	public ArrayList(int capacity){
		numOfElements = 0;
		objArray = new Object[capacity];
	}
	
	public int size(){
		return numOfElements;
	}
	
	public Object get(int index){
		if(index>numOfElements-1)
			throw new java.lang.ArrayIndexOutOfBoundsException(index);
		return objArray[index];
	}
	
	public Object get(Object obj){
		for(int i=0;i<numOfElements;i++){
			if(obj.equals(objArray[i]))
				return objArray[i];
		}
		return null;
	}
	
	public Object set(int index, Object obj){
		Object orig = objArray[index];
		if(index>numOfElements)
			return null;
		objArray[index] = obj;
		return orig;
	}
	
	public boolean isEmpty(){
		return numOfElements==0;
	}
	
	public String toString(){
		String res = "[";
		if(numOfElements==0)
			return "[]";
		if(numOfElements==1)
			return res+=(objArray[0].toString()+"]");
		for(int i=0;i<numOfElements-1;i++){
			res+=objArray[i].toString()+",";
		}
		res+=(objArray[numOfElements-1].toString()+"]");
		return res;
	}
	
	public void add(Object obj){
		numOfElements++;
		if(numOfElements==1){
			objArray[0]=obj;
			return;
		}
		Object[] copy = objArray.clone();
		if(numOfElements>objArray.length)
			objArray = new Object[objArray.length+DEFAULT_CAPACITY];
		for(int i=0;i<numOfElements-1;i++)
			objArray[i]=copy[i];
		objArray[numOfElements-1] = obj;
	}
	
	public void add(int index, Object obj){
		if(index>numOfElements-1)
			throw new java.lang.ArrayIndexOutOfBoundsException(index);		
		numOfElements++;
		Object[] copy = objArray.clone();
		if(numOfElements>objArray.length)
			objArray = new Object[objArray.length+DEFAULT_CAPACITY];
		for(int i=0;i<index;i++)
			objArray[i] = copy[i];
		objArray[index] = obj;
		for(int i=index+1;i<numOfElements;i++)
			objArray[i] = copy[i-1];
	}
	
	public Object remove(int index){
		if(index>numOfElements-1)
			throw new java.lang.ArrayIndexOutOfBoundsException(index);	
		numOfElements--;
		Object[] copy = objArray.clone();
		if(numOfElements>objArray.length)
			objArray = new Object[objArray.length+DEFAULT_CAPACITY];
		for(int i=0;i<index;i++)
			objArray[i] = copy[i];
		for(int i=index+1;i<numOfElements;i++)
			objArray[i-1] = copy[i];
		return copy[index];
	}
	
	public boolean remove(Object obj){
		numOfElements--;
		int index;
		Object[] copy = objArray.clone();
		if(numOfElements>objArray.length)
			objArray = new Object[objArray.length+DEFAULT_CAPACITY];
		for(index=0;index<=numOfElements;index++){
			if(index>=numOfElements)
				return false;
			if(obj.equals(copy[index])){
				index+=1;
				break;
			}
			objArray[index] = copy[index];
		}
		for(index=index;index<=numOfElements;index++){
			objArray[index-1] = copy[index];
		}
		return true;
	}
	

}
