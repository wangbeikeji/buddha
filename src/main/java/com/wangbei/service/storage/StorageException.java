package com.wangbei.service.storage;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = 7574137948579672358L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
