package codewars;

import java.util.Arrays;
import java.util.Comparator;

public class SumOfIntervals {

	public static int sumIntervals(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		Integer[] indexes = new Integer[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			indexes[i] = i;
		}
		Arrays.sort(indexes, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (intervals[o1][0] < intervals[o2][0]) {
					return -1;
				}
				if (intervals[o1][0] > intervals[o2][0]) {
					return 1;
				}
				return 0;
			}
		});
		int i = 0;
		int sum = 0;
		while (i < indexes.length) {
			int[] current = intervals[indexes[i]];
			int j = i + 1;
			int intervalLength = current[1] - current[0];
			while (j < intervals.length && intervals[indexes[j]][0] <= current[1]) {
				intervalLength = Math.max(intervalLength, intervals[indexes[j]][1] - current[0]);
				System.out.println(intervals[indexes[j]][1] + " -- " + current[0]);
				System.out.println("len = " + intervalLength);
				j++;
			}
			sum += intervalLength;
			i = j;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(sumIntervals(new int[][]{{1, 5}, {1, 6}, {5, 11}, {10, 20}, {16, 19},}));
	}
	
}
