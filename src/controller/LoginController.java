package controller;
import javax.faces.bean.ManagedBean;

import model.Login;

@ManagedBean
public class LoginController {
	
	Login login = new Login();
	
	public void fazerLogin() 
	{
		System.out.println(login.getEmail());
		System.out.println(login.getSenha());
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}