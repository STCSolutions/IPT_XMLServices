/**
 * SelectAppItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class SelectAppItem  implements java.io.Serializable {
    private java.lang.String appItem;

    public SelectAppItem() {
    }

    public SelectAppItem(
           java.lang.String appItem) {
           this.appItem = appItem;
    }


    /**
     * Gets the appItem value for this SelectAppItem.
     * 
     * @return appItem
     */
    public java.lang.String getAppItem() {
        return appItem;
    }


    /**
     * Sets the appItem value for this SelectAppItem.
     * 
     * @param appItem
     */
    public void setAppItem(java.lang.String appItem) {
        this.appItem = appItem;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectAppItem)) return false;
        SelectAppItem other = (SelectAppItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.appItem==null && other.getAppItem()==null) || 
             (this.appItem!=null &&
              this.appItem.equals(other.getAppItem())));
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
        if (getAppItem() != null) {
            _hashCode += getAppItem().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SelectAppItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectAppItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appItem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "AppItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
