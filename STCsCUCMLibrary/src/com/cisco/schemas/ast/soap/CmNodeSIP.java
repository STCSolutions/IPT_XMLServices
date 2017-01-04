/**
 * CmNodeSIP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class CmNodeSIP  implements java.io.Serializable {
    private com.cisco.schemas.ast.soap.RisReturnCode returnCode;

    private java.lang.String name;

    private boolean noChange;

    private com.cisco.schemas.ast.soap.CmDeviceSIP[] cmDevices;

    public CmNodeSIP() {
    }

    public CmNodeSIP(
           com.cisco.schemas.ast.soap.RisReturnCode returnCode,
           java.lang.String name,
           boolean noChange,
           com.cisco.schemas.ast.soap.CmDeviceSIP[] cmDevices) {
           this.returnCode = returnCode;
           this.name = name;
           this.noChange = noChange;
           this.cmDevices = cmDevices;
    }


    /**
     * Gets the returnCode value for this CmNodeSIP.
     * 
     * @return returnCode
     */
    public com.cisco.schemas.ast.soap.RisReturnCode getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this CmNodeSIP.
     * 
     * @param returnCode
     */
    public void setReturnCode(com.cisco.schemas.ast.soap.RisReturnCode returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the name value for this CmNodeSIP.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this CmNodeSIP.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the noChange value for this CmNodeSIP.
     * 
     * @return noChange
     */
    public boolean isNoChange() {
        return noChange;
    }


    /**
     * Sets the noChange value for this CmNodeSIP.
     * 
     * @param noChange
     */
    public void setNoChange(boolean noChange) {
        this.noChange = noChange;
    }


    /**
     * Gets the cmDevices value for this CmNodeSIP.
     * 
     * @return cmDevices
     */
    public com.cisco.schemas.ast.soap.CmDeviceSIP[] getCmDevices() {
        return cmDevices;
    }


    /**
     * Sets the cmDevices value for this CmNodeSIP.
     * 
     * @param cmDevices
     */
    public void setCmDevices(com.cisco.schemas.ast.soap.CmDeviceSIP[] cmDevices) {
        this.cmDevices = cmDevices;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CmNodeSIP)) return false;
        CmNodeSIP other = (CmNodeSIP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.returnCode==null && other.getReturnCode()==null) || 
             (this.returnCode!=null &&
              this.returnCode.equals(other.getReturnCode()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            this.noChange == other.isNoChange() &&
            ((this.cmDevices==null && other.getCmDevices()==null) || 
             (this.cmDevices!=null &&
              java.util.Arrays.equals(this.cmDevices, other.getCmDevices())));
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
        if (getReturnCode() != null) {
            _hashCode += getReturnCode().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        _hashCode += (isNoChange() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCmDevices() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCmDevices());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCmDevices(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CmNodeSIP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmNodeSIP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ReturnCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "RisReturnCode"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noChange");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "NoChange"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmDevices");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevices"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDeviceSIP"));
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
