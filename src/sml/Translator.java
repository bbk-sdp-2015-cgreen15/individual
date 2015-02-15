package sml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

	// word + line is the part of the current line that's not yet processed
	// word has no whitespace
	// If word and line are not empty, line begins with whitespace
	private String line = "";
	private Labels labels; // The labels of the program being translated
	private ArrayList<Instruction> program; // The program to be created
	private String fileName; // source file of SML code

	private static final String SRC = "src";

	public Translator(String fileName) {
		
		this.fileName = SRC + "/" + fileName;
	}

	// translate the small program in the file into lab (the labels) and
	// prog (the program)
	// return "no errors were detected"
	public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {
		
		try (Scanner sc = new Scanner(new File(fileName))) {
			// Scanner attached to the file chosen by the user
			labels = lab;
			labels.reset();
			program = prog;
			program.clear();

			try {
				line = sc.nextLine();
			} catch (NoSuchElementException ioE) {
				return false;
			}

			// Each iteration processes line and reads the next line into line
			while (line != null) {
				
				// Store the label in label
				
				String label = scan();
				
				if (label.length() > 0 && label.charAt(0) != '#') {	// Allow comment lines starting with #
					Instruction ins = getInstruction(label);
					if (ins != null) {
						labels.addLabel(label);
						program.add(ins);
					}
				}

				try {
					line = sc.nextLine();
				} catch (NoSuchElementException ioE) {
					return false;
				}
			}
		} catch (IOException ioE) {
			System.out.println("File: IO error " + ioE.getMessage());
			return false;
		}
		return true;
	}

	// line should consist of an MML instruction, with its label already
	// removed. Translate line into an instruction with label label
	// and return the instruction
	public Instruction getInstruction(String label) {	
		
		// the "Instruction" part means that the getInstruction function 
		// returns an object of the class type Instruction 
		
		int s1; // Possible operand 1 (register id ) of the instruction
		int s2; // Possible operand 2 (register id ) of the instruction
		int r;	// receiving register (register id) 
		int x;	// raw integer value 
		String l2; // label to jump to for the bnz instruction
		// Class<?> instruction;
		
		if (line.equals(""))
			return null;

		String ins = scan();	// get next bit of line, e.g. instruction
		
		
		//********************************************************************************
		// USING REFLECTION
		
		// I Couldn't get the reflection code to work ...
		
		// Reflection 
		// Get the constructor from the "default" ( = first public ) constructor
		// Use reflection to read the list of constructor parameter types 
		// pass in the appropriate number and types of parameters
		
		// Or we could get the private fields and assume that we need to add a 
		// string label on at the front for the opcode  
		
//		String instClass = ins.substring(0, 1).toUpperCase() + ins.substring(1) + "Instruction";
		
//		try {
//			instruction = Class.forName(instClass);
//			Field[] fields = instruction.getFields();
//			
//		} catch (ClassNotFoundException e) {
//			return null;
//			// Instruction type not found
//		}
		
		
		
		
		
		
		
		
		
		
		
		

		// Switch NOT NEEDED if reflection were working:
		
		//********************************************************************************
		// USING SWITCH
		
		// Each Class below invokes the superclass' constructor to set label and op
		switch (ins) {
		case "add":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new AddInstruction(label, r, s1, s2);
		case "lin":
			r = scanInt();
			x = scanInt();
			return new LinInstruction(label, r, x);
		case "sub":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new SubInstruction(label, r, s1, s2);
		case "mul":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new MulInstruction(label, r, s1, s2);
		case "div":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new DivInstruction(label, r, s1, s2);
		case "out":
			r = scanInt();
			return new OutInstruction(label, r);
		case "bnz":
			r = scanInt();
			l2 = scan();
			return new BnzInstruction(label, r, l2);
		}

		return null;
	}

	/*
	 * Return the first word of line and remove it from line. If there is no
	 * word, return ""
	 */
	private String scan() {
		line = line.trim();
		if (line.length() == 0)
			return "";

		int i = 0;
		while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
			i = i + 1;
		}
		String word = line.substring(0, i);
		line = line.substring(i);
		return word;
	}

	// Return the first word of line as an integer. If there is
	// any error, return the maximum int
	private int scanInt() {
		String word = scan();
		if (word.length() == 0) {
			return Integer.MAX_VALUE;
		}

		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}
}