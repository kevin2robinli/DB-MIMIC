package exception;

public class DataOverMaxLengthException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	//Exception to handle STB, TITLE, PROVIDER which is over 64 chars
	public DataOverMaxLengthException(String message) {
		super(message);
	}

}

