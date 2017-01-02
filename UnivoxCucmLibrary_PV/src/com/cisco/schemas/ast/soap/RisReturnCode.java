/**
 * RisReturnCode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class RisReturnCode implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected RisReturnCode(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Ok = "Ok";
    public static final java.lang.String _NotFound = "NotFound";
    public static final java.lang.String _InvalidRequest = "InvalidRequest";
    public static final java.lang.String _InternalError = "InternalError";
    public static final java.lang.String _NodeNotResponding = "NodeNotResponding";
    public static final java.lang.String _InvalidNodeName = "InvalidNodeName";
    public static final RisReturnCode Ok = new RisReturnCode(_Ok);
    public static final RisReturnCode NotFound = new RisReturnCode(_NotFound);
    public static final RisReturnCode InvalidRequest = new RisReturnCode(_InvalidRequest);
    public static final RisReturnCode InternalError = new RisReturnCode(_InternalError);
    public static final RisReturnCode NodeNotResponding = new RisReturnCode(_NodeNotResponding);
    public static final RisReturnCode InvalidNodeName = new RisReturnCode(_InvalidNodeName);
    public java.lang.String getValue() { return _value_;}
    public static RisReturnCode fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        RisReturnCode enumeration = (RisReturnCode)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static RisReturnCode fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(RisReturnCode.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "RisReturnCode"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
