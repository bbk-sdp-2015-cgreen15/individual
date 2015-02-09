package sml;

/**
 * This class adds one operand to another and puts result in a register
 * 
 * @author Craig Greenhouse
 */

public class AddInstruction extends Instruction {

	private int result;
	private int op1;
	private int op2;

	protected AddInstruction(String label, String op) {
		super(label, op);
	}

	public AddInstruction(String label, int result, int op1, int op2) {
		this(label, "add");	// Invoke Super Constructor
		
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 + value2);
	}

	@Override
	public String toString() {
		return super.toString() + " " + op1 + " + " + op2 + " to " + result;
	}
}
