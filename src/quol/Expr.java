package quol;

public abstract class Expr {
	static class Binary extends Expr {
		Binary(Expr left, Token operator, Expr right) {
			this.left = left;
			this.operator = operator;
			this.right = right;
		}
		
		final Expr left;
		final Token operator;
		final Expr right;
	}
	
	static class Unary extends Expr {
		Unary(Token operator, Expr expression) {
			this.operator = operator;
			this.expression = expression;
		}
		
		final Token operator;
		final Expr expression;
	}
	
	static class Group extends Expr {
		Group(Token left, Expr expression, Token right) {
			this.left = left;
			this.expression = expression;
			this.right = right;
		}
		
		final Token left;
		final Expr expression;
		final Token right;
	}
	
}
