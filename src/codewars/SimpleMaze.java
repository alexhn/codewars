package codewars;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleMaze {

	public static boolean hasExit(String[] maze) {
		int n = maze.length;
		int m = maze[0].length();
		int ki = -1;
		int kj = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (maze[i].charAt(j) == 'k') {
					if (ki != -1) {
						throw new RuntimeException();
					}
					ki = i;
					kj = j;
				}
			}
		}
		if (ki == -1) {
			throw new RuntimeException();
		}
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(ki, kj));
		boolean[][] visited = new boolean[n][m];
		visited[ki][kj] = true;
		int[] di = new int[] { 0, 0, 1, - 1};
		int[] dj = new int[] { 1, -1, 0, 0 };
		while (!q.isEmpty()) {
			Point current = q.poll();
			for (int k = 0; k < 4; k++) {
				int ni = current.i + di[k];
				int nj = current.j + dj[k];
				if (ni >= 0 && nj >=0 && ni < n && nj < m) {
					if (!visited[ni][nj] && maze[ni].charAt(nj) != '#') {
						visited[ni][nj] = true;
						q.add(new Point(ni, nj));
					}
				} else {
					return true;
				}
			}
		}
		return false;
	}
	
	static class Point {
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) {
		String[] maze = new String[] {"########",
            "# # ####",
            "# #k#  #",
            "# # # ##",
            "# # # ##",
            "#      #",
            "########"};
		System.out.println(hasExit(maze));
	}
	
}
