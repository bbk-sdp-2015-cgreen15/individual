package sml;

/**
 * This class outputs the value of the specified register 
 * 
 * @author Craig Greenhouse
 */

public class OutInstruction extends Instruction {

	// private int result;
	private int op1;

	public OutInstruction(String label, String op) {
		super(label, op);
	}

	public OutInstruction(String label, int op1) {
		this(label, "out"); 	// Invoke Super Constructor
		this.op1 = op1;
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		System.out.println("(out instruction says) Value of register " + op1 + " is " + value1);
	}

	@Override
	public String toString() {
		return super.toString() + " " + op1 + " to console ";
	}
	
}
