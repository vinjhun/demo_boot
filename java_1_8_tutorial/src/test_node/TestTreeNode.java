package test_node;

public class TestTreeNode {
	
	public static void main(String[] args) {
		
	}
	
	public static class Node
	{
		public int value;
		public Node leftNode;
		public Node rightNode;
		
		public Node(int value,Node leftNode, Node rightNode)
		{
			this.value =  value;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
		}
		
	}
}

