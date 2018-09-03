/**
 * User.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fws.user.core;

public interface User extends java.rmi.Remote {
    public boolean updateUser(br.com.fws.user.entity.UserInfo user) throws java.rmi.RemoteException;
    public java.lang.Object[] getAllUsers() throws java.rmi.RemoteException;
}
