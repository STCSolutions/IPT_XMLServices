/**
 * RISService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public interface RISService extends javax.xml.rpc.Service {
    public java.lang.String getRisPortAddress();

    public com.cisco.schemas.ast.soap.RisPortType getRisPort() throws javax.xml.rpc.ServiceException;

    public com.cisco.schemas.ast.soap.RisPortType getRisPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
