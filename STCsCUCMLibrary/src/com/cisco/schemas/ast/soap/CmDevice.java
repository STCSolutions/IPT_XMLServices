/**
 * CmDevice.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class CmDevice  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String ipAddress;

    private java.lang.String dirNumber;

    private com.cisco.schemas.ast.soap.DeviceClass _class;

    private org.apache.axis.types.UnsignedInt model;

    private org.apache.axis.types.UnsignedInt product;

    private org.apache.axis.types.UnsignedInt boxProduct;

    private com.cisco.schemas.ast.soap.CmDevHttpd httpd;

    private org.apache.axis.types.UnsignedInt registrationAttempts;

    private boolean isCtiControllable;

    private java.lang.String loginUserId;

    private com.cisco.schemas.ast.soap.CmDevRegStat status;

    private org.apache.axis.types.UnsignedInt statusReason;

    private org.apache.axis.types.UnsignedInt perfMonObject;

    private org.apache.axis.types.UnsignedInt DChannel;

    private java.lang.String description;

    private com.cisco.schemas.ast.soap.H323Trunk h323Trunk;

    private org.apache.axis.types.UnsignedInt timeStamp;

    public CmDevice() {
    }

    public CmDevice(
           java.lang.String name,
           java.lang.String ipAddress,
           java.lang.String dirNumber,
           com.cisco.schemas.ast.soap.DeviceClass _class,
           org.apache.axis.types.UnsignedInt model,
           org.apache.axis.types.UnsignedInt product,
           org.apache.axis.types.UnsignedInt boxProduct,
           com.cisco.schemas.ast.soap.CmDevHttpd httpd,
           org.apache.axis.types.UnsignedInt registrationAttempts,
           boolean isCtiControllable,
           java.lang.String loginUserId,
           com.cisco.schemas.ast.soap.CmDevRegStat status,
           org.apache.axis.types.UnsignedInt statusReason,
           org.apache.axis.types.UnsignedInt perfMonObject,
           org.apache.axis.types.UnsignedInt DChannel,
           java.lang.String description,
           com.cisco.schemas.ast.soap.H323Trunk h323Trunk,
           org.apache.axis.types.UnsignedInt timeStamp) {
           this.name = name;
           this.ipAddress = ipAddress;
           this.dirNumber = dirNumber;
           this._class = _class;
           this.model = model;
           this.product = product;
           this.boxProduct = boxProduct;
           this.httpd = httpd;
           this.registrationAttempts = registrationAttempts;
           this.isCtiControllable = isCtiControllable;
           this.loginUserId = loginUserId;
           this.status = status;
           this.statusReason = statusReason;
           this.perfMonObject = perfMonObject;
           this.DChannel = DChannel;
           this.description = description;
           this.h323Trunk = h323Trunk;
           this.timeStamp = timeStamp;
    }


    /**
     * Gets the name value for this CmDevice.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this CmDevice.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the ipAddress value for this CmDevice.
     * 
     * @return ipAddress
     */
    public java.lang.String getIpAddress() {
        return ipAddress;
    }


    /**
     * Sets the ipAddress value for this CmDevice.
     * 
     * @param ipAddress
     */
    public void setIpAddress(java.lang.String ipAddress) {
        this.ipAddress = ipAddress;
    }


    /**
     * Gets the dirNumber value for this CmDevice.
     * 
     * @return dirNumber
     */
    public java.lang.String getDirNumber() {
        return dirNumber;
    }


    /**
     * Sets the dirNumber value for this CmDevice.
     * 
     * @param dirNumber
     */
    public void setDirNumber(java.lang.String dirNumber) {
        this.dirNumber = dirNumber;
    }


    /**
     * Gets the _class value for this CmDevice.
     * 
     * @return _class
     */
    public com.cisco.schemas.ast.soap.DeviceClass get_class() {
        return _class;
    }


    /**
     * Sets the _class value for this CmDevice.
     * 
     * @param _class
     */
    public void set_class(com.cisco.schemas.ast.soap.DeviceClass _class) {
        this._class = _class;
    }


    /**
     * Gets the model value for this CmDevice.
     * 
     * @return model
     */
    public org.apache.axis.types.UnsignedInt getModel() {
        return model;
    }


    /**
     * Sets the model value for this CmDevice.
     * 
     * @param model
     */
    public void setModel(org.apache.axis.types.UnsignedInt model) {
        this.model = model;
    }


    /**
     * Gets the product value for this CmDevice.
     * 
     * @return product
     */
    public org.apache.axis.types.UnsignedInt getProduct() {
        return product;
    }


    /**
     * Sets the product value for this CmDevice.
     * 
     * @param product
     */
    public void setProduct(org.apache.axis.types.UnsignedInt product) {
        this.product = product;
    }


    /**
     * Gets the boxProduct value for this CmDevice.
     * 
     * @return boxProduct
     */
    public org.apache.axis.types.UnsignedInt getBoxProduct() {
        return boxProduct;
    }


    /**
     * Sets the boxProduct value for this CmDevice.
     * 
     * @param boxProduct
     */
    public void setBoxProduct(org.apache.axis.types.UnsignedInt boxProduct) {
        this.boxProduct = boxProduct;
    }


    /**
     * Gets the httpd value for this CmDevice.
     * 
     * @return httpd
     */
    public com.cisco.schemas.ast.soap.CmDevHttpd getHttpd() {
        return httpd;
    }


    /**
     * Sets the httpd value for this CmDevice.
     * 
     * @param httpd
     */
    public void setHttpd(com.cisco.schemas.ast.soap.CmDevHttpd httpd) {
        this.httpd = httpd;
    }


    /**
     * Gets the registrationAttempts value for this CmDevice.
     * 
     * @return registrationAttempts
     */
    public org.apache.axis.types.UnsignedInt getRegistrationAttempts() {
        return registrationAttempts;
    }


    /**
     * Sets the registrationAttempts value for this CmDevice.
     * 
     * @param registrationAttempts
     */
    public void setRegistrationAttempts(org.apache.axis.types.UnsignedInt registrationAttempts) {
        this.registrationAttempts = registrationAttempts;
    }


    /**
     * Gets the isCtiControllable value for this CmDevice.
     * 
     * @return isCtiControllable
     */
    public boolean isIsCtiControllable() {
        return isCtiControllable;
    }


    /**
     * Sets the isCtiControllable value for this CmDevice.
     * 
     * @param isCtiControllable
     */
    public void setIsCtiControllable(boolean isCtiControllable) {
        this.isCtiControllable = isCtiControllable;
    }


    /**
     * Gets the loginUserId value for this CmDevice.
     * 
     * @return loginUserId
     */
    public java.lang.String getLoginUserId() {
        return loginUserId;
    }


    /**
     * Sets the loginUserId value for this CmDevice.
     * 
     * @param loginUserId
     */
    public void setLoginUserId(java.lang.String loginUserId) {
        this.loginUserId = loginUserId;
    }


    /**
     * Gets the status value for this CmDevice.
     * 
     * @return status
     */
    public com.cisco.schemas.ast.soap.CmDevRegStat getStatus() {
        return status;
    }


    /**
     * Sets the status value for this CmDevice.
     * 
     * @param status
     */
    public void setStatus(com.cisco.schemas.ast.soap.CmDevRegStat status) {
        this.status = status;
    }


    /**
     * Gets the statusReason value for this CmDevice.
     * 
     * @return statusReason
     */
    public org.apache.axis.types.UnsignedInt getStatusReason() {
        return statusReason;
    }


    /**
     * Sets the statusReason value for this CmDevice.
     * 
     * @param statusReason
     */
    public void setStatusReason(org.apache.axis.types.UnsignedInt statusReason) {
        this.statusReason = statusReason;
    }


    /**
     * Gets the perfMonObject value for this CmDevice.
     * 
     * @return perfMonObject
     */
    public org.apache.axis.types.UnsignedInt getPerfMonObject() {
        return perfMonObject;
    }


    /**
     * Sets the perfMonObject value for this CmDevice.
     * 
     * @param perfMonObject
     */
    public void setPerfMonObject(org.apache.axis.types.UnsignedInt perfMonObject) {
        this.perfMonObject = perfMonObject;
    }


    /**
     * Gets the DChannel value for this CmDevice.
     * 
     * @return DChannel
     */
    public org.apache.axis.types.UnsignedInt getDChannel() {
        return DChannel;
    }


    /**
     * Sets the DChannel value for this CmDevice.
     * 
     * @param DChannel
     */
    public void setDChannel(org.apache.axis.types.UnsignedInt DChannel) {
        this.DChannel = DChannel;
    }


    /**
     * Gets the description value for this CmDevice.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this CmDevice.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the h323Trunk value for this CmDevice.
     * 
     * @return h323Trunk
     */
    public com.cisco.schemas.ast.soap.H323Trunk getH323Trunk() {
        return h323Trunk;
    }


    /**
     * Sets the h323Trunk value for this CmDevice.
     * 
     * @param h323Trunk
     */
    public void setH323Trunk(com.cisco.schemas.ast.soap.H323Trunk h323Trunk) {
        this.h323Trunk = h323Trunk;
    }


    /**
     * Gets the timeStamp value for this CmDevice.
     * 
     * @return timeStamp
     */
    public org.apache.axis.types.UnsignedInt getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this CmDevice.
     * 
     * @param timeStamp
     */
    public void setTimeStamp(org.apache.axis.types.UnsignedInt timeStamp) {
        this.timeStamp = timeStamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CmDevice)) return false;
        CmDevice other = (CmDevice) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.ipAddress==null && other.getIpAddress()==null) || 
             (this.ipAddress!=null &&
              this.ipAddress.equals(other.getIpAddress()))) &&
            ((this.dirNumber==null && other.getDirNumber()==null) || 
             (this.dirNumber!=null &&
              this.dirNumber.equals(other.getDirNumber()))) &&
            ((this._class==null && other.get_class()==null) || 
             (this._class!=null &&
              this._class.equals(other.get_class()))) &&
            ((this.model==null && other.getModel()==null) || 
             (this.model!=null &&
              this.model.equals(other.getModel()))) &&
            ((this.product==null && other.getProduct()==null) || 
             (this.product!=null &&
              this.product.equals(other.getProduct()))) &&
            ((this.boxProduct==null && other.getBoxProduct()==null) || 
             (this.boxProduct!=null &&
              this.boxProduct.equals(other.getBoxProduct()))) &&
            ((this.httpd==null && other.getHttpd()==null) || 
             (this.httpd!=null &&
              this.httpd.equals(other.getHttpd()))) &&
            ((this.registrationAttempts==null && other.getRegistrationAttempts()==null) || 
             (this.registrationAttempts!=null &&
              this.registrationAttempts.equals(other.getRegistrationAttempts()))) &&
            this.isCtiControllable == other.isIsCtiControllable() &&
            ((this.loginUserId==null && other.getLoginUserId()==null) || 
             (this.loginUserId!=null &&
              this.loginUserId.equals(other.getLoginUserId()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.statusReason==null && other.getStatusReason()==null) || 
             (this.statusReason!=null &&
              this.statusReason.equals(other.getStatusReason()))) &&
            ((this.perfMonObject==null && other.getPerfMonObject()==null) || 
             (this.perfMonObject!=null &&
              this.perfMonObject.equals(other.getPerfMonObject()))) &&
            ((this.DChannel==null && other.getDChannel()==null) || 
             (this.DChannel!=null &&
              this.DChannel.equals(other.getDChannel()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.h323Trunk==null && other.getH323Trunk()==null) || 
             (this.h323Trunk!=null &&
              this.h323Trunk.equals(other.getH323Trunk()))) &&
            ((this.timeStamp==null && other.getTimeStamp()==null) || 
             (this.timeStamp!=null &&
              this.timeStamp.equals(other.getTimeStamp())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getIpAddress() != null) {
            _hashCode += getIpAddress().hashCode();
        }
        if (getDirNumber() != null) {
            _hashCode += getDirNumber().hashCode();
        }
        if (get_class() != null) {
            _hashCode += get_class().hashCode();
        }
        if (getModel() != null) {
            _hashCode += getModel().hashCode();
        }
        if (getProduct() != null) {
            _hashCode += getProduct().hashCode();
        }
        if (getBoxProduct() != null) {
            _hashCode += getBoxProduct().hashCode();
        }
        if (getHttpd() != null) {
            _hashCode += getHttpd().hashCode();
        }
        if (getRegistrationAttempts() != null) {
            _hashCode += getRegistrationAttempts().hashCode();
        }
        _hashCode += (isIsCtiControllable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getLoginUserId() != null) {
            _hashCode += getLoginUserId().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStatusReason() != null) {
            _hashCode += getStatusReason().hashCode();
        }
        if (getPerfMonObject() != null) {
            _hashCode += getPerfMonObject().hashCode();
        }
        if (getDChannel() != null) {
            _hashCode += getDChannel().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getH323Trunk() != null) {
            _hashCode += getH323Trunk().hashCode();
        }
        if (getTimeStamp() != null) {
            _hashCode += getTimeStamp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CmDevice.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevice"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "IpAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dirNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DirNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_class");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Class"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DeviceClass"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("model");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Model"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("product");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Product"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("boxProduct");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "BoxProduct"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("httpd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Httpd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevHttpd"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registrationAttempts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "RegistrationAttempts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isCtiControllable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "IsCtiControllable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginUserId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "LoginUserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevRegStat"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "StatusReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("perfMonObject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "PerfMonObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DChannel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DChannel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("h323Trunk");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "H323Trunk"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "H323Trunk"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
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
