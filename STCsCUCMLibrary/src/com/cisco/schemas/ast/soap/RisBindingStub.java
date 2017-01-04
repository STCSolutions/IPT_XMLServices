/**
 * RisBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Jul 31, 2008 (10:54:19 GMT) WSDL2Java emitter.
 */

package com.cisco.schemas.ast.soap;

public class RisBindingStub extends org.apache.axis.client.Stub implements com.cisco.schemas.ast.soap.RisPortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[5];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SelectCmDevice");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "StateInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CmSelectionCriteria"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmSelectionCriteria"), com.cisco.schemas.ast.soap.CmSelectionCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectCmDeviceResult"));
        oper.setReturnClass(com.cisco.schemas.ast.soap.SelectCmDeviceResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "SelectCmDeviceResult"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SelectCtiItem");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "StateInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CtiSelectionCriteria"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiSelectionCriteria"), com.cisco.schemas.ast.soap.CtiSelectionCriteria.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectCtiItemResult"));
        oper.setReturnClass(com.cisco.schemas.ast.soap.SelectCtiItemResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "SelectCtiItemResult"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("ExecuteCCMSQLStatement");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ExecuteSQLInputData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "GetColumns"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ArrayOfGetColumns"), com.cisco.schemas.ast.soap.ColumnType[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ArrayOfColumnValues"));
        oper.setReturnClass(com.cisco.schemas.ast.soap.ColumnValueType[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ExcuteSQLOutputData"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetServerInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Hosts"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ArrayOfHosts"), java.lang.String[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ArrayOfServerInfo"));
        oper.setReturnClass(com.cisco.schemas.ast.soap.ServerInformation[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ServerInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SelectCmDeviceSIP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "StateInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CmSelectionCriteriaSIP"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmSelectionCriteriaSIP"), com.cisco.schemas.ast.soap.CmSelectionCriteriaSIP.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectCmDeviceResultSIP"));
        oper.setReturnClass(com.cisco.schemas.ast.soap.SelectCmDeviceResultSIP.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "SelectCmDeviceResultSIP"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[4] = oper;

    }

    public RisBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public RisBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public RisBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ArrayOfColumnValues");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.ColumnValueType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ColumnValueType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ArrayOfGetColumns");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.ColumnType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ColumnType");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ArrayOfHosts");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ArrayOfServerInfo");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.ServerInformation[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ServerInformation");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevHttpd");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmDevHttpd.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevice");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmDevice.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevices");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmDevice[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevice");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDeviceSIP");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmDeviceSIP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevicesSIP");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmDeviceSIP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDeviceSIP");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevLinesStatus");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmDevSingleLineStatus[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevSingleLineStatus");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevRegStat");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmDevRegStat.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmDevSingleLineStatus");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmDevSingleLineStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmNode");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmNode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmNodes");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmNode[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmNode");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmNodeSIP");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmNodeSIP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmNodesSIP");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmNodeSIP[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmNodeSIP");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmSelectionCriteria");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmSelectionCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmSelectionCriteriaSIP");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmSelectionCriteriaSIP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CmSingleLineStatus");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CmSingleLineStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ColumnNType");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ColumnType");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.ColumnType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ColumnValueType");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.ColumnValueType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ColumnVType");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiDevice");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CtiDevice.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiItem");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CtiItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiItems");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CtiItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiItem");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiLine");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CtiLine.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiMgrClass");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CtiMgrClass.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiNode");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CtiNode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiNodes");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CtiNode[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiNode");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiSelectAppBy");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CtiSelectAppBy.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiSelectionCriteria");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CtiSelectionCriteria.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "CtiStatus");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.CtiStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "DeviceClass");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.DeviceClass.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "H323Trunk");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.H323Trunk.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ProtocolType");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.ProtocolType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "RisReturnCode");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.RisReturnCode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectAppItem");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.SelectAppItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectAppItems");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.SelectAppItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectAppItem");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectCmDeviceResult");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.SelectCmDeviceResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectCmDeviceResultSIP");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.SelectCmDeviceResultSIP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectCtiItemResult");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.SelectCtiItemResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectDevName");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.SelectDevName.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectDevNames");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.SelectDevName[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectDevName");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectDirNumber");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.SelectDirNumber.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectDirNumbers");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.SelectDirNumber[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectDirNumber");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectItem");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.SelectItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectItems");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.SelectItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectItem");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ServerInformation");
            cachedSerQNames.add(qName);
            cls = com.cisco.schemas.ast.soap.ServerInformation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.cisco.schemas.ast.soap.SelectCmDeviceResult selectCmDevice(javax.xml.rpc.holders.StringHolder stateInfo, com.cisco.schemas.ast.soap.CmSelectionCriteria cmSelectionCriteria) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.cisco.com/ast/soap/action/#RisPort#SelectCmDevice");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectCmDevice"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {stateInfo.value, cmSelectionCriteria});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                stateInfo.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "StateInfo"));
            } catch (java.lang.Exception _exception) {
                stateInfo.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "StateInfo")), java.lang.String.class);
            }
            try {
                return (com.cisco.schemas.ast.soap.SelectCmDeviceResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.cisco.schemas.ast.soap.SelectCmDeviceResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.cisco.schemas.ast.soap.SelectCmDeviceResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.cisco.schemas.ast.soap.SelectCtiItemResult selectCtiItem(javax.xml.rpc.holders.StringHolder stateInfo, com.cisco.schemas.ast.soap.CtiSelectionCriteria ctiSelectionCriteria) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.cisco.com/ast/soap/action/#RisPort#SelectCtiItem");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectCtiItem"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {stateInfo.value, ctiSelectionCriteria});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                stateInfo.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "StateInfo"));
            } catch (java.lang.Exception _exception) {
                stateInfo.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "StateInfo")), java.lang.String.class);
            }
            try {
                return (com.cisco.schemas.ast.soap.SelectCtiItemResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.cisco.schemas.ast.soap.SelectCtiItemResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.cisco.schemas.ast.soap.SelectCtiItemResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.cisco.schemas.ast.soap.ColumnValueType[] executeCCMSQLStatement(java.lang.String executeSQLInputData, com.cisco.schemas.ast.soap.ColumnType[] getColumns) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.cisco.com/ast/soap/action/#PerfmonPort#ExecuteCCMSQLStatement");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "ExecuteCCMSQLStatement"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {executeSQLInputData, getColumns});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.cisco.schemas.ast.soap.ColumnValueType[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.cisco.schemas.ast.soap.ColumnValueType[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.cisco.schemas.ast.soap.ColumnValueType[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.cisco.schemas.ast.soap.ServerInformation[] getServerInfo(java.lang.String[] hosts) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.cisco.com/ast/soap/action/#PerfmonPort#GetServerInfo");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "GetServerInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {hosts});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.cisco.schemas.ast.soap.ServerInformation[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.cisco.schemas.ast.soap.ServerInformation[]) org.apache.axis.utils.JavaUtils.convert(_resp, com.cisco.schemas.ast.soap.ServerInformation[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.cisco.schemas.ast.soap.SelectCmDeviceResultSIP selectCmDeviceSIP(javax.xml.rpc.holders.StringHolder stateInfo, com.cisco.schemas.ast.soap.CmSelectionCriteriaSIP cmSelectionCriteriaSIP) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://schemas.cisco.com/ast/soap/action/#RisPort#SelectCmDeviceSIP");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://schemas.cisco.com/ast/soap/", "SelectCmDeviceSIP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {stateInfo.value, cmSelectionCriteriaSIP});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                stateInfo.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "StateInfo"));
            } catch (java.lang.Exception _exception) {
                stateInfo.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "StateInfo")), java.lang.String.class);
            }
            try {
                return (com.cisco.schemas.ast.soap.SelectCmDeviceResultSIP) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.cisco.schemas.ast.soap.SelectCmDeviceResultSIP) org.apache.axis.utils.JavaUtils.convert(_resp, com.cisco.schemas.ast.soap.SelectCmDeviceResultSIP.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
