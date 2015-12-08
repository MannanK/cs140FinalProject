
package pippin;

// Why isn't this built in?

/**
 * This class is intended to replicate some of the functionality found in Python's built-in 
 * range function.
 * @author Zach Ziccardi
 */
public class Range {
	
	// * Fields * //
	
	private int start;
	private int stop;
	
	// * Constructor * //
	
	/**
	 * This constructor initializes the fields with the values passed in.
	 * @param start an integer representing the lower value of a range
	 * @param stop an integer representing the higher value of a range
	 */
	public Range(int start, int stop) {
		this.start = start;
		this.stop = stop;
	}
	
	// * Method * //
	
	/**
	 * This method determines whether the parameter n is within the range represented by 
	 * the two fields.
	 * @param n an integer with no inherent meaning
	 * @return true if n is in the range, false otherwise
	 */
	public boolean contains(int n) {
		return (n >= start && n <= stop);
	}
	
}
