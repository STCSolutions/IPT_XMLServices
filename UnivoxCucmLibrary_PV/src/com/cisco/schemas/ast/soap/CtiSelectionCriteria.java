/**
 * CtiSelectionCriteria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class CtiSelectionCriteria  implements java.io.Serializable {
    private org.apache.axis.types.UnsignedInt maxReturnedItems;

    private com.cisco.schemas.ast.soap.CtiMgrClass ctiMgrClass;

    private com.cisco.schemas.ast.soap.CtiStatus status;

    private java.lang.String nodeName;

    private com.cisco.schemas.ast.soap.CtiSelectAppBy selectAppBy;

    private com.cisco.schemas.ast.soap.SelectAppItem[] appItems;

    private com.cisco.schemas.ast.soap.SelectDevName[] devNames;

    private com.cisco.schemas.ast.soap.SelectDirNumber[] dirNumbers;

    public CtiSelectionCriteria() {
    }

    public CtiSelectionCriteria(
           org.apache.axis.types.UnsignedInt maxReturnedItems,
           com.cisco.schemas.ast.soap.CtiMgrClass ctiMgrClass,
           com.cisco.schemas.ast.soap.CtiStatus status,
           java.lang.String nodeName,
           com.cisco.schemas.ast.soap.CtiSelectAppBy selectAppBy,
           com.cisco.schemas.ast.soap.SelectAppItem[] appItems,
           com.cisco.schemas.ast.soap.SelectDevName[] devNames,
           com.cisco.schemas.ast.soap.SelectDirNumber[] dirNumbers) {
           this.maxReturnedItems = maxReturnedItems;
           this.ctiMgrClass = ctiMgrClass;
           this.status = status;
           this.nodeName = nodeName;
           this.selectAppBy = selectAppBy;
           this.appItems = appItems;
           this.devNames = devNames;
           this.dirNumbers = dirNumbers;
    }


    /**
     * Gets the maxReturnedItems value for this CtiSelectionCriteria.
     * 
     * @return maxReturnedItems
     */
    public org.apache.axis.types.UnsignedInt getMaxReturnedItems() {
        return maxReturnedItems;
    }


    /**
     * Sets the maxReturnedItems value for this CtiSelectionCriteria.
     * 
     * @param maxReturnedItems
     */
    public void setMaxReturnedItems(org.apache.axis.types.UnsignedInt maxReturnedItems) {
        this.maxReturnedItems = maxReturnedItems;
    }


    /**
     * Gets the ctiMgrClass value for this CtiSelectionCriteria.
     * 
     * @return ctiMgrClass
     */
    public com.cisco.schemas.ast.soap.CtiMgrClass getCtiMgrClass() {
        return ctiMgrClass;
    }


    /**
     * Sets the ctiMgrClass value for this CtiSelectionCriteria.
     * 
     * @param ctiMgrClass
     */
    public void setCtiMgrClass(com.cisco.schemas.ast.soap.CtiMgrClass ctiMgrClass) {
        this.ctiMgrClass = ctiMgrClass;
    }


    /**
     * Gets the status value for this CtiSelectionCriteria.
     * 
     * @return status
     */
    public com.cisco.schemas.ast.soap.CtiStatus getStatus() {
        return status;
    }


    /**
     * Sets the status value for this CtiSelectionCriteria.
     * 
     * @param status
     */
    public void setStatus(com.cisco.schemas.ast.soap.CtiStatus status) {
        this.status = status;
    }


    /**
     * Gets the nodeName value for this CtiSelectionCriteria.
     * 
     * @return nodeName
     */
    public java.lang.String getNodeName() {
        return nodeName;
    }


    /**
     * Sets the nodeName value for this CtiSelectionCriteria.
     * 
     * @param nodeName
     */
    public void setNodeName(java.lang.String nodeName) {
        this.nodeName = nodeName;
    }


    /**
     * Gets the selectAppBy value for this CtiSelectionCriteria.
     * 
     * @return selectAppBy
     */
    public com.cisco.schemas.ast.soap.CtiSelectAppBy getSelectAppBy() {
        return selectAppBy;
    }


    /**
     * Sets the selectAppBy value for this CtiSelectionCriteria.
     * 
     * @param selectAppBy
     */
    public void setSelectAppBy(com.cisco.schemas.ast.soap.CtiSelectAppBy selectAppBy) {
        this.selectAppBy = selectAppBy;
    }


    /**
     * Gets the appItems value for this CtiSelectionCriteria.
     * 
     * @return appItems
     */
    public com.cisco.schemas.ast.soap.SelectAppItem[] getAppItems() {
        return appItems;
    }


    /**
     * Sets the appItems value for this CtiSelectionCriteria.
     * 
     * @param appItems
     */
    public void setAppItems(com.cisco.schemas.ast.soap.SelectAppItem[] appItems) {
        this.appItems = appItems;
    }


    /**
     * Gets the devNames value for this CtiSelectionCriteria.
     * 
     * @return devNames
     */
    public com.cisco.schemas.ast.soap.SelectDevName[] getDevNames() {
        return devNames;
    }


    /**
     * Sets the devNames value for this CtiSelectionCriteria.
     * 
     * @param devNames
     */
    public void setDevNames(com.cisco.schemas.ast.soap.SelectDevName[] devNames) {
        this.devNames = devNames;
    }


    /**
     * Gets the dirNumbers value for this CtiSelectionCriteria.
     * 
     * @return dirNumbers
     */
    public com.cisco.schemas.ast.soap.SelectDirNumber[] getDirNumbers() {
        return dirNumbers;
    }


    /**
     * Sets the dirNumbers value for this CtiSelectionCriteria.
     * 
     * @param dirNumbers
     */
    public void setDirNumbers(com.cisco.schemas.ast.soap.SelectDirNumber[] dirNumbers) {
        this.dirNumbers = dirNumbers;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CtiSelectionCriteria)) return false;
        CtiSelectionCriteria other = (CtiSelectionCriteria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.maxReturnedItems==null && other.getMaxReturnedItems()==null) || 
             (this.maxReturnedItems!=null &&
              this.maxReturnedItems.equals(other.getMaxReturnedItems()))) &&
            ((this.ctiMgrClass==null && other.getCtiMgrClass()==null) || 
             (this.ctiMgrClass!=null &&
              this.ctiMgrClass.equals(other.getCtiMgrClass()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.nodeName==null && other.getNodeName()==null) || 
             (this.nodeName!=null &&
              this.nodeName.equals(other.getNodeName()))) &&
            ((this.selectAppBy==null && other.getSelectAppBy()==null) || 
             (this.selectAppBy!=null &&
              this.selectAppBy.equals(other.getSelectAppBy()))) &&
            ((this.appItems==null && other.getAppItems()==null) || 
             (this.appItems!=null &&
              java.util.Arrays.equals(this.appItems, other.getAppItems()))) &&
            ((this.devNames==null && other.getDevNames()==null) || 
             (this.devNames!=null &&
              java.util.Arrays.equals(this.devNames, other.getDevNames()))) &&
            ((this.dirNumbers==null && other.getDirNumbers()==null) || 
             (this.dirNumbers!=null &&
              java.util.Arrays.equals(this.dirNumbers, other.getDirNumbers())));
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
        if (getMaxReturnedItems() != null) {
            _hashCode += getMaxReturnedItems().hashCode();
        }
        if (getCtiMgrClass() != null) {
            _hashCode += getCtiMgrClass().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getNodeName() != null) {
            _hashCode += getNodeName().hashCode();
        }
        if (getSelectAppBy() != null) {
            _hashCode += getSelectAppBy().hashCode();
        }
        if (getAppItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAppItems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAppItems(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDevNames() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDevNames());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDevNames(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDirNumbers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDirNumbers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDirNumbers(), i);
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
        new org.apache.axis.description.TypeDesc(CtiSelectionCriteria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiSelectionCriteria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxReturnedItems");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "MaxReturnedItems"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ctiMgrClass");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiMgrClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiMgrClass"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiStatus"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "NodeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectAppBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectAppBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiSelectAppBy"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appItems");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "AppItems"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectAppItem"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("devNames");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DevNames"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectDevName"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dirNumbers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DirNumbers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectDirNumber"));
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
