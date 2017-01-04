/**
 * CmSingleLineStatus.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class CmSingleLineStatus implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected CmSingleLineStatus(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Any = "Any";
    public static final java.lang.String _Registered = "Registered";
    public static final java.lang.String _UnRegistered = "UnRegistered";
    public static final java.lang.String _Rejected = "Rejected";
    public static final java.lang.String _Unknown = "Unknown";
    public static final CmSingleLineStatus Any = new CmSingleLineStatus(_Any);
    public static final CmSingleLineStatus Registered = new CmSingleLineStatus(_Registered);
    public static final CmSingleLineStatus UnRegistered = new CmSingleLineStatus(_UnRegistered);
    public static final CmSingleLineStatus Rejected = new CmSingleLineStatus(_Rejected);
    public static final CmSingleLineStatus Unknown = new CmSingleLineStatus(_Unknown);
    public java.lang.String getValue() { return _value_;}
    public static CmSingleLineStatus fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        CmSingleLineStatus enumeration = (CmSingleLineStatus)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static CmSingleLineStatus fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(CmSingleLineStatus.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmSingleLineStatus"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
