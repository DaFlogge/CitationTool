package plugins;

public class ExtractionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7394965898283373772L;

	private String step = "unknown";

	public ExtractionException(String string) {
		this.step = string;
	}

	public String getStep() {
		return step;
	}
}
