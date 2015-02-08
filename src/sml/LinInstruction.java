package sml;

/**
 * This class loads a value into a register (like "LDA" in the olden days of assembly language ;-) 
 * 
 * @author KLM / Craig Greenhouse
 */

public class LinInstruction extends Instruction {
	private int register;
	private int value;

	public LinInstruction(String label, String opcode) {
		super(label, opcode);
	}

	public LinInstruction(String label, int register, int value) {
		super(label, "lin");
		this.register = register;
		this.value = value;
	}

	@Override
	public void execute(Machine m) {
		m.getRegisters().setRegister(register, value);
	}

	@Override
	public String toString() {
		return super.toString() + " register " + register + " value is " + value;
	}
}
