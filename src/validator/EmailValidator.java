package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import dao.UsuarioDAO;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator{
	
	private final UsuarioDAO dao; 
	private static final String EXPRESSAO_REGULAR = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";
	
	public EmailValidator()
	{
		this.dao = new UsuarioDAO();
	}

	@Override
	public void validate(FacesContext contexto, UIComponent componente, Object valor) throws ValidatorException {
		String email = valor.toString();
		if (!verificarEmail(email))
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email inválido", "Email inválido"));
		else
		{
			if (this.dao.verificarSeExisteEmailCadastrado(email))
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Email já cadastrado", "Email já cadastrado"));
		}
		
	}
	
	private static Boolean verificarEmail(String email)
	{
		if (!email.isEmpty() && email != null)
		{
			Pattern pattern = Pattern.compile(EXPRESSAO_REGULAR, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches())
				return true;
		}
		
		return false;
	}

}
