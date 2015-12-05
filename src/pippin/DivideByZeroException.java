package pippin;

public class DivideByZeroException extends RuntimeException {
	
	private static final long serialVersionUID = -3870343547745128405L;

    public DivideByZeroException() {
        super();
    }
    /**
     * Preferred constructor that sets the inherited message field
     * of the exception object
     * @param arg0 message passed by the exception that was thrown
     */
    public DivideByZeroException(String arg0) {
        super(arg0);
    }
}