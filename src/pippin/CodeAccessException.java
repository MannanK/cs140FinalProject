package pippin;

public class CodeAccessException extends RuntimeException {

	private static final long serialVersionUID = -5029740301720987922L;
	
	public CodeAccessException() {
        super();
    }
    /**
     * Preferred constructor that sets the inherited message field
     * of the exception object
     * @param arg0 message passed by the exception that was thrown
     */
    public CodeAccessException(String arg0) {
        super(arg0);
    }
}