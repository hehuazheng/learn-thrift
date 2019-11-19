package com.hzz.thrift.api;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftStruct;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
