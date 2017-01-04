/**
 * ServerInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class ServerInformation  implements java.io.Serializable {
    private java.lang.String hostName;

    private java.lang.String osName;

    private java.lang.String osVersion;

    private java.lang.String osArch;

    private java.lang.String javaRuntimeVersion;

    private java.lang.String javaVmVendor;

    private java.lang.String callManagerVersion;

    private java.lang.String active_Versions;

    private java.lang.String in_Active_Versions;

    public ServerInformation() {
    }

    public ServerInformation(
           java.lang.String hostName,
           java.lang.String osName,
           java.lang.String osVersion,
           java.lang.String osArch,
           java.lang.String javaRuntimeVersion,
           java.lang.String javaVmVendor,
           java.lang.String callManagerVersion,
           java.lang.String active_Versions,
           java.lang.String in_Active_Versions) {
           this.hostName = hostName;
           this.osName = osName;
           this.osVersion = osVersion;
           this.osArch = osArch;
           this.javaRuntimeVersion = javaRuntimeVersion;
           this.javaVmVendor = javaVmVendor;
           this.callManagerVersion = callManagerVersion;
           this.active_Versions = active_Versions;
           this.in_Active_Versions = in_Active_Versions;
    }


    /**
     * Gets the hostName value for this ServerInformation.
     * 
     * @return hostName
     */
    public java.lang.String getHostName() {
        return hostName;
    }


    /**
     * Sets the hostName value for this ServerInformation.
     * 
     * @param hostName
     */
    public void setHostName(java.lang.String hostName) {
        this.hostName = hostName;
    }


    /**
     * Gets the osName value for this ServerInformation.
     * 
     * @return osName
     */
    public java.lang.String getOsName() {
        return osName;
    }


    /**
     * Sets the osName value for this ServerInformation.
     * 
     * @param osName
     */
    public void setOsName(java.lang.String osName) {
        this.osName = osName;
    }


    /**
     * Gets the osVersion value for this ServerInformation.
     * 
     * @return osVersion
     */
    public java.lang.String getOsVersion() {
        return osVersion;
    }


    /**
     * Sets the osVersion value for this ServerInformation.
     * 
     * @param osVersion
     */
    public void setOsVersion(java.lang.String osVersion) {
        this.osVersion = osVersion;
    }


    /**
     * Gets the osArch value for this ServerInformation.
     * 
     * @return osArch
     */
    public java.lang.String getOsArch() {
        return osArch;
    }


    /**
     * Sets the osArch value for this ServerInformation.
     * 
     * @param osArch
     */
    public void setOsArch(java.lang.String osArch) {
        this.osArch = osArch;
    }


    /**
     * Gets the javaRuntimeVersion value for this ServerInformation.
     * 
     * @return javaRuntimeVersion
     */
    public java.lang.String getJavaRuntimeVersion() {
        return javaRuntimeVersion;
    }


    /**
     * Sets the javaRuntimeVersion value for this ServerInformation.
     * 
     * @param javaRuntimeVersion
     */
    public void setJavaRuntimeVersion(java.lang.String javaRuntimeVersion) {
        this.javaRuntimeVersion = javaRuntimeVersion;
    }


    /**
     * Gets the javaVmVendor value for this ServerInformation.
     * 
     * @return javaVmVendor
     */
    public java.lang.String getJavaVmVendor() {
        return javaVmVendor;
    }


    /**
     * Sets the javaVmVendor value for this ServerInformation.
     * 
     * @param javaVmVendor
     */
    public void setJavaVmVendor(java.lang.String javaVmVendor) {
        this.javaVmVendor = javaVmVendor;
    }


    /**
     * Gets the callManagerVersion value for this ServerInformation.
     * 
     * @return callManagerVersion
     */
    public java.lang.String getCallManagerVersion() {
        return callManagerVersion;
    }


    /**
     * Sets the callManagerVersion value for this ServerInformation.
     * 
     * @param callManagerVersion
     */
    public void setCallManagerVersion(java.lang.String callManagerVersion) {
        this.callManagerVersion = callManagerVersion;
    }


    /**
     * Gets the active_Versions value for this ServerInformation.
     * 
     * @return active_Versions
     */
    public java.lang.String getActive_Versions() {
        return active_Versions;
    }


    /**
     * Sets the active_Versions value for this ServerInformation.
     * 
     * @param active_Versions
     */
    public void setActive_Versions(java.lang.String active_Versions) {
        this.active_Versions = active_Versions;
    }


    /**
     * Gets the in_Active_Versions value for this ServerInformation.
     * 
     * @return in_Active_Versions
     */
    public java.lang.String getIn_Active_Versions() {
        return in_Active_Versions;
    }


    /**
     * Sets the in_Active_Versions value for this ServerInformation.
     * 
     * @param in_Active_Versions
     */
    public void setIn_Active_Versions(java.lang.String in_Active_Versions) {
        this.in_Active_Versions = in_Active_Versions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServerInformation)) return false;
        ServerInformation other = (ServerInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.hostName==null && other.getHostName()==null) || 
             (this.hostName!=null &&
              this.hostName.equals(other.getHostName()))) &&
            ((this.osName==null && other.getOsName()==null) || 
             (this.osName!=null &&
              this.osName.equals(other.getOsName()))) &&
            ((this.osVersion==null && other.getOsVersion()==null) || 
             (this.osVersion!=null &&
              this.osVersion.equals(other.getOsVersion()))) &&
            ((this.osArch==null && other.getOsArch()==null) || 
             (this.osArch!=null &&
              this.osArch.equals(other.getOsArch()))) &&
            ((this.javaRuntimeVersion==null && other.getJavaRuntimeVersion()==null) || 
             (this.javaRuntimeVersion!=null &&
              this.javaRuntimeVersion.equals(other.getJavaRuntimeVersion()))) &&
            ((this.javaVmVendor==null && other.getJavaVmVendor()==null) || 
             (this.javaVmVendor!=null &&
              this.javaVmVendor.equals(other.getJavaVmVendor()))) &&
            ((this.callManagerVersion==null && other.getCallManagerVersion()==null) || 
             (this.callManagerVersion!=null &&
              this.callManagerVersion.equals(other.getCallManagerVersion()))) &&
            ((this.active_Versions==null && other.getActive_Versions()==null) || 
             (this.active_Versions!=null &&
              this.active_Versions.equals(other.getActive_Versions()))) &&
            ((this.in_Active_Versions==null && other.getIn_Active_Versions()==null) || 
             (this.in_Active_Versions!=null &&
              this.in_Active_Versions.equals(other.getIn_Active_Versions())));
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
        if (getHostName() != null) {
            _hashCode += getHostName().hashCode();
        }
        if (getOsName() != null) {
            _hashCode += getOsName().hashCode();
        }
        if (getOsVersion() != null) {
            _hashCode += getOsVersion().hashCode();
        }
        if (getOsArch() != null) {
            _hashCode += getOsArch().hashCode();
        }
        if (getJavaRuntimeVersion() != null) {
            _hashCode += getJavaRuntimeVersion().hashCode();
        }
        if (getJavaVmVendor() != null) {
            _hashCode += getJavaVmVendor().hashCode();
        }
        if (getCallManagerVersion() != null) {
            _hashCode += getCallManagerVersion().hashCode();
        }
        if (getActive_Versions() != null) {
            _hashCode += getActive_Versions().hashCode();
        }
        if (getIn_Active_Versions() != null) {
            _hashCode += getIn_Active_Versions().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServerInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ServerInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hostName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "HostName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("osName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "os-name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("osVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "os-version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("osArch");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "os-arch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("javaRuntimeVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "java-runtime-version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("javaVmVendor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "java-vm-vendor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callManagerVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "call-manager-version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("active_Versions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Active_Versions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("in_Active_Versions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "In_Active_Versions"));
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
