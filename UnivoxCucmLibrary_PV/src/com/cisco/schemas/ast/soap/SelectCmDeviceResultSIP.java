/**
 * SelectCmDeviceResultSIP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class SelectCmDeviceResultSIP  implements java.io.Serializable {
    private org.apache.axis.types.UnsignedInt totalDevicesFound;

    private com.cisco.schemas.ast.soap.CmNodeSIP[] cmNodes;

    public SelectCmDeviceResultSIP() {
    }

    public SelectCmDeviceResultSIP(
           org.apache.axis.types.UnsignedInt totalDevicesFound,
           com.cisco.schemas.ast.soap.CmNodeSIP[] cmNodes) {
           this.totalDevicesFound = totalDevicesFound;
           this.cmNodes = cmNodes;
    }


    /**
     * Gets the totalDevicesFound value for this SelectCmDeviceResultSIP.
     * 
     * @return totalDevicesFound
     */
    public org.apache.axis.types.UnsignedInt getTotalDevicesFound() {
        return totalDevicesFound;
    }


    /**
     * Sets the totalDevicesFound value for this SelectCmDeviceResultSIP.
     * 
     * @param totalDevicesFound
     */
    public void setTotalDevicesFound(org.apache.axis.types.UnsignedInt totalDevicesFound) {
        this.totalDevicesFound = totalDevicesFound;
    }


    /**
     * Gets the cmNodes value for this SelectCmDeviceResultSIP.
     * 
     * @return cmNodes
     */
    public com.cisco.schemas.ast.soap.CmNodeSIP[] getCmNodes() {
        return cmNodes;
    }


    /**
     * Sets the cmNodes value for this SelectCmDeviceResultSIP.
     * 
     * @param cmNodes
     */
    public void setCmNodes(com.cisco.schemas.ast.soap.CmNodeSIP[] cmNodes) {
        this.cmNodes = cmNodes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectCmDeviceResultSIP)) return false;
        SelectCmDeviceResultSIP other = (SelectCmDeviceResultSIP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.totalDevicesFound==null && other.getTotalDevicesFound()==null) || 
             (this.totalDevicesFound!=null &&
              this.totalDevicesFound.equals(other.getTotalDevicesFound()))) &&
            ((this.cmNodes==null && other.getCmNodes()==null) || 
             (this.cmNodes!=null &&
              java.util.Arrays.equals(this.cmNodes, other.getCmNodes())));
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
        if (getTotalDevicesFound() != null) {
            _hashCode += getTotalDevicesFound().hashCode();
        }
        if (getCmNodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCmNodes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCmNodes(), i);
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
        new org.apache.axis.description.TypeDesc(SelectCmDeviceResultSIP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectCmDeviceResultSIP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalDevicesFound");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "TotalDevicesFound"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmNodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmNodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmNodeSIP"));
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
