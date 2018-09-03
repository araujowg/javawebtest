package br.com.fws.user.test;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import br.com.fws.user.core.Login;
import br.com.fws.user.enums.LoginMessage;

public class LoginTest {

	final UUID uuid = UUID.randomUUID();
	String email = "email@" + uuid.toString();
	String login = "login" + uuid.toString();
	Login loginCore = new Login();

	@Test
	public void createLogin() throws Exception {
		Assert.assertEquals(LoginMessage.SUCCESSFULLYCREATED.getMessage(), loginCore.addLogin(login, "123", email));

		// Assert.assertEquals(LoginMessage.LOGINEXISTS.getMessage(),
		// loginCore.addLogin(login, "123", "any" + email));
		// Assert.assertEquals(LoginMessage.EMAILEXISTS.getMessage(),
		// loginCore.addLogin("any" + login, "1234", email));
		//
		// Assert.assertEquals(LoginMessage.SUCCESS.getMessage(),
		// loginCore.doLogin(login, "123"));
		// Assert.assertEquals(LoginMessage.FAILURE.getMessage(),
		// loginCore.doLogin(login, "1123"));
	}

	@Test
	public void doSuccessLogin() throws Exception {

		// Given
		Assert.assertEquals(LoginMessage.SUCCESSFULLYCREATED.getMessage(), loginCore.addLogin(login, "123", email));

		// When
		Assert.assertEquals(LoginMessage.LOGINEXISTS.getMessage(), loginCore.addLogin(login, "123", "any" + email));

		// Then
		Assert.assertEquals(LoginMessage.SUCCESS.getMessage(), loginCore.doLogin(login, "123"));
	}

}
