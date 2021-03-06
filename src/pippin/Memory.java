package pippin;

public class Memory {
	public final static int DATA_SIZE = 512;
	private int[] data = new int[DATA_SIZE];
	private int changedIndex = -1;

	public int getData(int index) {
		return data[index];
	}

	public void setData(int index, int value) {
		data[index] = value;
		changedIndex = index;
	}

	int[] getData() {
		return data;
	}

	public int getChangedIndex() {
		return changedIndex;
	}
	
	public void clear() {
        for(int i = 0; i < DATA_SIZE; i++) {
            data[i] = 0;
        }
        changedIndex = -1;
    }
}
