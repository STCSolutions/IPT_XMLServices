/**
 * CtiNode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class CtiNode  implements java.io.Serializable {
    private com.cisco.schemas.ast.soap.RisReturnCode returnCode;

    private java.lang.String name;

    private boolean noChange;

    private com.cisco.schemas.ast.soap.CtiItem[] ctiItems;

    public CtiNode() {
    }

    public CtiNode(
           com.cisco.schemas.ast.soap.RisReturnCode returnCode,
           java.lang.String name,
           boolean noChange,
           com.cisco.schemas.ast.soap.CtiItem[] ctiItems) {
           this.returnCode = returnCode;
           this.name = name;
           this.noChange = noChange;
           this.ctiItems = ctiItems;
    }


    /**
     * Gets the returnCode value for this CtiNode.
     * 
     * @return returnCode
     */
    public com.cisco.schemas.ast.soap.RisReturnCode getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this CtiNode.
     * 
     * @param returnCode
     */
    public void setReturnCode(com.cisco.schemas.ast.soap.RisReturnCode returnCode) {
        this.returnCode = returnCode;
    }


    /**
     * Gets the name value for this CtiNode.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this CtiNode.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the noChange value for this CtiNode.
     * 
     * @return noChange
     */
    public boolean isNoChange() {
        return noChange;
    }


    /**
     * Sets the noChange value for this CtiNode.
     * 
     * @param noChange
     */
    public void setNoChange(boolean noChange) {
        this.noChange = noChange;
    }


    /**
     * Gets the ctiItems value for this CtiNode.
     * 
     * @return ctiItems
     */
    public com.cisco.schemas.ast.soap.CtiItem[] getCtiItems() {
        return ctiItems;
    }


    /**
     * Sets the ctiItems value for this CtiNode.
     * 
     * @param ctiItems
     */
    public void setCtiItems(com.cisco.schemas.ast.soap.CtiItem[] ctiItems) {
        this.ctiItems = ctiItems;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CtiNode)) return false;
        CtiNode other = (CtiNode) obj;
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
            ((this.ctiItems==null && other.getCtiItems()==null) || 
             (this.ctiItems!=null &&
              java.util.Arrays.equals(this.ctiItems, other.getCtiItems())));
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
        if (getCtiItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCtiItems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCtiItems(), i);
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
        new org.apache.axis.description.TypeDesc(CtiNode.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiNode"));
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
        elemField.setFieldName("ctiItems");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiItems"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiItem"));
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
