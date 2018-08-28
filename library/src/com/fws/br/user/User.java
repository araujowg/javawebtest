package com.fws.br.user;

import java.util.List;

import com.fws.br.user.databind.UserData;
import com.fws.br.user.entity.UserInfo;

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

}
