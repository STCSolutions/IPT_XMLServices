/**
 * SelectCtiItemResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class SelectCtiItemResult  implements java.io.Serializable {
    private org.apache.axis.types.UnsignedInt totalItemsFound;

    private com.cisco.schemas.ast.soap.CtiNode[] ctiNodes;

    public SelectCtiItemResult() {
    }

    public SelectCtiItemResult(
           org.apache.axis.types.UnsignedInt totalItemsFound,
           com.cisco.schemas.ast.soap.CtiNode[] ctiNodes) {
           this.totalItemsFound = totalItemsFound;
           this.ctiNodes = ctiNodes;
    }


    /**
     * Gets the totalItemsFound value for this SelectCtiItemResult.
     * 
     * @return totalItemsFound
     */
    public org.apache.axis.types.UnsignedInt getTotalItemsFound() {
        return totalItemsFound;
    }


    /**
     * Sets the totalItemsFound value for this SelectCtiItemResult.
     * 
     * @param totalItemsFound
     */
    public void setTotalItemsFound(org.apache.axis.types.UnsignedInt totalItemsFound) {
        this.totalItemsFound = totalItemsFound;
    }


    /**
     * Gets the ctiNodes value for this SelectCtiItemResult.
     * 
     * @return ctiNodes
     */
    public com.cisco.schemas.ast.soap.CtiNode[] getCtiNodes() {
        return ctiNodes;
    }


    /**
     * Sets the ctiNodes value for this SelectCtiItemResult.
     * 
     * @param ctiNodes
     */
    public void setCtiNodes(com.cisco.schemas.ast.soap.CtiNode[] ctiNodes) {
        this.ctiNodes = ctiNodes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectCtiItemResult)) return false;
        SelectCtiItemResult other = (SelectCtiItemResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.totalItemsFound==null && other.getTotalItemsFound()==null) || 
             (this.totalItemsFound!=null &&
              this.totalItemsFound.equals(other.getTotalItemsFound()))) &&
            ((this.ctiNodes==null && other.getCtiNodes()==null) || 
             (this.ctiNodes!=null &&
              java.util.Arrays.equals(this.ctiNodes, other.getCtiNodes())));
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
        if (getTotalItemsFound() != null) {
            _hashCode += getTotalItemsFound().hashCode();
        }
        if (getCtiNodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCtiNodes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCtiNodes(), i);
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
        new org.apache.axis.description.TypeDesc(SelectCtiItemResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectCtiItemResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalItemsFound");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "TotalItemsFound"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ctiNodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiNodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiNode"));
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
