/**
 * CmSelectionCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class CmSelectionCriteria  implements java.io.Serializable {
    private org.apache.axis.types.UnsignedInt maxReturnedDevices;

    private java.lang.String _class;

    private org.apache.axis.types.UnsignedInt model;

    private java.lang.String status;

    private java.lang.String nodeName;

    private java.lang.String selectBy;

    private com.cisco.schemas.ast.soap.SelectItem[] selectItems;

    public CmSelectionCriteria() {
    }

    public CmSelectionCriteria(
           org.apache.axis.types.UnsignedInt maxReturnedDevices,
           java.lang.String _class,
           org.apache.axis.types.UnsignedInt model,
           java.lang.String status,
           java.lang.String nodeName,
           java.lang.String selectBy,
           com.cisco.schemas.ast.soap.SelectItem[] selectItems) {
           this.maxReturnedDevices = maxReturnedDevices;
           this._class = _class;
           this.model = model;
           this.status = status;
           this.nodeName = nodeName;
           this.selectBy = selectBy;
           this.selectItems = selectItems;
    }


    /**
     * Gets the maxReturnedDevices value for this CmSelectionCriteria.
     * 
     * @return maxReturnedDevices
     */
    public org.apache.axis.types.UnsignedInt getMaxReturnedDevices() {
        return maxReturnedDevices;
    }


    /**
     * Sets the maxReturnedDevices value for this CmSelectionCriteria.
     * 
     * @param maxReturnedDevices
     */
    public void setMaxReturnedDevices(org.apache.axis.types.UnsignedInt maxReturnedDevices) {
        this.maxReturnedDevices = maxReturnedDevices;
    }


    /**
     * Gets the _class value for this CmSelectionCriteria.
     * 
     * @return _class
     */
    public java.lang.String get_class() {
        return _class;
    }


    /**
     * Sets the _class value for this CmSelectionCriteria.
     * 
     * @param _class
     */
    public void set_class(java.lang.String _class) {
        this._class = _class;
    }


    /**
     * Gets the model value for this CmSelectionCriteria.
     * 
     * @return model
     */
    public org.apache.axis.types.UnsignedInt getModel() {
        return model;
    }


    /**
     * Sets the model value for this CmSelectionCriteria.
     * 
     * @param model
     */
    public void setModel(org.apache.axis.types.UnsignedInt model) {
        this.model = model;
    }


    /**
     * Gets the status value for this CmSelectionCriteria.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this CmSelectionCriteria.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the nodeName value for this CmSelectionCriteria.
     * 
     * @return nodeName
     */
    public java.lang.String getNodeName() {
        return nodeName;
    }


    /**
     * Sets the nodeName value for this CmSelectionCriteria.
     * 
     * @param nodeName
     */
    public void setNodeName(java.lang.String nodeName) {
        this.nodeName = nodeName;
    }


    /**
     * Gets the selectBy value for this CmSelectionCriteria.
     * 
     * @return selectBy
     */
    public java.lang.String getSelectBy() {
        return selectBy;
    }


    /**
     * Sets the selectBy value for this CmSelectionCriteria.
     * 
     * @param selectBy
     */
    public void setSelectBy(java.lang.String selectBy) {
        this.selectBy = selectBy;
    }


    /**
     * Gets the selectItems value for this CmSelectionCriteria.
     * 
     * @return selectItems
     */
    public com.cisco.schemas.ast.soap.SelectItem[] getSelectItems() {
        return selectItems;
    }


    /**
     * Sets the selectItems value for this CmSelectionCriteria.
     * 
     * @param selectItems
     */
    public void setSelectItems(com.cisco.schemas.ast.soap.SelectItem[] selectItems) {
        this.selectItems = selectItems;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CmSelectionCriteria)) return false;
        CmSelectionCriteria other = (CmSelectionCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.maxReturnedDevices==null && other.getMaxReturnedDevices()==null) || 
             (this.maxReturnedDevices!=null &&
              this.maxReturnedDevices.equals(other.getMaxReturnedDevices()))) &&
            ((this._class==null && other.get_class()==null) || 
             (this._class!=null &&
              this._class.equals(other.get_class()))) &&
            ((this.model==null && other.getModel()==null) || 
             (this.model!=null &&
              this.model.equals(other.getModel()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.nodeName==null && other.getNodeName()==null) || 
             (this.nodeName!=null &&
              this.nodeName.equals(other.getNodeName()))) &&
            ((this.selectBy==null && other.getSelectBy()==null) || 
             (this.selectBy!=null &&
              this.selectBy.equals(other.getSelectBy()))) &&
            ((this.selectItems==null && other.getSelectItems()==null) || 
             (this.selectItems!=null &&
              java.util.Arrays.equals(this.selectItems, other.getSelectItems())));
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
        if (getMaxReturnedDevices() != null) {
            _hashCode += getMaxReturnedDevices().hashCode();
        }
        if (get_class() != null) {
            _hashCode += get_class().hashCode();
        }
        if (getModel() != null) {
            _hashCode += getModel().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getNodeName() != null) {
            _hashCode += getNodeName().hashCode();
        }
        if (getSelectBy() != null) {
            _hashCode += getSelectBy().hashCode();
        }
        if (getSelectItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSelectItems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSelectItems(), i);
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
        new org.apache.axis.description.TypeDesc(CmSelectionCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmSelectionCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxReturnedDevices");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "MaxReturnedDevices"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_class");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Class"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("model");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Model"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "NodeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectItems");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectItems"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectItem"));
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
