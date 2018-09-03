package br.com.fws.user.core;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.junit.Ignore;

import br.com.fws.user.databind.UserData;
import br.com.fws.user.entity.UserInfo;

@WebService(endpointInterface = "br.com.fws.core.user")
@SOAPBinding(style = Style.RPC)

public class User {

	@Ignore
	public Boolean addUser(UserInfo userInfo) throws Exception {
		try {
			UserData data = new UserData();
			data.addUser(userInfo);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public Boolean addManyUsers(List<UserInfo> users) throws Exception {
		String message = "";

		try {
			UserData data = new UserData();
			Boolean isSuccess;

			for (UserInfo userInfo : users) {
				isSuccess = data.addUserWithAttributeValuesMethod(userInfo);
				if (!isSuccess) {
					message += " " + userInfo.getLogin();
				}
			}

			if (message.isEmpty()) {
				return true;
			} else {
				throw new Exception("Erro ao incluir os logins:  " + message);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@WebMethod
	public Boolean updateUser(UserInfo user) throws Exception {
		try {
			UserData data = new UserData();
			return data.updateUser(user);
		} catch (Exception e) {
			throw e;
		}

	}

	@WebMethod
	public List<UserInfo> getAllUsers() throws Exception {
		List<UserInfo> users = null;
		try {
			UserData data = new UserData();

			users = data.getAllUsers();

		} catch (Exception e) {
			throw e;
		}
		return users;
	}
}
