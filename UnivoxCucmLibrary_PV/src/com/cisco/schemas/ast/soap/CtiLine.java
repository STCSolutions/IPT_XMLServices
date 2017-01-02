/**
 * CtiLine.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class CtiLine  implements java.io.Serializable {
    private java.lang.String dirNumber;

    private com.cisco.schemas.ast.soap.CtiStatus lineStatus;

    private org.apache.axis.types.UnsignedInt lineStatusReason;

    private org.apache.axis.types.UnsignedInt lineTimeStamp;

    public CtiLine() {
    }

    public CtiLine(
           java.lang.String dirNumber,
           com.cisco.schemas.ast.soap.CtiStatus lineStatus,
           org.apache.axis.types.UnsignedInt lineStatusReason,
           org.apache.axis.types.UnsignedInt lineTimeStamp) {
           this.dirNumber = dirNumber;
           this.lineStatus = lineStatus;
           this.lineStatusReason = lineStatusReason;
           this.lineTimeStamp = lineTimeStamp;
    }


    /**
     * Gets the dirNumber value for this CtiLine.
     * 
     * @return dirNumber
     */
    public java.lang.String getDirNumber() {
        return dirNumber;
    }


    /**
     * Sets the dirNumber value for this CtiLine.
     * 
     * @param dirNumber
     */
    public void setDirNumber(java.lang.String dirNumber) {
        this.dirNumber = dirNumber;
    }


    /**
     * Gets the lineStatus value for this CtiLine.
     * 
     * @return lineStatus
     */
    public com.cisco.schemas.ast.soap.CtiStatus getLineStatus() {
        return lineStatus;
    }


    /**
     * Sets the lineStatus value for this CtiLine.
     * 
     * @param lineStatus
     */
    public void setLineStatus(com.cisco.schemas.ast.soap.CtiStatus lineStatus) {
        this.lineStatus = lineStatus;
    }


    /**
     * Gets the lineStatusReason value for this CtiLine.
     * 
     * @return lineStatusReason
     */
    public org.apache.axis.types.UnsignedInt getLineStatusReason() {
        return lineStatusReason;
    }


    /**
     * Sets the lineStatusReason value for this CtiLine.
     * 
     * @param lineStatusReason
     */
    public void setLineStatusReason(org.apache.axis.types.UnsignedInt lineStatusReason) {
        this.lineStatusReason = lineStatusReason;
    }


    /**
     * Gets the lineTimeStamp value for this CtiLine.
     * 
     * @return lineTimeStamp
     */
    public org.apache.axis.types.UnsignedInt getLineTimeStamp() {
        return lineTimeStamp;
    }


    /**
     * Sets the lineTimeStamp value for this CtiLine.
     * 
     * @param lineTimeStamp
     */
    public void setLineTimeStamp(org.apache.axis.types.UnsignedInt lineTimeStamp) {
        this.lineTimeStamp = lineTimeStamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CtiLine)) return false;
        CtiLine other = (CtiLine) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dirNumber==null && other.getDirNumber()==null) || 
             (this.dirNumber!=null &&
              this.dirNumber.equals(other.getDirNumber()))) &&
            ((this.lineStatus==null && other.getLineStatus()==null) || 
             (this.lineStatus!=null &&
              this.lineStatus.equals(other.getLineStatus()))) &&
            ((this.lineStatusReason==null && other.getLineStatusReason()==null) || 
             (this.lineStatusReason!=null &&
              this.lineStatusReason.equals(other.getLineStatusReason()))) &&
            ((this.lineTimeStamp==null && other.getLineTimeStamp()==null) || 
             (this.lineTimeStamp!=null &&
              this.lineTimeStamp.equals(other.getLineTimeStamp())));
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
        if (getDirNumber() != null) {
            _hashCode += getDirNumber().hashCode();
        }
        if (getLineStatus() != null) {
            _hashCode += getLineStatus().hashCode();
        }
        if (getLineStatusReason() != null) {
            _hashCode += getLineStatusReason().hashCode();
        }
        if (getLineTimeStamp() != null) {
            _hashCode += getLineTimeStamp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CtiLine.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiLine"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dirNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DirNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "LineStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiStatus"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineStatusReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "LineStatusReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineTimeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "LineTimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
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
