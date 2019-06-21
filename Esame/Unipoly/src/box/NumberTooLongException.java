package box;

public class NumberTooLongException extends Exception{
	private static final String ERROR_MESSAGE_DEFAULT = "Il numero deve essere compreso tra 1 e un milione inclusi";
	//auto generated serial version default
	private static final long serialVersionUID = 1L;

	public NumberTooLongException(String errorMessage) {
		super(errorMessage);
	}
	public NumberTooLongException() {
		super(ERROR_MESSAGE_DEFAULT);
	}
}
