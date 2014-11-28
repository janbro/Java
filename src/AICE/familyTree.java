package AICE;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class familyTree {

	static ArrayList<String> family = new ArrayList<String>();

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		Tree family = new Tree("HEAD");
		String input;

		while(sc.hasNext()){
			String[] relation = sc.nextLine().split(" ");
			family.addNode(family.getRoot(), relation[0],relation[0]);
		}
		
		pw.flush();
	}
}
class Tree {
	private Node root;

	public Tree(String rootData) {
		root = new Node("rootData");
		root.data = rootData;
		root.children = new ArrayList<Node>();
	}

	public boolean addNode(Node node, String nodeData,String in){
		if(node.data.equals(nodeData)){
			node.children.add(new Node(in));
			return true;
		}
		for(Node child:node.children)
			return addNode(child, nodeData,in);
		return false;
	}

	public Node findNode(Node node,String nodeData){
		if(node.data.equals(nodeData)){
			return node;
		}
		for(Node child:node.children)
			return findNode(child, nodeData);
		return null;
	}
	
	public Node getRoot(){
		return root;
	}

	private class Node {
		public Node(String in) {
			// TODO Auto-generated constructor stub
			this.data = in;
		}
		private String data;
		private Node parent;
		private List<Node> children;
	}
}
