package com.fws.br.profile.core;

import com.fws.br.profile.commom.enums.EnumLogin;
import com.fws.br.profile.databind.UserData;
import com.fws.br.profile.entities.UserInfo;

public class Login {

	public EnumLogin doLogin(String login, String pwd) throws Exception {
		try {
			UserData data = new UserData();
			UserInfo user = data.getUserByLoginOrEmail(login, "");
			if (user != null && user.getPassword().equals(pwd)) {
				return EnumLogin.SUCCESS;
			} else {
				return EnumLogin.FAILURE;
			}
		} catch (Exception e) {
			return EnumLogin.EXECPTION;
		}
	}
}
