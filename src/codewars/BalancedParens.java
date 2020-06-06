package codewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BalancedParens {

	public static List<String> balancedParens (int n) {
		if (n == 0) {
			return Arrays.asList(new String[] {""});
		}
		List<String> subTask = balancedParens(n - 1);
		Set<String> ret = new HashSet<>();
		for (String sub : subTask) {
			ret.add("(" + sub + ")");
			ret.add("()" + sub);
			ret.add(sub + "()");
		}
		List<String> ans = new ArrayList<>(ret);
		Collections.sort(ans);
		Collections.reverse(ans);
		return ans;
    }
	
	public static void main(String[] args) {
		System.out.println(balancedParens(3));
	}
	
}
