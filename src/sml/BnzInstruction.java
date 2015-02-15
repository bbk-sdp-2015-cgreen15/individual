package sml;


/**
 * This class branches to the instruction specified by the specified label 
 * if the specified register is not zero
 * 
 * @author Craig Greenhouse
 */


public class BnzInstruction extends Instruction {

	private int value;
	private int register;
	private String l2;
	
	// "Hide" this constructor from reflection
	protected BnzInstruction(String label, String op) {
		super(label, op);
	}
		
	// Use a public constructor for reflection to get the parameters from the "Default" constructor
	public BnzInstruction(String label, int register, String l2) {
		this(label, "bnz"); // Invoke Super Constructor
		this.register = register;
		this.l2 = l2;
	}
	
	@Override
	public void execute(Machine m) {
		value = m.getRegisters().getRegister(register);
		
		// If value is NOT zero then we set the program counter to the value of the label l2
		if (value != 0 ) {
			m.jumpToLabel(l2);
		}
		
	}
	
	@Override
	public String toString() {
		return super.toString() + " register " + register + " value is " + value + " l2 is " + l2;
	}
}
