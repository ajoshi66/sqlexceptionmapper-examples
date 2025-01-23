package com.joshi.manufacturer.exceptions;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class SqlExceptionAdvice extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private SqlExceptionHandler h2dbHandler;
	
	@ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(
            final DataAccessException exception, final HttpServletRequest request) throws ManufacturerException {
		log.error("Data Access Error:" + exception.getMessage());
		String response = "Database error::";
		String entityName = getEntityName(request);
		String errorCode = "";
		if (!entityName.isBlank()) {
			errorCode += entityName + ".";
		}
		SqlExceptionDetail sqlED = SqlExceptionHandler.getSqlExceptionDetails(exception, SqlExceptionHandler.DBTYPE_H2DB);
		if (sqlED != null) {
			try {
				h2dbHandler.handleSqlException(sqlED);
			} catch (Exception e) {
				log.error("Critical Error: Unable to handle exception.", e);
			}
			if (sqlED.getMappedErrorCode() != null) {
				errorCode += sqlED.getMappedErrorCode();
			}
			try {
				response += messageSource.getMessage(errorCode.toUpperCase(), null, request.getLocale());
			} catch (NoSuchMessageException e) {
				log.error("Critical Error: No such error message.", e);
				response += sqlED.toString();
			}
		} else {
			response += " from unknown database.";
		}
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private String getEntityName(final HttpServletRequest request) {
		String result = "";
		String path = request.getServletPath();
		if (path != null) {
			String[] parts = path.split("/");
			for (int i = 0; i < parts.length; i++) {
				if (!parts[i].isBlank()) {
					result = parts[i];
					break;
				}
			}
		}
		return result;
	}
	
}
