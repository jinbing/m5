package math;

import java.util.Stack;

public class MatrixStack {
	private Stack<Matrix4f> stack;
	private Matrix4f current;
	
	public MatrixStack() {
		current = new Matrix4f();
		current.toIdentity();
		stack = new Stack<>();
	}
	
	public MatrixStack(Matrix4f mat) {
		current = mat;
		stack = new Stack<>();
	}
	
	public Matrix4f getTop() {
		return current;
	}
	
	public void setTop(Matrix4f mat) {
		current = mat;
	}
	
	public void push() {
		stack.push(current);
		current = new Matrix4f(current);
	}
	
	public void pop() {
		current = stack.pop();
	}
}

