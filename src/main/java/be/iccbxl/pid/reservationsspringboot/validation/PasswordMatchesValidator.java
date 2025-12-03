package be.iccbxl.pid.reservationsspringboot.validation;

import be.iccbxl.pid.reservationsspringboot.dto.UserProfileDto;
import be.iccbxl.pid.reservationsspringboot.dto.UserRegistrationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		if (value == null) {
			return true;
		}

		String pwd = null;
		String confirm = null;

		if (value instanceof UserRegistrationDto dto) {
			pwd = dto.getPassword();
			confirm = dto.getConfirmPassword();
		} else if (value instanceof UserProfileDto dto) {
			pwd = dto.getPassword();
			confirm = dto.getConfirmPassword();
		} else {
			// Si une autre classe utilise par erreur @PasswordMatches, on ne bloque pas
			return true;
		}

		System.out.println("Passe: " + pwd);
		System.out.println("Confirm: " + confirm);

		// Si les deux champs sont vides, on considère que le mot de passe n'est pas
		// modifié => OK
		if ((pwd == null || pwd.isBlank()) && (confirm == null || confirm.isBlank())) {
			return true;
		}

		// Sinon, il faut qu'ils soient identiques
		if (pwd == null) {
			return false;
		}
		return pwd.equals(confirm);
	}
}
