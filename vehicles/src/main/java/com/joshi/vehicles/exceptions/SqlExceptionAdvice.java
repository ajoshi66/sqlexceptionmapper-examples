package com.joshi.vehicles.exceptions;

import org.joshi.sqlexceptionmapper.SqlExceptionDetail;
import org.joshi.sqlexceptionmapper.SqlExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class SqlExceptionAdvice extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource msgs;
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<?> handleApplicationException(
			final DataAccessException dae, final HttpServletRequest req) {
		SqlExceptionDetail sed = SqlExceptionHandler.getSqlExceptionDetails(dae, SqlExceptionHandler.DBTYPE_MYSQL);
		SqlExceptionHandler handler = SqlExceptionHandler.getHandler(SqlExceptionHandler.DBTYPE_MYSQL);
		String errorCode = sed.getMappedErrorCode();
		String errorMessage = sed.getSqlErrorMessage();
		if (handler != null) {
			handler.handleSqlException(sed);
			errorCode = sed.getMappedErrorCode();
			errorMessage = sed.getSqlErrorMessage();
		} else {
			errorCode = sed.getSqlErrorCode();
			errorMessage = sed.getOriginalMessage();
		}
//		while (errorCode != null && errorCode.endsWith(".")) {
//			errorCode = errorCode.substring(0, errorCode.length() - 1);
//		}
		try {
			errorMessage = msgs.getMessage(errorCode.toUpperCase(), null, req.getLocale());
		} catch (NoSuchMessageException e) {
			errorMessage = sed.toString();
		}
		
		return new ResponseEntity<>("DB Error occured:" + errorCode + "|" + errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
