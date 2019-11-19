package com.hzz.thrift;

import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;
import com.hzz.thrift.api.CustomData;
import com.hzz.thrift.api.CustomType;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author: hezz
 */
@ThriftService
public interface ThriftAnnoService {
    @ThriftMethod
    String change(String v) throws TException;

    @ThriftMethod
    List<CustomData> getTypes();
}
