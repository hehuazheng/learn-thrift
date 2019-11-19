package com.hzz.thrift.api;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;

/**
 * @author: hezz
 */
@ThriftStruct
public class CustomData {
    private CustomType type;
    private int id;

    @ThriftField(1)
    public CustomType getType() {
        return type;
    }

    @ThriftField
    public void setType(CustomType type) {
        this.type = type;
    }

    @ThriftField(2)
    public int getId() {
        return id;
    }

    @ThriftField
    public void setId(int id) {
        this.id = id;
    }
}
