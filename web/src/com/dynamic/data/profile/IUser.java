package com.dynamic.data.profile;

import java.util.List;

import com.dynamic.ent.Usuario;

public interface IUser {

	public Boolean addUser(List<Usuario> lstUser) throws Exception;

	public Boolean addUserWithAttributeValuesMethod(List<Usuario> lstUsers) throws Exception;

	public Usuario getUserByIdAndLogin(int id, String login) throws Exception;

	public List<Usuario> getUserByName(String name) throws Exception;

	public List<Usuario> getUserByQueryId(int id) throws Exception;

	public List<Usuario> getDisabledUsers() throws Exception;

	public List<Usuario> getAllUsers() throws Exception;

	public Usuario updateUser(Usuario user) throws Exception;

	public Boolean deleteDisabledUsers() throws Exception;
}
