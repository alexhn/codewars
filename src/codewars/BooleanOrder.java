package codewars;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class BooleanOrder {

	private BigInteger ans;
	
	private Map<String, BigInteger> mem = new HashMap<>();
	
    public BooleanOrder(final String operands, final String operators) {
    	mem.clear();
    	this.ans = calc(operands, operators, true);
    }

    private BigInteger calc(String operands, String operators, boolean expectedValue) {
    	if (operands.length() == 1) {
    		char op = operands.charAt(0);
    		if (op == 't') {
    			return expectedValue == true ? BigInteger.ONE : BigInteger.ZERO;
    		} else {
    			return expectedValue == false ? BigInteger.ONE : BigInteger.ZERO;
    		}
    	}
    	String key = operands + '.' + operators + '.' + expectedValue;
    	if (mem.containsKey(key)) {
    		return mem.get(key);
    	}
    	int n = operands.length();
    	BigInteger ret = BigInteger.ZERO;
    	for (int i = 0; i < n - 1; i++) {
    		// put operator after i-th
    		char operator = operators.charAt(i);
    		String leftPart = operands.substring(0, i + 1);
    		String leftPartOperators = operators.substring(0, i);
    		String rightPart = operands.substring(i + 1);
    		String rightPartOperators = operators.substring(i + 1);
    		BigInteger leftCountTrue = calc(leftPart, leftPartOperators, true);
			BigInteger rightCountTrue = calc(rightPart, rightPartOperators, true);
			BigInteger leftCountFalse = calc(leftPart, leftPartOperators, false);
			BigInteger rightCountFalse = calc(rightPart, rightPartOperators, false);
    		if (operator == '&') {
    			if (expectedValue == true) {
    				ret = ret.add(leftCountTrue.multiply(rightCountTrue));
    			} else {
    				ret = ret.add(leftCountTrue.multiply(rightCountFalse));
    				ret = ret.add(leftCountFalse.multiply(rightCountTrue));
    				ret = ret.add(leftCountFalse.multiply(rightCountFalse));
    			}
    		} else if (operator == '|') {
    			if (expectedValue == true) {
        			ret = ret.add(leftCountTrue.multiply(rightCountTrue.add(rightCountFalse)));
        			ret = ret.add(leftCountFalse.multiply(rightCountTrue));
    			} else {
    				ret = ret.add(leftCountFalse.multiply(rightCountFalse));
    			}
    		} else if (operator == '^') {
    			if (expectedValue == true) {
	    			ret = ret.add(leftCountTrue.multiply(rightCountFalse));
	    			ret = ret.add(leftCountFalse.multiply(rightCountTrue));
    			} else {
    				ret = ret.add(leftCountTrue.multiply(rightCountTrue));
    				ret = ret.add(leftCountFalse.multiply(rightCountFalse));
    				
    			}
    		}
    	}
    	mem.put(key, ret);
		return ret;
	}

	public BigInteger solve() {
        return ans;
    }
	
	public static void main(String[] args) {
		System.out.println(new BooleanOrder("ttftfftf","|&^&&||").solve());
	}
    
}
