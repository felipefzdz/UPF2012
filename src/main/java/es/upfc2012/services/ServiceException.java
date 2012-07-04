package es.upfc2012.services;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 5713688892328646720L;

	public ServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ServiceException(final Throwable cause) {
		super(cause);
	}

}
