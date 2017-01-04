/**
 * RISServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RISServiceLocator extends org.apache.axis.client.Service implements com.cisco.schemas.ast.soap.RISService {
  static String callManagerIP="192.168.1.10";
    public RISServiceLocator() {
        Properties ProbFile=new Properties();
        try {
            ProbFile.load(new FileInputStream(new File("C:\\STCs\\Config.properties")));
        } catch (IOException ex) {

        }
        callManagerIP=ProbFile.getProperty("callManagerIP");

    }


    public RISServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RISServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RisPort
    private java.lang.String RisPort_address = "https://"+callManagerIP+"/realtimeservice/services/RisPort";

    public java.lang.String getRisPortAddress() {
        return RisPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RisPortWSDDServiceName = "RisPort";

    public java.lang.String getRisPortWSDDServiceName() {
        return RisPortWSDDServiceName;
    }

    public void setRisPortWSDDServiceName(java.lang.String name) {
        RisPortWSDDServiceName = name;
    }

    public com.cisco.schemas.ast.soap.RisPortType getRisPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RisPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRisPort(endpoint);
    }

    public com.cisco.schemas.ast.soap.RisPortType getRisPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.cisco.schemas.ast.soap.RisBindingStub _stub = new com.cisco.schemas.ast.soap.RisBindingStub(portAddress, this);
            _stub.setPortName(getRisPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRisPortEndpointAddress(java.lang.String address) {
        RisPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.cisco.schemas.ast.soap.RisPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.cisco.schemas.ast.soap.RisBindingStub _stub = new com.cisco.schemas.ast.soap.RisBindingStub(new java.net.URL(RisPort_address), this);
                _stub.setPortName(getRisPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RisPort".equals(inputPortName)) {
            return getRisPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "RISService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "RisPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RisPort".equals(portName)) {
            setRisPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
