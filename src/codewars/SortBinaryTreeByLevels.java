package codewars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SortBinaryTreeByLevels {

	public static List<Integer> treeByLevels(Node root) {
		if (root == null) {
			return Collections.emptyList();
		}
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		List<Integer> ret = new ArrayList<>();
		while (!q.isEmpty()) {
			Node current = q.poll();
			ret.add(current.value);
			if (current.left != null) {
				q.add(current.left);
			}
			if (current.right != null) {
				q.add(current.right);
			}
		}
		return ret;
	}


	public class Node {
		public Node left;
		public Node right;
		public int value;

		public Node(Node l, Node r, int v) {
			left = l;
			right = r;
			value = v;
		}
	}

}
