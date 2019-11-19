package com.hzz.thrift.api;

/**
 * @author: hezz
 */
public enum CustomType {
    TYPE1("type1", 1), TYPE2("type2", 2);

    private String name;
    private int v;

    CustomType(String name, int v) {
        this.name = name;
        this.v = v;
    }
}
