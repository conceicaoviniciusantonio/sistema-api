package com.example.sistema.utils.exceptions.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDetailDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Map<String, Set<String>> errors;
	private String message;
	private String detail;

	public ErrorDetailDto(String message, String detail) {
		this.message = message;
		this.detail = detail;
		this.errors = null;
	}

	public ErrorDetailDto(String message, String detail, Map<String, Set<String>> errors) {
		this.message = message;
		this.detail = detail;
		this.errors = errors;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Map<String, Set<String>> getErrors() {
		return this.errors;
	}
}

