package br.com.cannoni.jagent.service.exception;

/**
 * @author patrizio
 * @since 03/05/2016
 */
public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1649256069946982567L;

	public ServiceException(String message, Throwable e) {
		super(message, e);
	}
}
