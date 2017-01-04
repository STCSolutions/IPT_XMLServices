/**
 * CtiItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class CtiItem  implements java.io.Serializable {
    private java.lang.String appId;

    private java.lang.String userId;

    private java.lang.String appIpAddr;

    private com.cisco.schemas.ast.soap.CtiStatus appStatus;

    private org.apache.axis.types.UnsignedInt appStatusReason;

    private org.apache.axis.types.UnsignedInt appTimeStamp;

    private com.cisco.schemas.ast.soap.CtiDevice ctiDevice;

    private com.cisco.schemas.ast.soap.CtiLine ctiLine;

    public CtiItem() {
    }

    public CtiItem(
           java.lang.String appId,
           java.lang.String userId,
           java.lang.String appIpAddr,
           com.cisco.schemas.ast.soap.CtiStatus appStatus,
           org.apache.axis.types.UnsignedInt appStatusReason,
           org.apache.axis.types.UnsignedInt appTimeStamp,
           com.cisco.schemas.ast.soap.CtiDevice ctiDevice,
           com.cisco.schemas.ast.soap.CtiLine ctiLine) {
           this.appId = appId;
           this.userId = userId;
           this.appIpAddr = appIpAddr;
           this.appStatus = appStatus;
           this.appStatusReason = appStatusReason;
           this.appTimeStamp = appTimeStamp;
           this.ctiDevice = ctiDevice;
           this.ctiLine = ctiLine;
    }


    /**
     * Gets the appId value for this CtiItem.
     * 
     * @return appId
     */
    public java.lang.String getAppId() {
        return appId;
    }


    /**
     * Sets the appId value for this CtiItem.
     * 
     * @param appId
     */
    public void setAppId(java.lang.String appId) {
        this.appId = appId;
    }


    /**
     * Gets the userId value for this CtiItem.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this CtiItem.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the appIpAddr value for this CtiItem.
     * 
     * @return appIpAddr
     */
    public java.lang.String getAppIpAddr() {
        return appIpAddr;
    }


    /**
     * Sets the appIpAddr value for this CtiItem.
     * 
     * @param appIpAddr
     */
    public void setAppIpAddr(java.lang.String appIpAddr) {
        this.appIpAddr = appIpAddr;
    }


    /**
     * Gets the appStatus value for this CtiItem.
     * 
     * @return appStatus
     */
    public com.cisco.schemas.ast.soap.CtiStatus getAppStatus() {
        return appStatus;
    }


    /**
     * Sets the appStatus value for this CtiItem.
     * 
     * @param appStatus
     */
    public void setAppStatus(com.cisco.schemas.ast.soap.CtiStatus appStatus) {
        this.appStatus = appStatus;
    }


    /**
     * Gets the appStatusReason value for this CtiItem.
     * 
     * @return appStatusReason
     */
    public org.apache.axis.types.UnsignedInt getAppStatusReason() {
        return appStatusReason;
    }


    /**
     * Sets the appStatusReason value for this CtiItem.
     * 
     * @param appStatusReason
     */
    public void setAppStatusReason(org.apache.axis.types.UnsignedInt appStatusReason) {
        this.appStatusReason = appStatusReason;
    }


    /**
     * Gets the appTimeStamp value for this CtiItem.
     * 
     * @return appTimeStamp
     */
    public org.apache.axis.types.UnsignedInt getAppTimeStamp() {
        return appTimeStamp;
    }


    /**
     * Sets the appTimeStamp value for this CtiItem.
     * 
     * @param appTimeStamp
     */
    public void setAppTimeStamp(org.apache.axis.types.UnsignedInt appTimeStamp) {
        this.appTimeStamp = appTimeStamp;
    }


    /**
     * Gets the ctiDevice value for this CtiItem.
     * 
     * @return ctiDevice
     */
    public com.cisco.schemas.ast.soap.CtiDevice getCtiDevice() {
        return ctiDevice;
    }


    /**
     * Sets the ctiDevice value for this CtiItem.
     * 
     * @param ctiDevice
     */
    public void setCtiDevice(com.cisco.schemas.ast.soap.CtiDevice ctiDevice) {
        this.ctiDevice = ctiDevice;
    }


    /**
     * Gets the ctiLine value for this CtiItem.
     * 
     * @return ctiLine
     */
    public com.cisco.schemas.ast.soap.CtiLine getCtiLine() {
        return ctiLine;
    }


    /**
     * Sets the ctiLine value for this CtiItem.
     * 
     * @param ctiLine
     */
    public void setCtiLine(com.cisco.schemas.ast.soap.CtiLine ctiLine) {
        this.ctiLine = ctiLine;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CtiItem)) return false;
        CtiItem other = (CtiItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.appId==null && other.getAppId()==null) || 
             (this.appId!=null &&
              this.appId.equals(other.getAppId()))) &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.appIpAddr==null && other.getAppIpAddr()==null) || 
             (this.appIpAddr!=null &&
              this.appIpAddr.equals(other.getAppIpAddr()))) &&
            ((this.appStatus==null && other.getAppStatus()==null) || 
             (this.appStatus!=null &&
              this.appStatus.equals(other.getAppStatus()))) &&
            ((this.appStatusReason==null && other.getAppStatusReason()==null) || 
             (this.appStatusReason!=null &&
              this.appStatusReason.equals(other.getAppStatusReason()))) &&
            ((this.appTimeStamp==null && other.getAppTimeStamp()==null) || 
             (this.appTimeStamp!=null &&
              this.appTimeStamp.equals(other.getAppTimeStamp()))) &&
            ((this.ctiDevice==null && other.getCtiDevice()==null) || 
             (this.ctiDevice!=null &&
              this.ctiDevice.equals(other.getCtiDevice()))) &&
            ((this.ctiLine==null && other.getCtiLine()==null) || 
             (this.ctiLine!=null &&
              this.ctiLine.equals(other.getCtiLine())));
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
        if (getAppId() != null) {
            _hashCode += getAppId().hashCode();
        }
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getAppIpAddr() != null) {
            _hashCode += getAppIpAddr().hashCode();
        }
        if (getAppStatus() != null) {
            _hashCode += getAppStatus().hashCode();
        }
        if (getAppStatusReason() != null) {
            _hashCode += getAppStatusReason().hashCode();
        }
        if (getAppTimeStamp() != null) {
            _hashCode += getAppTimeStamp().hashCode();
        }
        if (getCtiDevice() != null) {
            _hashCode += getCtiDevice().hashCode();
        }
        if (getCtiLine() != null) {
            _hashCode += getCtiLine().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CtiItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "AppId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "UserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appIpAddr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "AppIpAddr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "AppStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiStatus"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appStatusReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "AppStatusReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appTimeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "AppTimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ctiDevice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiDevice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiDevice"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ctiLine");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiLine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiLine"));
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
