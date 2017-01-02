/**
 * CmDevRegStat.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class CmDevRegStat implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected CmDevRegStat(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Any = "Any";
    public static final java.lang.String _Registered = "Registered";
    public static final java.lang.String _UnRegistered = "UnRegistered";
    public static final java.lang.String _Rejected = "Rejected";
    public static final java.lang.String _PartiallyRegistered = "PartiallyRegistered";
    public static final java.lang.String _Unknown = "Unknown";
    public static final CmDevRegStat Any = new CmDevRegStat(_Any);
    public static final CmDevRegStat Registered = new CmDevRegStat(_Registered);
    public static final CmDevRegStat UnRegistered = new CmDevRegStat(_UnRegistered);
    public static final CmDevRegStat Rejected = new CmDevRegStat(_Rejected);
    public static final CmDevRegStat PartiallyRegistered = new CmDevRegStat(_PartiallyRegistered);
    public static final CmDevRegStat Unknown = new CmDevRegStat(_Unknown);
    public java.lang.String getValue() { return _value_;}
    public static CmDevRegStat fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        CmDevRegStat enumeration = (CmDevRegStat)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static CmDevRegStat fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(CmDevRegStat.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevRegStat"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
