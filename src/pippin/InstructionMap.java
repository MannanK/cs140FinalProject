package pippin;

import java.util.Map;
import java.util.TreeMap;

public class InstructionMap {
	public static Map<String, Integer> opcode = new TreeMap<>();
	public static Map<Integer, String> mnemonics = new TreeMap<>();

	static {
		opcode.put("NOP", 0);
		opcode.put("LODI", 0x1);
		opcode.put("LOD", 0x2);
		opcode.put("LODN", 0x3);
		opcode.put("STO", 0x4);
		opcode.put("STON", 0x5);
		opcode.put("JMPI", 0x6);
		opcode.put("JUMP", 0x7);
		opcode.put("JMZI", 0x8);
		opcode.put("JMPZ", 0x9);
		opcode.put("ADDI", 0xA);
		opcode.put("ADD", 0xB);
		opcode.put("ADDN", 0xC);
		opcode.put("SUBI", 0xD);
		opcode.put("SUB", 0xE);
		opcode.put("SUBN", 0xF);
		opcode.put("MULI", 0x10);
		opcode.put("MUL", 0x11);
		opcode.put("MULN", 0x12);
		opcode.put("DIVI", 0x13);
		opcode.put("DIV", 0x14);
		opcode.put("DIVN", 0x15);
		opcode.put("ANDI", 0x16);
		opcode.put("AND", 0x17);
		opcode.put("NOT", 0x18);
		opcode.put("CMPL", 0x19);
		opcode.put("CMPZ", 0x1A);
		opcode.put("COPY", 0x1D);
		opcode.put("CPYN", 0x1E);
		opcode.put("HALT", 0x1F);

		for(String str : opcode.keySet()) {
			mnemonics.put(opcode.get(str), str);
		}
	}
}