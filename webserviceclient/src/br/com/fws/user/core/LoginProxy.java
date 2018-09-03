package br.com.fws.user.core;

public class LoginProxy implements br.com.fws.user.core.Login {
  private String _endpoint = null;
  private br.com.fws.user.core.Login login = null;
  
  public LoginProxy() {
    _initLoginProxy();
  }
  
  public LoginProxy(String endpoint) {
    _endpoint = endpoint;
    _initLoginProxy();
  }
  
  private void _initLoginProxy() {
    try {
      login = (new br.com.fws.user.core.LoginServiceLocator()).getLogin();
      if (login != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)login)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)login)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (login != null)
      ((javax.xml.rpc.Stub)login)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.fws.user.core.Login getLogin() {
    if (login == null)
      _initLoginProxy();
    return login;
  }
  
  public java.lang.String addLogin(java.lang.String login0, java.lang.String pwd, java.lang.String email) throws java.rmi.RemoteException{
    if (login == null)
      _initLoginProxy();
    return login.addLogin(login0, pwd, email);
  }
  
  public java.lang.String doLogin(java.lang.String login0, java.lang.String pwd) throws java.rmi.RemoteException{
    if (login == null)
      _initLoginProxy();
    return login.doLogin(login0, pwd);
  }
  
  
}