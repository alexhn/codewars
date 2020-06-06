package codewars;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Equation {

	public static BigInteger differentiate(String equation, long x) {
		String current = "";
		List<Term> terms = new ArrayList<>();
		boolean found = false;
		for (char c : equation.toCharArray()) {
			if (c == ' ') {
				continue;
			}
 			if ((c == '+' || c == '-') && found) {
				terms.add(parseTerm(current));
				current = "" + c;
			} else {
				found = true;
				current = current + c;
			}
		}
		if (current.length() > 0) {
			terms.add(parseTerm(current));
		}
		Collections.sort(terms, new Comparator<Term>() {
			@Override
			public int compare(Term o1, Term o2) {
				return Long.compare(o1.power, o2.power);
			}
		});
		terms = simplify(terms);
		terms = differentiate(terms);
		BigInteger ans = calc(terms, x);
		return ans;
	}
	
	private static BigInteger calc(List<Term> terms, long x) {
		BigInteger ret = BigInteger.ZERO;
		for (Term t : terms) {
			BigInteger sum = BigInteger.valueOf(t.coef);
			sum = sum.multiply(BigInteger.valueOf(x).pow(t.power));
			ret = ret.add(sum);
		}
		return ret;
	}

	private static List<Term> differentiate(List<Term> terms) {
		List<Term> ret = new ArrayList<Term>();
		for (Term t : terms) {
			if (t.power > 0) {
				ret.add(new Term(t.coef * t.power, t.power - 1));
			}
		}
		return ret;
	}

	private static List<Term> simplify(List<Term> terms) {
		int i = 0;
		List<Term> ret = new ArrayList<>();
		while (i < terms.size()) {
			long totalCoef = terms.get(i).coef;
			int j = i + 1;
			while (j < terms.size() && terms.get(j).power == terms.get(i).power) {
				totalCoef += terms.get(j).coef;
				j++;
			}
			ret.add(new Term(totalCoef, terms.get(i).power));
			i = j;
		}
		return ret;
	}

	private static Term parseTerm(String str) {
		Term term = new Term(0, 0);
		if (str.contains("x")) {
			if (str.contains("^")) {
				String left = str.substring(0, str.indexOf('^'));
				String right = str.substring(str.indexOf('^') + 1);
				term.power = Integer.parseInt(right);
				String coefStr = left.substring(0, left.length() - 1);
				if (coefStr.length() == 1) {
					term.coef = coefStr.charAt(0) == '+' ? 1 : -1;
				} else if (coefStr.length() == 0) {
					term.coef = 1;
				} else {
					term.coef = Integer.parseInt(coefStr);
				}
			} else {
				term.power = 1;
				String coefStr = str.substring(0, str.indexOf('x'));
				if (coefStr.length() == 1) {
					term.coef = coefStr.charAt(0) == '+' ? 1 : -1;
				} else if (coefStr.length() == 0) {
					term.coef = 1;
				} else {
					term.coef = Integer.parseInt(coefStr);
				}
			}
		} else {
			term.coef = Integer.parseInt(str);
			term.power = 0;
		}
		return term;
	}

	static class Term {
		long coef;
		int power;
		public String toString() {
			return "coef = " + coef + ", power= "+ power;
		}
		public Term(long coef, int power) {
			this.coef = coef;
			this.power = power;
		}
	}

	public static void main(String[] args) {
		System.out.println(differentiate("x^2-x", 3));
	}
}
