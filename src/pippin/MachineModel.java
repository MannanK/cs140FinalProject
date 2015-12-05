package pippin;

import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

public class MachineModel extends Observable {
	public final Map<Integer, Instruction> INSTRUCTIONS = new TreeMap<>();
	private Registers cpu = new Registers();
	private Memory memory = new Memory();
	private boolean withGUI = false;
	private Code code = new Code();

	public class Registers {
		private int accumulator;
		private int programCounter;
	}

	public MachineModel() {
		this(false);
	}

	public MachineModel(boolean withGUI) {
		this.withGUI = withGUI;

		//INSTRUCTIONS entry for "NOP"
		INSTRUCTIONS.put(0x0, arg -> {
			cpu.programCounter++;
		});

		//INSTRUCTIONS entry for "LODI"
		INSTRUCTIONS.put(0x1, arg -> {
			cpu.accumulator = arg;
			cpu.programCounter++;
		});

		//INSTRUCTIONS entry for "LOD"
		INSTRUCTIONS.put(0x2, arg -> {
			INSTRUCTIONS.get(0x1).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "LODN"
		INSTRUCTIONS.put(0x3, arg -> {
			INSTRUCTIONS.get(0x2).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "STO"
		INSTRUCTIONS.put(0x4, arg -> {
			memory.setData(arg, cpu.accumulator);
			cpu.programCounter++;
		});

		//INSTRUCTIONS entry for "STON"
		INSTRUCTIONS.put(0x5, arg -> {
			INSTRUCTIONS.get(0x4).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "JMPI"
		INSTRUCTIONS.put(0x6, arg -> {
			cpu.programCounter = arg;
		});

		//INSTRUCTIONS entry for "JUMP"
		INSTRUCTIONS.put(0x7, arg -> {
			INSTRUCTIONS.get(0x6).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "JMZI"
		INSTRUCTIONS.put(0x8, arg -> {
			if(cpu.accumulator == 0) cpu.programCounter = arg;
			else {cpu.programCounter++;}
		});

		//INSTRUCTIONS entry for "JMPZ"
		INSTRUCTIONS.put(0x9, arg -> {
			INSTRUCTIONS.get(0x8).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "ADDI"
		INSTRUCTIONS.put(0xA, arg -> {
			cpu.accumulator += arg;
			cpu.programCounter++;
		});

		//INSTRUCTIONS entry for "ADD"
		INSTRUCTIONS.put(0xB, arg -> {
			INSTRUCTIONS.get(0xA).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "ADDN"
		INSTRUCTIONS.put(0xC, arg -> {
			INSTRUCTIONS.get(0xB).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "SUBI"
		INSTRUCTIONS.put(0xD, arg -> {
			cpu.accumulator -= arg;
			cpu.programCounter++;
		});

		//INSTRUCTIONS entry for "SUB"
		INSTRUCTIONS.put(0xE, arg -> {
			INSTRUCTIONS.get(0xD).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "SUBN"
		INSTRUCTIONS.put(0xF, arg -> {
			INSTRUCTIONS.get(0xE).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "MULI"
		INSTRUCTIONS.put(0x10, arg -> {
			cpu.accumulator *= arg;
			cpu.programCounter++;
		});

		//INSTRUCTIONS entry for "MUL"
		INSTRUCTIONS.put(0x11, arg -> {
			INSTRUCTIONS.get(0x10).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "MULN"
		INSTRUCTIONS.put(0x12, arg -> {
			INSTRUCTIONS.get(0x11).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "DIVI"
		INSTRUCTIONS.put(0x13, arg -> {
			if(arg == 0) throw new DivideByZeroException();
			else {
				cpu.accumulator /= arg;
				cpu.programCounter++;
			}
		});

		//INSTRUCTIONS entry for "DIV"
		INSTRUCTIONS.put(0x14, arg -> {
			INSTRUCTIONS.get(0x13).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "DIVN"
		INSTRUCTIONS.put(0x15, arg -> {
			INSTRUCTIONS.get(0x14).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "ANDI"
		INSTRUCTIONS.put(0x16, arg -> {
			if(arg != 0 && cpu.accumulator != 0) {
				cpu.accumulator = 1;
				cpu.programCounter++;
			}
			else {
				cpu.accumulator = 0;
				cpu.programCounter++;
			}
		});

		//INSTRUCTIONS entry for "AND"
		INSTRUCTIONS.put(0x17, arg -> {
			INSTRUCTIONS.get(0x16).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "NOT"
		INSTRUCTIONS.put(0x18, arg -> {
			if(cpu.accumulator == 0) {
				cpu.accumulator = 1;
				cpu.programCounter++;
			}
			else {
				cpu.accumulator = 0;
				cpu.programCounter++;
			}
		});

		//INSTRUCTIONS entry for "CMPL"
		INSTRUCTIONS.put(0x19, arg -> {
			if(memory.getData(arg) < 0) {
				cpu.accumulator = 1;
				cpu.programCounter++;
			}
			else {
				cpu.accumulator = 0;
				cpu.programCounter++;
			}
		});

		//INSTRUCTIONS entry for "CMPZ"
		INSTRUCTIONS.put(0x1A, arg -> {
			if(memory.getData(arg) == 0) {
				cpu.accumulator = 1;
				cpu.programCounter++;
			}
			else {
				cpu.accumulator = 0;
				cpu.programCounter++;
			}
		});

		//INSTRUCTION_MAP entry for "COPY"
		INSTRUCTIONS.put(0x1D,(arg) -> {
			copy(arg);
			cpu.programCounter++;
		});

		//INSTRUCTION_MAP entry for "CPYN"
		INSTRUCTIONS.put(0x1E, arg -> {
			INSTRUCTIONS.get(0x1D).execute(memory.getData(arg));
		});

		//INSTRUCTIONS entry for "HALT"
		INSTRUCTIONS.put(0x1F, arg -> {
			halt();
		});

	}

	public int getData(int index) {
		return memory.getData(index);
	}
	public void setData(int index, int value) {
		memory.setData(index, value);
	}
	public Instruction get(Object key) {
		return INSTRUCTIONS.get(key);
	}
	int[] getData() {
		return memory.getData();
	}
	int getProgramCounter() {
		return cpu.programCounter;
	}
	int getAccumulator() {
		return cpu.accumulator;
	}
	void setAccumulator(int i) {
		cpu.accumulator = i;
	}
	void setProgramCounter(int i) {
		cpu.programCounter = i;
	}

	void halt() {
		if(withGUI) {
			//code to come here later
		} else {
			System.exit(0);
		}
	}

	public int getChangedIndex() {
		return memory.getChangedIndex();
	}

	public void clearMemory() {
		memory.clear();
	}

	Code getCode() {
		return code;
	}

	public void setCode(int op, int arg) {
		code.setCode(op, arg);
	}

	/**
	 * Copies from one part of data memory to another, allowing for the source and target 
	 * locations to overlap. Takes values in memory in source range and copies them to 
	 * memory locations specified in target range. The exception IllegalArgumentException 
	 * is thrown if the source or target range of locations includes the indices arg thru
	 * arg+1, or if the source range or target ranges go out of the memory addresses.
	 * @param arg the initial memory location to look at, contains value for source, allows
	 * code to look up value for target location and length as well
	 */
	public void copy(int arg) {
		int source = memory.getData(arg);
		int target = memory.getData(arg+1);
		int length = memory.getData(arg+2);
		int sLength = source+length;
		int tLength = target+length;
		int maxAddress = Memory.DATA_SIZE-1;

		/*if((source >= arg && source <= arg+2) || (target >= arg && target <= arg+2)) {
			throw new IllegalArgumentException("The instruction would corrupt arg");*/

		if((arg >= source && arg <= sLength) || (arg+1 >= source && arg+1 <= sLength) 
				|| (arg+2 >= source && arg+2 <= sLength) || (arg >= target && 
				arg <= tLength) || (arg+1 >= target && arg+1 <= tLength) 
				|| (arg+2 >= target && arg+2 <= tLength)) {
			
			throw new IllegalArgumentException("The instruction would corrupt arg");

		} else if(source <= 0 || source >= maxAddress || sLength <= 0 || sLength >= maxAddress 
				|| target <= 0 || target >= maxAddress || tLength <= 0 || tLength >= maxAddress) {
			
			throw new IllegalArgumentException("The source range or target ranges go out"
					+ "of the memory addresses");
			
		} else if(source < target) {
			source = source+length-1;
			target = target+length-1;

			for(int i=0; i < length; i++) {
				memory.setData(target, memory.getData(source));
				target--;
				source--;
			}
		} else {
			for(int i=0; i < length; i++) {
				memory.setData(target, memory.getData(source));
				target++;
				source++;
			}
		}
	}
}
