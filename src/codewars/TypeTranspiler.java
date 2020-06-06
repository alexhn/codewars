package codewars;

import java.util.Arrays;

public class TypeTranspiler {
	
	private String originalString;
	
	public TypeTranspiler(String s) {
		this.originalString = s;
	}
    
    public String transpile() {
        return transpile(createTokens(this.originalString));
    }

    private String transpile(String[] tokens) {
    	System.out.println(Arrays.toString(tokens));
		return null;
	}

	private String[] createTokens(String s) {
		return s.split(" ");
	}

	public static void main(String[] args) {
    	System.out.println(new TypeTranspiler("(List<Int> -> B) -> C").transpile());
    }
    
}
