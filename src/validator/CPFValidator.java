package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import dao.FuncionarioDAO;

@FacesValidator("cpfValidator")
public class CPFValidator implements Validator{
	private final FuncionarioDAO dao;
	
	public CPFValidator() {
		this.dao = new FuncionarioDAO();
	}
	
	@Override
	public void validate(FacesContext contexto, UIComponent componente, Object valor) throws ValidatorException {
		String cpf = (String) valor;
		
		if (this.dao.verificarSeExisteCpfCadastrado(cpf))
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF já cadastrado!", "erro!"));
	}

}
