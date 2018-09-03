/**
 * Login.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fws.user.core;

public interface Login extends java.rmi.Remote {
    public java.lang.String addLogin(java.lang.String login, java.lang.String pwd, java.lang.String email) throws java.rmi.RemoteException;
    public java.lang.String doLogin(java.lang.String login, java.lang.String pwd) throws java.rmi.RemoteException;
}
