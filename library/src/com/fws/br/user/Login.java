package com.fws.br.user;

import com.fws.br.user.databind.UserData;
import com.fws.br.user.entity.UserInfo;
import com.fws.br.user.enums.EnumLogin;

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
