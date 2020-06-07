package codewars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Maze {
	
	static int[] di = new int[] {0, 0, 1, - 1};
	static int[] dj = new int[] {1, -1, 0, 0};
	static char[] dir = new char[] {'>', '<', 'v', '^'};

	public static List<Character> escape(char[][] maze) {
		int n = maze.length;
		int m = maze[0].length;
		int si = -1;
		int sj = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (maze[i][j] == '^' || maze[i][j] == '<' || maze[i][j] == '>' || maze[i][j] == 'v') {
					si = i;
					sj = j;
				}
			}
		}
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(si, sj));
		boolean[][] visited = new boolean[n][m];
		Point[][] prev = new Point[n][m];
		visited[si][sj] = true;
		int ei = -1;
		int ej = -1;
		while (!q.isEmpty()) {
			Point current = q.poll();
			for (int i = 0; i < 4; i++) {
				int ni = current.i + di[i];
				int nj = current.j + dj[i];
				if (ni >= 0 && nj >= 0 && ni < n && nj < m) {
					if (!visited[ni][nj] && maze[ni][nj] == ' ') {
						visited[ni][nj] = true;
						q.add(new Point(ni, nj));
						prev[ni][nj] = current;
					}
				} else {
					ei = current.i;
					ej = current.j;
					break;
				}
			}
		}
		if (ei == -1) {
			return Collections.emptyList();
		}
		List<Point> path = new ArrayList<>();
		path.add(new Point(ei, ej));
		while (prev[ei][ej] != null) {
			Point pr = prev[ei][ej];
			ei = pr.i;
			ej = pr.j;
			path.add(pr);
		}
		Collections.reverse(path);
		int currentDirection = findDir(maze[si][sj]);
		List<Character> ret = new ArrayList<>();
		for (int i = 1; i < path.size(); i++) {
			int dirRequired = findDir(path.get(i - 1), path.get(i));
			if (dirRequired == currentDirection) {
				ret.add('F');
			} else {
				char rotation = findRotation(dir[currentDirection], dir[dirRequired]);
				ret.add(rotation);
				ret.add('F');
				currentDirection = dirRequired;
			}
		}
		return ret;
	}
	
	private static char findRotation(int currentDirection, int requiredDirection) {
		if (currentDirection == '^') {
			if (requiredDirection == 'v') {
				return 'B';
			}
			if (requiredDirection == '<') {
				return 'L';
			}
			if (requiredDirection == '>') {
				return 'R';
			}
		} else if (currentDirection == 'v') {
			if (requiredDirection == '^') {
				return 'B';
			}
			if (requiredDirection == '<') {
				return 'R';
			}
			if (requiredDirection == '>') {
				return 'L';
			}
		} else if (currentDirection == '<') {
			if (requiredDirection == '>') {
				return 'B';
			}
			if (requiredDirection == '^') {
				return 'R';
			}
			if (requiredDirection == 'v') {
				return 'L';
			}
		} else if (currentDirection == '>') {
			if (requiredDirection == '<') {
				return 'B';
			}
			if (requiredDirection == '^') {
				return 'L';
			}
			if (requiredDirection == 'v') {
				return 'R';
			}
		}
		return 0;
	}

	private static int findDir(char c) {
		for (int k = 0; k < 4; k++) {
			if (dir[k] == c) {
				return k;
			}
		}
		return -1;
	}

	private static int findDir(Point pfrom, Point pto) {
		int rdi = pto.i - pfrom.i;
		int rdj = pto.j - pfrom.j;
		for (int k = 0; k < 4; k++) {
			if (di[k] == rdi && dj[k] == rdj) {
				return k;
			}
		}
		return -1;
	}

	static class Point {
		int i;
		int j;
		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) {
		char[][] maze = new char[][] {
	          "#########################################".toCharArray(),
	          "#<    #       #     #         # #   #   #".toCharArray(),
	          "##### # ##### # ### # # ##### # # # ### #".toCharArray(),
	          "# #   #   #   #   #   # #     #   #   # #".toCharArray(),
	          "# # # ### # ########### # ####### # # # #".toCharArray(),
	          "#   #   # # #       #   # #   #   # #   #".toCharArray(),
	          "####### # # # ##### # ### # # # #########".toCharArray(),
	          "#   #     # #     # #   #   # # #       #".toCharArray(),
	          "# # ####### ### ### ##### ### # ####### #".toCharArray(),
	          "# #             #   #     #   #   #   # #".toCharArray(),
	          "# ############### ### ##### ##### # # # #".toCharArray(),
	          "#               #     #   #   #   # #   #".toCharArray(),
	          "##### ####### # ######### # # # ### #####".toCharArray(),
	          "#   # #   #   # #         # # # #       #".toCharArray(),
	          "# # # # # # ### # # ####### # # ### ### #".toCharArray(),
	          "# # #   # # #     #   #     # #     #   #".toCharArray(),
	          "# # ##### # # ####### # ##### ####### # #".toCharArray(),
	          "# #     # # # #   # # #     # #       # #".toCharArray(),
	          "# ##### ### # ### # # ##### # # ### ### #".toCharArray(),
	          "#     #     #     #   #     #   #   #    ".toCharArray(),
	          "#########################################".toCharArray()
	        };
	    System.out.println(new Maze().escape(maze));
	        
	}
	
}
