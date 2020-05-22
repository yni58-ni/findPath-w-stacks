
/**
 * 
 * @author Nicole Ni
 * @param stack           this is an array that stores the data items of the
 *                        stack
 * @param top             stores the position of the last data item
 * @param initialCapacity initial size of the array stack
 * @param sizeIncrease    the size of array will increase by this amount
 * @param sizeDecrease    the size of array will decrease by this amount
 */
public class ArrayStack<T> implements ArrayStackADT<T> {
	private T[] stack;
	private int top;
	private int initialCapacity;
	private int sizeIncrease;
	private int sizeDecrease;

	public ArrayStack(int initialCap, int sizeInc, int sizeDec) {
		this.top = -1;
		this.initialCapacity = initialCap;
		this.sizeIncrease = sizeInc;
		this.sizeDecrease = sizeDec;
		stack = (T[]) (new Object[initialCap]);
	}

	/**
	 * check if the stack is full, and add dataItem to the top of the stack and
	 * update the value of top
	 * 
	 * @param dataItem new value which needs to be added
	 */
	public void push(T dataItem) {
		if (top == stack.length - 1) {
			expandSize();
		}
		top++;
		stack[top] = dataItem;
	}

	/**
	 * Remove and returns the data items at the top of the stack and updates the
	 * value of top Throw an EmptyStackException if the stack is empty
	 */
	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("The Stack is empty.");
		}
		T topElement = stack[top];
		stack[top] = null;
		top--;
		if (size() < (stack.length / 4) && stack.length > initialCapacity) {
			T[] newArray = (T[]) (new Object[length() - sizeDecrease]);
			for (int i = 0; i < newArray.length; i++) {
				newArray[i] = stack[i];
			}
			stack = newArray;
		}
		return topElement;
	}

	/**
	 * return the data item at the top of the stack without removing it throw an
	 * EmptyStackException if the stack is empty
	 */
	public T peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("The Stack is empty.");
		} else
			return stack[top];
	}

	/**
	 * return ture if the stack is empty otherwisw return false
	 */
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		} else
			return false;
	}

	/**
	 * returns the number of data items in the stack
	 */
	public int size() {
		return (top + 1);
	}

	/**
	 * return the length of the array stack
	 */
	public int length() {
		return (stack.length);
	}

	/**
	 * return a string representation of the stack
	 */
	public String toString() {
		String result = "Stack:";
		String[] newList;
		for (int i = 0; i < top; i++)
			result = result + " " + stack[i].toString() + ",";
		String last = " " + stack[top].toString();
		return result + last;
	}

	/**
	 * Increase the size of array by sizeIncrease.
	 */
	private void expandSize() {
		T[] newStack = (T[]) (new Object[stack.length + sizeIncrease]);
		for (int i = 0; i < stack.length; i++) {
			newStack[i] = stack[i];
		}
		stack = newStack;
	}
}
