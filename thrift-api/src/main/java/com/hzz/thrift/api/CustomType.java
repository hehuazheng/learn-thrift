package com.hzz.thrift.api;

import com.facebook.swift.codec.ThriftEnumValue;

/**
 * @author: hezz
 */
public enum CustomType {
    TYPE0("type0", 0), TYPE1("type1", 1), TYPE2("type2", 2), TYPE3("type3", 3);

    private String name;
    private int v;

    CustomType(String name, int v) {
        this.name = name;
        this.v = v;
    }

    @ThriftEnumValue
    public int getV() {
        return v;
    }
}
