package pippin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Loader {
    public static String load(MachineModel model, File file) {
        try (Scanner input = new Scanner(file)) {
        	boolean readingCode = true;
        	
        	while(input.hasNextLine()) {
        		String line = input.nextLine();
				Scanner parser = new Scanner(line);
				int int1 = parser.nextInt(16);
				
				if(readingCode == true && int1 == -1) {
					readingCode = false;
				} else if(readingCode == true) {
					int int2 = parser.nextInt(16);
					model.setCode(int1, int2);
				} else {
					int int2 = parser.nextInt(16);
					model.setData(int1, int2);
				}
				
				parser.close();
        	}
        	
            return "success";
            
        } catch (ArrayIndexOutOfBoundsException e) {
            return("Array Index " + e.getMessage());
        } catch (NoSuchElementException e) {
            return("NoSuchElementException");
        } catch (FileNotFoundException e1) {
            return("File " + file.getName() + " Not Found");
        }
    }
    // this main is only for initial testing and can be deleted after the load works correctly
    public static void main(String[] args) {
        MachineModel m = new MachineModel();
        System.out.println(Loader.load(m, new File("factorial8.pexe")));
        for(int i = 0; i < 12; i++) {
            System.out.println(m.getCode().getText(i));         
        }
        System.out.println(0 + " " + m.getData(0));
    }
}
