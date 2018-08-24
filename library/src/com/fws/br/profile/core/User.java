package com.fws.br.profile.core;

import java.util.List;

import com.fws.br.profile.databind.UserData;
import com.fws.br.profile.entities.UserInfo;

public class User {

	/**
	 * @param login
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public Boolean doLogin(String login, String pwd) throws Exception {
		try {
			UserData data = new UserData();
			UserInfo user = data.getUserByLogin(login);
			if (user.getPassword().equals(pwd))
				return true;
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	/**
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	public Boolean addUser(UserInfo userInfo) throws Exception {
		try {
			UserData data = new UserData();
			data.addUser(userInfo);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param users
	 * @return
	 * @throws Exception
	 */
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
