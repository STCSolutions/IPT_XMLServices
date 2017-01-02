/**
 * H323Trunk.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class H323Trunk  implements java.io.Serializable {
    private java.lang.String configName;

    private java.lang.String techPrefix;

    private java.lang.String zone;

    private java.lang.String remoteCmServer1;

    private java.lang.String remoteCmServer2;

    private java.lang.String remoteCmServer3;

    private java.lang.String altGkList;

    private java.lang.String activeGk;

    private java.lang.String callSignalAddr;

    private java.lang.String rasAddr;

    public H323Trunk() {
    }

    public H323Trunk(
           java.lang.String configName,
           java.lang.String techPrefix,
           java.lang.String zone,
           java.lang.String remoteCmServer1,
           java.lang.String remoteCmServer2,
           java.lang.String remoteCmServer3,
           java.lang.String altGkList,
           java.lang.String activeGk,
           java.lang.String callSignalAddr,
           java.lang.String rasAddr) {
           this.configName = configName;
           this.techPrefix = techPrefix;
           this.zone = zone;
           this.remoteCmServer1 = remoteCmServer1;
           this.remoteCmServer2 = remoteCmServer2;
           this.remoteCmServer3 = remoteCmServer3;
           this.altGkList = altGkList;
           this.activeGk = activeGk;
           this.callSignalAddr = callSignalAddr;
           this.rasAddr = rasAddr;
    }


    /**
     * Gets the configName value for this H323Trunk.
     * 
     * @return configName
     */
    public java.lang.String getConfigName() {
        return configName;
    }


    /**
     * Sets the configName value for this H323Trunk.
     * 
     * @param configName
     */
    public void setConfigName(java.lang.String configName) {
        this.configName = configName;
    }


    /**
     * Gets the techPrefix value for this H323Trunk.
     * 
     * @return techPrefix
     */
    public java.lang.String getTechPrefix() {
        return techPrefix;
    }


    /**
     * Sets the techPrefix value for this H323Trunk.
     * 
     * @param techPrefix
     */
    public void setTechPrefix(java.lang.String techPrefix) {
        this.techPrefix = techPrefix;
    }


    /**
     * Gets the zone value for this H323Trunk.
     * 
     * @return zone
     */
    public java.lang.String getZone() {
        return zone;
    }


    /**
     * Sets the zone value for this H323Trunk.
     * 
     * @param zone
     */
    public void setZone(java.lang.String zone) {
        this.zone = zone;
    }


    /**
     * Gets the remoteCmServer1 value for this H323Trunk.
     * 
     * @return remoteCmServer1
     */
    public java.lang.String getRemoteCmServer1() {
        return remoteCmServer1;
    }


    /**
     * Sets the remoteCmServer1 value for this H323Trunk.
     * 
     * @param remoteCmServer1
     */
    public void setRemoteCmServer1(java.lang.String remoteCmServer1) {
        this.remoteCmServer1 = remoteCmServer1;
    }


    /**
     * Gets the remoteCmServer2 value for this H323Trunk.
     * 
     * @return remoteCmServer2
     */
    public java.lang.String getRemoteCmServer2() {
        return remoteCmServer2;
    }


    /**
     * Sets the remoteCmServer2 value for this H323Trunk.
     * 
     * @param remoteCmServer2
     */
    public void setRemoteCmServer2(java.lang.String remoteCmServer2) {
        this.remoteCmServer2 = remoteCmServer2;
    }


    /**
     * Gets the remoteCmServer3 value for this H323Trunk.
     * 
     * @return remoteCmServer3
     */
    public java.lang.String getRemoteCmServer3() {
        return remoteCmServer3;
    }


    /**
     * Sets the remoteCmServer3 value for this H323Trunk.
     * 
     * @param remoteCmServer3
     */
    public void setRemoteCmServer3(java.lang.String remoteCmServer3) {
        this.remoteCmServer3 = remoteCmServer3;
    }


    /**
     * Gets the altGkList value for this H323Trunk.
     * 
     * @return altGkList
     */
    public java.lang.String getAltGkList() {
        return altGkList;
    }


    /**
     * Sets the altGkList value for this H323Trunk.
     * 
     * @param altGkList
     */
    public void setAltGkList(java.lang.String altGkList) {
        this.altGkList = altGkList;
    }


    /**
     * Gets the activeGk value for this H323Trunk.
     * 
     * @return activeGk
     */
    public java.lang.String getActiveGk() {
        return activeGk;
    }


    /**
     * Sets the activeGk value for this H323Trunk.
     * 
     * @param activeGk
     */
    public void setActiveGk(java.lang.String activeGk) {
        this.activeGk = activeGk;
    }


    /**
     * Gets the callSignalAddr value for this H323Trunk.
     * 
     * @return callSignalAddr
     */
    public java.lang.String getCallSignalAddr() {
        return callSignalAddr;
    }


    /**
     * Sets the callSignalAddr value for this H323Trunk.
     * 
     * @param callSignalAddr
     */
    public void setCallSignalAddr(java.lang.String callSignalAddr) {
        this.callSignalAddr = callSignalAddr;
    }


    /**
     * Gets the rasAddr value for this H323Trunk.
     * 
     * @return rasAddr
     */
    public java.lang.String getRasAddr() {
        return rasAddr;
    }


    /**
     * Sets the rasAddr value for this H323Trunk.
     * 
     * @param rasAddr
     */
    public void setRasAddr(java.lang.String rasAddr) {
        this.rasAddr = rasAddr;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof H323Trunk)) return false;
        H323Trunk other = (H323Trunk) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.configName==null && other.getConfigName()==null) || 
             (this.configName!=null &&
              this.configName.equals(other.getConfigName()))) &&
            ((this.techPrefix==null && other.getTechPrefix()==null) || 
             (this.techPrefix!=null &&
              this.techPrefix.equals(other.getTechPrefix()))) &&
            ((this.zone==null && other.getZone()==null) || 
             (this.zone!=null &&
              this.zone.equals(other.getZone()))) &&
            ((this.remoteCmServer1==null && other.getRemoteCmServer1()==null) || 
             (this.remoteCmServer1!=null &&
              this.remoteCmServer1.equals(other.getRemoteCmServer1()))) &&
            ((this.remoteCmServer2==null && other.getRemoteCmServer2()==null) || 
             (this.remoteCmServer2!=null &&
              this.remoteCmServer2.equals(other.getRemoteCmServer2()))) &&
            ((this.remoteCmServer3==null && other.getRemoteCmServer3()==null) || 
             (this.remoteCmServer3!=null &&
              this.remoteCmServer3.equals(other.getRemoteCmServer3()))) &&
            ((this.altGkList==null && other.getAltGkList()==null) || 
             (this.altGkList!=null &&
              this.altGkList.equals(other.getAltGkList()))) &&
            ((this.activeGk==null && other.getActiveGk()==null) || 
             (this.activeGk!=null &&
              this.activeGk.equals(other.getActiveGk()))) &&
            ((this.callSignalAddr==null && other.getCallSignalAddr()==null) || 
             (this.callSignalAddr!=null &&
              this.callSignalAddr.equals(other.getCallSignalAddr()))) &&
            ((this.rasAddr==null && other.getRasAddr()==null) || 
             (this.rasAddr!=null &&
              this.rasAddr.equals(other.getRasAddr())));
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
        if (getConfigName() != null) {
            _hashCode += getConfigName().hashCode();
        }
        if (getTechPrefix() != null) {
            _hashCode += getTechPrefix().hashCode();
        }
        if (getZone() != null) {
            _hashCode += getZone().hashCode();
        }
        if (getRemoteCmServer1() != null) {
            _hashCode += getRemoteCmServer1().hashCode();
        }
        if (getRemoteCmServer2() != null) {
            _hashCode += getRemoteCmServer2().hashCode();
        }
        if (getRemoteCmServer3() != null) {
            _hashCode += getRemoteCmServer3().hashCode();
        }
        if (getAltGkList() != null) {
            _hashCode += getAltGkList().hashCode();
        }
        if (getActiveGk() != null) {
            _hashCode += getActiveGk().hashCode();
        }
        if (getCallSignalAddr() != null) {
            _hashCode += getCallSignalAddr().hashCode();
        }
        if (getRasAddr() != null) {
            _hashCode += getRasAddr().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(H323Trunk.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "H323Trunk"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("configName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ConfigName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("techPrefix");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "TechPrefix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Zone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remoteCmServer1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "RemoteCmServer1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remoteCmServer2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "RemoteCmServer2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remoteCmServer3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "RemoteCmServer3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("altGkList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "AltGkList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activeGk");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ActiveGk"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callSignalAddr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CallSignalAddr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rasAddr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "RasAddr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
