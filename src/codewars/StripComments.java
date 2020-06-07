package codewars;

public class StripComments {

	public static String stripComments(String text, String[] commentSymbols) {
		String[] lines = text.split("\n");
		StringBuilder ret = new StringBuilder();
		for (int k = 0; k < lines.length; k++) {
			StringBuilder line = new StringBuilder();
			for (char c : lines[k].toCharArray()) {
				boolean isComment = false;
				for (String symbol : commentSymbols) {
					if (symbol.charAt(0) == c) {
						isComment = true;
					}
				}
				if (isComment) {
					break;
				} else {
					line.append(c);
				}
			}
			String newLine = line.toString();
			int i = newLine.length();
			while (i > 0 && newLine.charAt(i - 1) == ' ') {
				i--;
			}
			newLine = newLine.substring(0, i);
			ret.append(newLine);
			if (k < lines.length - 1) {
				ret.append("\n");
			}
		}
		return ret.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(stripComments("apples, pears # and bananas\\ngrapes\\nbananas !apples", new String[] {"#", "!"}));
	}
	
}
