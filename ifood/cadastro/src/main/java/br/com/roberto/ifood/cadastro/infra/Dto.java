package br.com.roberto.ifood.cadastro.infra;

import javax.validation.ConstraintValidatorContext;

public interface Dto {

	default boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
