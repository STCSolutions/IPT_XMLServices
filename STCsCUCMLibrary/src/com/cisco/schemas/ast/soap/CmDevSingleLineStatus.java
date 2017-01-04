/**
 * CmDevSingleLineStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class CmDevSingleLineStatus  implements java.io.Serializable {
    private java.lang.String directoryNumber;

    private com.cisco.schemas.ast.soap.CmSingleLineStatus status;

    public CmDevSingleLineStatus() {
    }

    public CmDevSingleLineStatus(
           java.lang.String directoryNumber,
           com.cisco.schemas.ast.soap.CmSingleLineStatus status) {
           this.directoryNumber = directoryNumber;
           this.status = status;
    }


    /**
     * Gets the directoryNumber value for this CmDevSingleLineStatus.
     * 
     * @return directoryNumber
     */
    public java.lang.String getDirectoryNumber() {
        return directoryNumber;
    }


    /**
     * Sets the directoryNumber value for this CmDevSingleLineStatus.
     * 
     * @param directoryNumber
     */
    public void setDirectoryNumber(java.lang.String directoryNumber) {
        this.directoryNumber = directoryNumber;
    }


    /**
     * Gets the status value for this CmDevSingleLineStatus.
     * 
     * @return status
     */
    public com.cisco.schemas.ast.soap.CmSingleLineStatus getStatus() {
        return status;
    }


    /**
     * Sets the status value for this CmDevSingleLineStatus.
     * 
     * @param status
     */
    public void setStatus(com.cisco.schemas.ast.soap.CmSingleLineStatus status) {
        this.status = status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CmDevSingleLineStatus)) return false;
        CmDevSingleLineStatus other = (CmDevSingleLineStatus) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.directoryNumber==null && other.getDirectoryNumber()==null) || 
             (this.directoryNumber!=null &&
              this.directoryNumber.equals(other.getDirectoryNumber()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus())));
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
        if (getDirectoryNumber() != null) {
            _hashCode += getDirectoryNumber().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CmDevSingleLineStatus.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevSingleLineStatus"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("directoryNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DirectoryNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmSingleLineStatus"));
        elemField.setNillable(false);
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
