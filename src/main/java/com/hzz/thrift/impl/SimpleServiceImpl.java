package com.hzz.thrift.impl;

import org.apache.thrift.TException;
import thrift.api.SimpleParameter;
import thrift.api.SimpleService;

/**
 * Created by hejf on 2017/3/14.
 */
public class SimpleServiceImpl implements SimpleService.Iface {
    @Override
    public String m1(SimpleParameter param) throws TException {
        return "V2" + param.getP1() + "#" + param.getP2();
    }
}
