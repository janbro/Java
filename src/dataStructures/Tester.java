package dataStructures;

import AICE.MyNode;

public class Tester {
	
	public static void main(String[] args){
		/*MyArrayList Testing
		ArrayList arr = new ArrayList();

		System.out.println(arr.isEmpty());
		
		arr.append("Hello World");
		arr.append("What");
		
		arr.append(1,"gg");
		
		arr.append("asdf");
		
		System.out.println(arr.toString());
		System.out.println(arr.size());
		System.out.println(arr.delete("What"));
		System.out.println(arr.toString());
		System.out.println(arr.size());
		System.out.println(arr.isEmpty());*/
		
		/*MyLinkedList Testing
		LinkedList myList = new LinkedList();
		myList.append("Hello");
		myList.append("World");
		myList.delete(1);
		for(int i=0;i<4;i++)
			myList.append("World");
		System.out.println(myList.toString());
		myList.delete(3);
		System.out.println(myList.toString());
		myList.delete(0);
		myList.delete(0);
		myList.delete(0);
		myList.delete(0);
		System.out.println(myList.toString());
		myList.append("ff");
		myList.insert(0,"Hello world!");
		myList.insert(1,"What");
		myList.insert(2,"asd");
		System.out.println(myList.toString());
		myList.delete(1);
		System.out.println(myList.toString());
		myList.set(0, "Bye World!");
		System.out.println(myList.toString());
		System.out.println(myList.get(myList.size()-1));
		myList.delete("asd");
		System.out.println(myList.toString());
		System.out.println(myList.contains("ff"));
		myList.set(1, "asd");
		System.out.println(myList.toString());*/
		
		MyNode stuff = new MyNode("Hello");
		stuff.setNext(new MyNode("World"));
		Iterator iter = new Iterator(stuff);
		System.out.println(iter.next().getObject().toString());
		System.out.println(iter.remove().getObject().toString());
	}
}