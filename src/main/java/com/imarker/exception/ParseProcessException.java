package com.imarker.exception;

import com.imarker.utils.L;

public class ParseProcessException extends IMarkerException {

	private static final long serialVersionUID = -1407158001403173319L;
	
	private static final String ERROR_MSG = "Parse ParseObject to Object error.";

	public ParseProcessException() {
		L.e(ERROR_MSG);
	}

	public ParseProcessException(String detailMessage) {
		super(detailMessage);
		L.e(ERROR_MSG);
	}

	public ParseProcessException(Throwable throwable) {
		super(throwable);
		L.e(ERROR_MSG, throwable);
	}

	public ParseProcessException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		L.e(ERROR_MSG, throwable);
	}

}
