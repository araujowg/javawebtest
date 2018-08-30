package br.com.fws.user.test;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import br.com.fws.commom.Encryption;
import br.com.fws.user.core.Login;
import br.com.fws.user.core.User;
import br.com.fws.user.enums.LoginMessage;

public class LoginTest {

	@Test
	public void createLogin() throws Exception {

		Assert.assertNotNull(Encryption.Generate("123"));

		final UUID uuid = UUID.randomUUID();
		Assert.assertNotNull(uuid.toString().replaceAll("-", ""));

		String email = "email@" + uuid.toString();
		String login = "login" + uuid.toString();

		Login loginCore = new Login();
		User userCore = new User();

		Assert.assertEquals(LoginMessage.SUCCESSFULLYCREATED.getMessage(), loginCore.addLogin(login, "123", email));

		Assert.assertEquals(LoginMessage.LOGINEXISTS.getMessage(), loginCore.addLogin(login, "123", "any"));

		Assert.assertEquals(LoginMessage.EMAILEXISTS.getMessage(), loginCore.addLogin("any", "1234", email));

		Assert.assertEquals(LoginMessage.SUCCESS.getMessage(), loginCore.doLogin(login, "123"));

		Assert.assertEquals(LoginMessage.FAILURE.getMessage(), loginCore.doLogin(login, "1123"));

	}

}
