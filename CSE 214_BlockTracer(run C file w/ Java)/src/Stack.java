/**
 * 
 * @author Joohak Lee 108699180 Recitation 01
 *
 *<code>Stack </code> that is used for <code>BlockTracer</code>.
 *This class implements codes from <code>Block</code>.
 *<code>Stack</code> contains several <code>Block</code>.
 */

import java.util.ArrayList;

public class Stack {
	private ArrayList<Block> stack;
	private int counter;
	/**
	 * creates a stack object with no parameter
	 */
	public Stack() {
		counter = -1;
		stack = new ArrayList<Block>();
	}
	/**
	 * remove the block from the stack
	 * <b>Preconditions:</b>
	 * the stack should not be empty;
	 */
	public void pop() {
		try {
			stack.remove(stack.size() - 1);
			counter --;
		} catch (Exception e) {
			System.out.println("empty stack error");
		}
	}
	/**
	 * add the block into the stack
	 * @param block
	 * Block object that has variables
	 */
	public void push(Block block) {
		stack.add(block);
		counter ++;
	}
	/**
	 * retrieves how many blocks are in the array
	 * @return
	 * the number of blocks in the array in integer
	 */
	public int getSize() {
		return counter+1;
	}
	/**
	 * retrieves the currentBlock
	 * @return
	 * the currentBlock in block object
	 */
	public Block getCurrnetStack() {
		return stack.get(counter);
	}
	/**
	 * retrieves the stack
	 * @return
	 * the stack in arrayList
	 */
	public ArrayList<Block> getStack(){
		return stack;
	}
}
