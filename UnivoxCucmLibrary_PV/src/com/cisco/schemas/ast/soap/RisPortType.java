/**
 * RisPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public interface RisPortType extends java.rmi.Remote {
    public com.cisco.schemas.ast.soap.SelectCmDeviceResult selectCmDevice(javax.xml.rpc.holders.StringHolder stateInfo, com.cisco.schemas.ast.soap.CmSelectionCriteria cmSelectionCriteria) throws java.rmi.RemoteException;
    public com.cisco.schemas.ast.soap.SelectCtiItemResult selectCtiItem(javax.xml.rpc.holders.StringHolder stateInfo, com.cisco.schemas.ast.soap.CtiSelectionCriteria ctiSelectionCriteria) throws java.rmi.RemoteException;
    public com.cisco.schemas.ast.soap.ColumnValueType[] executeCCMSQLStatement(java.lang.String executeSQLInputData, com.cisco.schemas.ast.soap.ColumnType[] getColumns) throws java.rmi.RemoteException;
    public com.cisco.schemas.ast.soap.ServerInformation[] getServerInfo(java.lang.String[] hosts) throws java.rmi.RemoteException;
    public com.cisco.schemas.ast.soap.SelectCmDeviceResultSIP selectCmDeviceSIP(javax.xml.rpc.holders.StringHolder stateInfo, com.cisco.schemas.ast.soap.CmSelectionCriteriaSIP cmSelectionCriteriaSIP) throws java.rmi.RemoteException;
}
