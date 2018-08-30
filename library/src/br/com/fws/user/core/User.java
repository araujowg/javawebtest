package br.com.fws.user.core;

import java.util.List;

import br.com.fws.user.databind.UserData;
import br.com.fws.user.entity.UserInfo;

public class User {

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

	public Boolean updateUser(UserInfo user) throws Exception {
		try {
			UserData data = new UserData();
			return data.updateUser(user);
		} catch (Exception e) {
			throw e;
		}

	}

}
