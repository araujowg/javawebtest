/**
 * LoginInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.fws.user.entity;

public class LoginInfo  implements java.io.Serializable {
    private int accessCounter;

    private boolean active;

    private boolean blocked;

    private java.lang.String login;

    private java.lang.String password;

    private java.lang.String ultimoAcesso;

    private br.com.fws.user.entity.UserInfo user;

    private java.lang.String userId;

    public LoginInfo() {
    }

    public LoginInfo(
           int accessCounter,
           boolean active,
           boolean blocked,
           java.lang.String login,
           java.lang.String password,
           java.lang.String ultimoAcesso,
           br.com.fws.user.entity.UserInfo user,
           java.lang.String userId) {
           this.accessCounter = accessCounter;
           this.active = active;
           this.blocked = blocked;
           this.login = login;
           this.password = password;
           this.ultimoAcesso = ultimoAcesso;
           this.user = user;
           this.userId = userId;
    }


    /**
     * Gets the accessCounter value for this LoginInfo.
     * 
     * @return accessCounter
     */
    public int getAccessCounter() {
        return accessCounter;
    }


    /**
     * Sets the accessCounter value for this LoginInfo.
     * 
     * @param accessCounter
     */
    public void setAccessCounter(int accessCounter) {
        this.accessCounter = accessCounter;
    }


    /**
     * Gets the active value for this LoginInfo.
     * 
     * @return active
     */
    public boolean isActive() {
        return active;
    }


    /**
     * Sets the active value for this LoginInfo.
     * 
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }


    /**
     * Gets the blocked value for this LoginInfo.
     * 
     * @return blocked
     */
    public boolean isBlocked() {
        return blocked;
    }


    /**
     * Sets the blocked value for this LoginInfo.
     * 
     * @param blocked
     */
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }


    /**
     * Gets the login value for this LoginInfo.
     * 
     * @return login
     */
    public java.lang.String getLogin() {
        return login;
    }


    /**
     * Sets the login value for this LoginInfo.
     * 
     * @param login
     */
    public void setLogin(java.lang.String login) {
        this.login = login;
    }


    /**
     * Gets the password value for this LoginInfo.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this LoginInfo.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the ultimoAcesso value for this LoginInfo.
     * 
     * @return ultimoAcesso
     */
    public java.lang.String getUltimoAcesso() {
        return ultimoAcesso;
    }


    /**
     * Sets the ultimoAcesso value for this LoginInfo.
     * 
     * @param ultimoAcesso
     */
    public void setUltimoAcesso(java.lang.String ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }


    /**
     * Gets the user value for this LoginInfo.
     * 
     * @return user
     */
    public br.com.fws.user.entity.UserInfo getUser() {
        return user;
    }


    /**
     * Sets the user value for this LoginInfo.
     * 
     * @param user
     */
    public void setUser(br.com.fws.user.entity.UserInfo user) {
        this.user = user;
    }


    /**
     * Gets the userId value for this LoginInfo.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this LoginInfo.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LoginInfo)) return false;
        LoginInfo other = (LoginInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.accessCounter == other.getAccessCounter() &&
            this.active == other.isActive() &&
            this.blocked == other.isBlocked() &&
            ((this.login==null && other.getLogin()==null) || 
             (this.login!=null &&
              this.login.equals(other.getLogin()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.ultimoAcesso==null && other.getUltimoAcesso()==null) || 
             (this.ultimoAcesso!=null &&
              this.ultimoAcesso.equals(other.getUltimoAcesso()))) &&
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getAccessCounter();
        _hashCode += (isActive() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isBlocked() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getLogin() != null) {
            _hashCode += getLogin().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getUltimoAcesso() != null) {
            _hashCode += getUltimoAcesso().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LoginInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entity.user.fws.com.br", "LoginInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessCounter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.fws.com.br", "accessCounter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("active");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.fws.com.br", "active"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blocked");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.fws.com.br", "blocked"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("login");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.fws.com.br", "login"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.fws.com.br", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ultimoAcesso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.fws.com.br", "ultimoAcesso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.fws.com.br", "user"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://entity.user.fws.com.br", "UserInfo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.user.fws.com.br", "userId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
