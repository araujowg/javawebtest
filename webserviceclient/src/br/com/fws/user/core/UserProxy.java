package br.com.fws.user.core;

public class UserProxy implements br.com.fws.user.core.User {
  private String _endpoint = null;
  private br.com.fws.user.core.User user = null;
  
  public UserProxy() {
    _initUserProxy();
  }
  
  public UserProxy(String endpoint) {
    _endpoint = endpoint;
    _initUserProxy();
  }
  
  private void _initUserProxy() {
    try {
      user = (new br.com.fws.user.core.UserServiceLocator()).getUser();
      if (user != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)user)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)user)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (user != null)
      ((javax.xml.rpc.Stub)user)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public br.com.fws.user.core.User getUser() {
    if (user == null)
      _initUserProxy();
    return user;
  }
  
  public boolean updateUser(br.com.fws.user.entity.UserInfo user0) throws java.rmi.RemoteException{
    if (user == null)
      _initUserProxy();
    return user.updateUser(user0);
  }
  
  public java.lang.Object[] getAllUsers() throws java.rmi.RemoteException{
    if (user == null)
      _initUserProxy();
    return user.getAllUsers();
  }
  
  
}