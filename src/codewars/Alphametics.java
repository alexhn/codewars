package codewars;

import java.util.List;
import java.util.stream.Collectors;

public class Alphametics {

	public Alphametics(String s) {
		List<Character> chars = s.chars().mapToObj(i -> (char)i)
				.filter(c -> c >= 'A' && c <= 'Z')
				.distinct().collect(Collectors.toList());
		solve(chars, 0, s);
	}

	private void solve(List<Character> chars,  int k, String s) {
		if (k == chars.size()) {
			
		} else {
			
		}
	}

	public String solve() {
		return null;
	}

	public static void main(String[] args) {
		System.out.println(new Alphametics("ABC + DEF = AAA"));
	}

}