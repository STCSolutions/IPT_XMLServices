/**
 * DeviceClass.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class DeviceClass implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected DeviceClass(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Any = "Any";
    public static final java.lang.String _Phone = "Phone";
    public static final java.lang.String _Gateway = "Gateway";
    public static final java.lang.String _H323 = "H323";
    public static final java.lang.String _Cti = "Cti";
    public static final java.lang.String _VoiceMail = "VoiceMail";
    public static final java.lang.String _MediaResources = "MediaResources";
    public static final java.lang.String _Unknown = "Unknown";
    public static final DeviceClass Any = new DeviceClass(_Any);
    public static final DeviceClass Phone = new DeviceClass(_Phone);
    public static final DeviceClass Gateway = new DeviceClass(_Gateway);
    public static final DeviceClass H323 = new DeviceClass(_H323);
    public static final DeviceClass Cti = new DeviceClass(_Cti);
    public static final DeviceClass VoiceMail = new DeviceClass(_VoiceMail);
    public static final DeviceClass MediaResources = new DeviceClass(_MediaResources);
    public static final DeviceClass Unknown = new DeviceClass(_Unknown);
    public java.lang.String getValue() { return _value_;}
    public static DeviceClass fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        DeviceClass enumeration = (DeviceClass)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static DeviceClass fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeviceClass.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DeviceClass"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
