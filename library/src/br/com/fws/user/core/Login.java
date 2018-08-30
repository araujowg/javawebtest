package br.com.fws.user.core;

import br.com.fws.commom.Encryption;
import br.com.fws.user.databind.LoginData;
import br.com.fws.user.databind.UserData;
import br.com.fws.user.entity.LoginInfo;
import br.com.fws.user.entity.UserInfo;
import br.com.fws.user.enums.LoginMessage;

public class Login {

	public String doLogin(String login, String pwd) throws Exception {
		try {
			LoginData data = new LoginData();
			LoginInfo info = data.getUserByLogin(login);
			if (info != null && info.getPassword().equals(Encryption.Generate(pwd))) {
				data.addCountAccess(info);
				return LoginMessage.SUCCESS.getMessage();
			} else {
				return LoginMessage.FAILURE.getMessage();
			}
		} catch (Exception e) {
			return LoginMessage.EXECPTION.getMessage();
		}
	}

	public String addLogin(String login, String pwd, String email) throws Exception {
		try {

			if (checkIfLoginExists(login))
				return LoginMessage.LOGINEXISTS.getMessage();

			if (checkIfEmailExists(email))
				return LoginMessage.EMAILEXISTS.getMessage();

			LoginData data = new LoginData();
			LoginInfo info = new LoginInfo(false, 0, false, login, pwd, null,
					new UserInfo(null, null, email, null, null));

			if (data.addLogin(info)) {
				return LoginMessage.SUCCESSFULLYCREATED.getMessage();
			} else {
				return LoginMessage.FAILUREONCREATION.getMessage();
			}

		} catch (Exception e) {
			throw e;
			// return LoginMessage.FAILUREONCREATION.getMessage();
		}
	}

	private Boolean checkIfLoginExists(String login) throws Exception {
		try {
			LoginData data = new LoginData();
			LoginInfo info = data.getUserByLogin(login);
			return (info != null);

		} catch (Exception e) {
			throw e;
		}
	}

	private Boolean checkIfEmailExists(String email) throws Exception {
		try {
			UserData data = new UserData();
			UserInfo info = data.getUserByEmail(email);
			return (info != null);

		} catch (Exception e) {
			throw e;
		}
	}

}
