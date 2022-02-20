package com.obus.uaa.common.validator.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.obus.uaa.user.request.UserRequest;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatches, UserRequest> {

	@Override
	public boolean isValid(UserRequest obj, ConstraintValidatorContext context) {

		return obj.getPassword().equals(obj.getMatchingPassword());
	}
}
