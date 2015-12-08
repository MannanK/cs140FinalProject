package pippin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Assembler {
	public static Set<String> noArgument = new TreeSet<String>();
	static {
		noArgument.add("HALT");
		noArgument.add("NOP");
		noArgument.add("NOT");
	}

	/**
	 * Method to assemble a file to its executable representation. If the input has errors
	 * one of the errors will be reported the StringBulder. The error may not be the first
	 * error in the code and will depend on the order in which instructions are checked.
	 * The line number of the error that is reported is returned as the value of the method.
	 * A return value of 0 indicates that the code had no errors and an output file was 
	 * produced and saved. If the input or output cannot be opened, the return value is -1.
	 * The unchecked exception IllegalArgumentException is thrown if the error parameter is
	 * null, since it would not be possible to provide error information about the source
	 * code.
	 * @param input the source assembly language file
	 * @param output the executable version of the program if the source program is correctly formatted
	 * @param error the StringBuilder to store the description of the error that is reported. 
	 * It will be empty if no error is found
	 * @return 0 if the source code is correct and the executable is saved, -1 if the input or
	 * output files cannot be opened, otherwise the line number of the reported error
	 */
	public static int assemble(File input, File output, StringBuilder error) {
		if(error == null) throw new IllegalArgumentException("Coding error: the error buffer is null");

		int retVal = 0;
		try (Scanner inp = new Scanner(input)) {
			int currentLine = 0;
			int firstBlankLine = 0;
			boolean blankFound = false;
			ArrayList<String> inputText = new ArrayList<>();

			while(inp.hasNextLine() && retVal == 0) {
				String line = inp.nextLine();
				currentLine++;

				if(line.trim().length() == 0 && blankFound == false) {
					firstBlankLine = currentLine;
					blankFound = true;
				} else if(line.trim().length() == 0 && blankFound == true) {
					error.append("Illegal blank line in the source file");
					retVal = firstBlankLine;        			
				} else if(line.charAt(0) == ' ' || line.charAt(0) == '\t') {
					error.append("Line starts with illegal white space");
					retVal = currentLine;
				} else {
					inputText.add(line.trim());
				}
			}

			ArrayList<String> inputCode = new ArrayList<>();
			ArrayList<String> inputData = new ArrayList<>();

			if(retVal == 0) {
				boolean readingCode = true;

				for(int i = 0; i < inputText.size() && retVal == 0; i++) {
					if(readingCode == true) {
						String line = inputText.get(i);

						if(line.equals("ENDCODE")) {
							readingCode = false;
						} else if(line.equalsIgnoreCase("ENDCODE")) {
							error.append("Error on line " + (i+1) + 
									": \"ENDCODE\" must be upper case");
							retVal = i+1;
						} else {
							inputCode.add(line);
						}
					} else {
						String line = inputText.get(i);
						inputData.add(line);
					}
				}

				ArrayList<String> outputCode = new ArrayList<>();

				for(int i = 0; i < inputCode.size() && retVal == 0; i++) {
					String[] parts = inputCode.get(i).split("\\s+");

					if(!InstructionMap.opcode.containsKey(parts[0].toUpperCase())) {
						error.append("Error on line " + (i+1) + ": illegal mnemonic");
						retVal = i+1;
					} else if(!parts[0].equals(parts[0].toUpperCase())) {
						error.append("Error on line " + (i+1) + ": mnemonic must be "
								+ "upper case");
						retVal = i+1;
					} else if(noArgument.contains(parts[0])) {
						if(parts.length > 1) {
							error.append("Error on line " + (i+1) + 
									": this mnemonic cannot take arguments");
							retVal = i+1;
						} else {
							outputCode.add(Integer.toString
									(InstructionMap.opcode.get(parts[0]), 16) + " 0");
						}
					} else {
						if(parts.length > 2) {
							error.append("Error on line " + (i+1) + 
									": this mnemonic has too many arguments");
							retVal = i+1;
						} else if(parts.length == 1) {
							error.append("Error on line " + (i+1) + 
									": this mnemonic is missing arguments");
							retVal = i+1;
						} else {
							try {
								int arg = Integer.parseInt(parts[1],16);
								outputCode.add(Integer.toString(InstructionMap.opcode.get(parts[0]), 16)
										+ " " + Integer.toString(arg, 16));
							} catch(NumberFormatException e) {
								error.append("Error on line " + (i+1) + 
										": argument is not a hex number");
								retVal = i+1;
							}
						}
					}
				}

				ArrayList<String> outputData = new ArrayList<>();

				for(int i = 0; i < inputData.size() && retVal == 0; i++) {
					String[] parts = inputData.get(i).split("\\s+");

					if(parts.length != 2) {
						error.append("Error on line " + (outputCode.size()+i+2) + 
								": this data is missing arguments");
						retVal = outputCode.size()+i+2;
					} else {
						try {
							int arg1 = Integer.parseInt(parts[0],16);
							int arg2 = Integer.parseInt(parts[1],16);
							
							outputData.add(Integer.toString(arg1, 16)
									+ " " + Integer.toString(arg2, 16));
						} catch(NumberFormatException e) {
							error.append("Error on line " + (outputCode.size()+i+2) + 
									": data integer(s) is not a hex number");
							retVal = outputCode.size()+i+2;
						}
					}
				}

				if(retVal == 0) {
					try (PrintWriter outp = new PrintWriter(output)){
						for(int i=0; i < outputCode.size(); i++) {
							outp.println(outputCode.get(i));
						}

						outp.println("-1");

						for(int i=0; i < outputData.size(); i++) {
							outp.println(outputData.get(i));
						}

						outp.close();
					} catch (FileNotFoundException e) {
						error.append("Error: Unable to write the assembled program to the output file");
						retVal = -1;
					}
				}
			}

		} catch (FileNotFoundException e) {
			error.append("Unable to open the assembled file");
			retVal = -1;
		}

		return retVal;
	}

	public static void main(String[] args) {
		StringBuilder error = new StringBuilder();
		int i = assemble(new File("factorial8.pasm"), new File("factorial8.pexe"), error);
		System.out.println(i + " " + error);
	}
}
