package codewars;

import java.math.BigInteger;

public class Fibonacci {

	public static BigInteger fib(BigInteger f) {
		int n = f.intValue();
		if (n >= 0) {
			BigInteger prevprev = BigInteger.ZERO;
			BigInteger prev = BigInteger.ONE;
			if (n == 0) {
				return BigInteger.ZERO;
			}
			if (n == 1) {
				return BigInteger.ONE;
			}
			BigInteger cur = null;
			for (int i = 2; i <= n; i++) {
				cur = prevprev.add(prev);
				prevprev = prev;
				prev = cur;
			}
			return cur;
		} else {
			BigInteger prevprev = BigInteger.ONE;
			BigInteger prev = BigInteger.ZERO;
			BigInteger cur = null;
			for (int i = -1; i >= n; i--) {
				cur = prevprev.subtract(prev);
				prevprev = prev;
				prev = cur;
			}
			return cur;
		}
	}

	public static void main(String[] args) {
		System.out.println(fib(new BigInteger("" + 1109782)));
	}
}
