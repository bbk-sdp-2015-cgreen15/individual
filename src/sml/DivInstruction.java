package sml;


/**
 * This class multiplies two operands together and puts result in a register
 * 
 * @author Craig Greenhouse
 */

public class DivInstruction extends Instruction {

	private int result;
	private int op1;
	private int op2;

	public DivInstruction(String label, String op) {
		// Invoke the superclass Instruction's Constructor 
		super(label, op);
	}

	
	public DivInstruction(String label, int result, int op1, int op2) {
		// Use Polymorphism for this class's constructor
		this(label, "div"); 	// Invoke Super Constructor
		this.result = result;
		this.op1 = op1;
		this.op2 = op2;
	}

	@Override
	public void execute(Machine m) {
		int value1 = m.getRegisters().getRegister(op1);
		int value2 = m.getRegisters().getRegister(op2);
		m.getRegisters().setRegister(result, value1 / value2);
	}

	@Override
	public String toString() {
		return super.toString() + " " + op1 + " / " + op2 + " to " + result;
	}
	
}
