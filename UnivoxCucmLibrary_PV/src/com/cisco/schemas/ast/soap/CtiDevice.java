/**
 * CtiDevice.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class CtiDevice  implements java.io.Serializable {
    private java.lang.Boolean appControlsMedia;

    private java.lang.String deviceName;

    private com.cisco.schemas.ast.soap.CtiStatus deviceStatus;

    private org.apache.axis.types.UnsignedInt deviceStatusReason;

    private org.apache.axis.types.UnsignedInt deviceTimeStamp;

    public CtiDevice() {
    }

    public CtiDevice(
           java.lang.Boolean appControlsMedia,
           java.lang.String deviceName,
           com.cisco.schemas.ast.soap.CtiStatus deviceStatus,
           org.apache.axis.types.UnsignedInt deviceStatusReason,
           org.apache.axis.types.UnsignedInt deviceTimeStamp) {
           this.appControlsMedia = appControlsMedia;
           this.deviceName = deviceName;
           this.deviceStatus = deviceStatus;
           this.deviceStatusReason = deviceStatusReason;
           this.deviceTimeStamp = deviceTimeStamp;
    }


    /**
     * Gets the appControlsMedia value for this CtiDevice.
     * 
     * @return appControlsMedia
     */
    public java.lang.Boolean getAppControlsMedia() {
        return appControlsMedia;
    }


    /**
     * Sets the appControlsMedia value for this CtiDevice.
     * 
     * @param appControlsMedia
     */
    public void setAppControlsMedia(java.lang.Boolean appControlsMedia) {
        this.appControlsMedia = appControlsMedia;
    }


    /**
     * Gets the deviceName value for this CtiDevice.
     * 
     * @return deviceName
     */
    public java.lang.String getDeviceName() {
        return deviceName;
    }


    /**
     * Sets the deviceName value for this CtiDevice.
     * 
     * @param deviceName
     */
    public void setDeviceName(java.lang.String deviceName) {
        this.deviceName = deviceName;
    }


    /**
     * Gets the deviceStatus value for this CtiDevice.
     * 
     * @return deviceStatus
     */
    public com.cisco.schemas.ast.soap.CtiStatus getDeviceStatus() {
        return deviceStatus;
    }


    /**
     * Sets the deviceStatus value for this CtiDevice.
     * 
     * @param deviceStatus
     */
    public void setDeviceStatus(com.cisco.schemas.ast.soap.CtiStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }


    /**
     * Gets the deviceStatusReason value for this CtiDevice.
     * 
     * @return deviceStatusReason
     */
    public org.apache.axis.types.UnsignedInt getDeviceStatusReason() {
        return deviceStatusReason;
    }


    /**
     * Sets the deviceStatusReason value for this CtiDevice.
     * 
     * @param deviceStatusReason
     */
    public void setDeviceStatusReason(org.apache.axis.types.UnsignedInt deviceStatusReason) {
        this.deviceStatusReason = deviceStatusReason;
    }


    /**
     * Gets the deviceTimeStamp value for this CtiDevice.
     * 
     * @return deviceTimeStamp
     */
    public org.apache.axis.types.UnsignedInt getDeviceTimeStamp() {
        return deviceTimeStamp;
    }


    /**
     * Sets the deviceTimeStamp value for this CtiDevice.
     * 
     * @param deviceTimeStamp
     */
    public void setDeviceTimeStamp(org.apache.axis.types.UnsignedInt deviceTimeStamp) {
        this.deviceTimeStamp = deviceTimeStamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CtiDevice)) return false;
        CtiDevice other = (CtiDevice) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.appControlsMedia==null && other.getAppControlsMedia()==null) || 
             (this.appControlsMedia!=null &&
              this.appControlsMedia.equals(other.getAppControlsMedia()))) &&
            ((this.deviceName==null && other.getDeviceName()==null) || 
             (this.deviceName!=null &&
              this.deviceName.equals(other.getDeviceName()))) &&
            ((this.deviceStatus==null && other.getDeviceStatus()==null) || 
             (this.deviceStatus!=null &&
              this.deviceStatus.equals(other.getDeviceStatus()))) &&
            ((this.deviceStatusReason==null && other.getDeviceStatusReason()==null) || 
             (this.deviceStatusReason!=null &&
              this.deviceStatusReason.equals(other.getDeviceStatusReason()))) &&
            ((this.deviceTimeStamp==null && other.getDeviceTimeStamp()==null) || 
             (this.deviceTimeStamp!=null &&
              this.deviceTimeStamp.equals(other.getDeviceTimeStamp())));
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
        if (getAppControlsMedia() != null) {
            _hashCode += getAppControlsMedia().hashCode();
        }
        if (getDeviceName() != null) {
            _hashCode += getDeviceName().hashCode();
        }
        if (getDeviceStatus() != null) {
            _hashCode += getDeviceStatus().hashCode();
        }
        if (getDeviceStatusReason() != null) {
            _hashCode += getDeviceStatusReason().hashCode();
        }
        if (getDeviceTimeStamp() != null) {
            _hashCode += getDeviceTimeStamp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CtiDevice.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiDevice"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appControlsMedia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "AppControlsMedia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deviceName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DeviceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deviceStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DeviceStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiStatus"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deviceStatusReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DeviceStatusReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deviceTimeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DeviceTimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
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
