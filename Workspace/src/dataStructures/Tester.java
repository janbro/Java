package dataStructures;

import java.util.TreeSet;

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
		System.out.println(myList.toString());
		
		LinkedList list = new LinkedList();
		list.append("Hello");
		list.append("World");
		list.append("jhg");
		list.append("Wor8797ld");
		System.out.println(list.toString())
		Iterator iter = new Iterator(list.linkedList());
		System.out.println(iter.next().getObject().toString());
		System.out.println(iter.next().getObject().toString());
		iter.remove();
		System.out.println(iter.next().getObject().toString());
		System.out.println(iter.next().getObject().toString());
		System.out.println(list.toString());*/
		/*
		TreeNode node1 = new TreeNode(13123123);
		TreeNode node2 = new TreeNode(14);
		TreeNode node3 = new TreeNode(-29349,node1,node2);
		TreeNode node6 = new TreeNode(0);
		TreeNode node4 = new TreeNode(888,node3,node6);
		TreeNode node7 = new TreeNode(7);
		TreeNode head = new TreeNode(45,node4,node7);
		System.out.println(head.printPreOrder(head));//,Integer.MIN_VALUE));
		System.out.println("sdf:\n"+head.printInOrder(head));
		System.out.println("asd:\n"+head.printPostOrder(head));*/
		BSTreeNode<Integer> binary = new BSTreeNode<Integer>(2);
		binary.addNode(binary, new TreeNode<Integer>(7));
		binary.addNode(binary, new TreeNode<Integer>(4));
		binary.addNode(binary, new TreeNode<Integer>(90));
		binary.addNode(binary, new TreeNode<Integer>(9828));
		binary.addNode(binary, new TreeNode<Integer>(7));
		//binary.addNode(binary, node4);
		//binary.addNode(binary, node7);
		//binary.addNode(binary, node6);
		System.out.println("BinTree:\n"+binary.printInOrder(binary)+":\n"+binary.max(binary, Integer.MIN_VALUE));
		TreeSet mapA = new TreeSet();
		mapA.add(23);
		mapA.add(4);
		mapA.add(7);
		mapA.add(90);
		mapA.add(12);
		mapA.add(7);
		TreeSet mapB = new TreeSet();
		mapB.add(2);
		mapB.add(7);
		mapB.add(4);
		mapB.add(90);
		mapB.add(9828);
		mapB.add(7);
		System.out.println(mapB.toString());
		
	
	}
}