package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import dao.UsuarioDAO;

@FacesValidator("matriculaValidator")
public class MatriculaValidator implements Validator {
	private final UsuarioDAO dao;
	
	public MatriculaValidator()
	{
		this.dao = new UsuarioDAO();
	}

	@Override
	public void validate(FacesContext contexto, UIComponent componente, Object valor) throws ValidatorException {
		 Long matricula = Long.valueOf((Integer) valor);
		 
		 if (this.dao.verificarSeExisteMatriculaCadastrada(matricula))
			 throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "matricula já cadastrada", "a matricula já está cadastrada"));
		
	}

}
