package com.hzz.thrift.swift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("SimpleService")
public interface SimpleService
{
    @ThriftService("SimpleService")
    public interface Async
    {
        @ThriftMethod(value = "m1")
        ListenableFuture<String> m1(
            @ThriftField(value=1, name="param", requiredness=Requiredness.NONE) final SimpleParameter param
        );
    }
    @ThriftMethod(value = "m1")
    String m1(
        @ThriftField(value=1, name="param", requiredness=Requiredness.NONE) final SimpleParameter param
    ) throws org.apache.thrift.TException;
}