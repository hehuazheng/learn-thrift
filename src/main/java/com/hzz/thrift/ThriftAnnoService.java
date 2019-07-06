package com.hzz.thrift;

import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;
import org.apache.thrift.TException;

/**
 * @author: hezz
 */
@ThriftService
public interface ThriftAnnoService {
    @ThriftMethod
    String change(String v) throws TException;
}
