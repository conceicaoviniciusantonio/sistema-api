package com.example.sistema.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

import com.example.sistema.exceptions.BusinessException;
import com.example.sistema.exceptions.validations.ValidationBusinessException;

@Component
public class ValidatorUtils {
	public <T> void validate(T vo) throws BusinessException {
		final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		final Validator validator = factory.getValidator();

		final Set<ConstraintViolation<T>> constraintViolations = validator.validate(vo);

		if (constraintViolations.size() > 0) {
			final Map<String, Set<String>> errors = new HashMap<>();

			final Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();

			while (iterator.hasNext()) {
				final ConstraintViolation<T> cv = iterator.next();

				Set<String> errosField = errors.get(cv.getPropertyPath().toString());
				if (errosField == null) {
					errosField = new HashSet<>();
				}
				errosField.add(cv.getMessage());
				errors.put(cv.getPropertyPath().toString(), errosField);
			}

			throw new ValidationBusinessException("Erro ao salvar dados", errors);
		}
	}
}
