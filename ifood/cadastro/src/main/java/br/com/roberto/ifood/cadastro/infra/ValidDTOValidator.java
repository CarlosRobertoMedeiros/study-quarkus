package br.com.roberto.ifood.cadastro.infra;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDTOValidator  implements ConstraintValidator<ValidDto, Dto>{

	@Override
	public boolean isValid(Dto dto, ConstraintValidatorContext context) {
		return dto.isValid(context);
	}

}
