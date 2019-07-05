package com.hzz.thrift.swift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("SimpleParameter")
public final class SimpleParameter
{
    public SimpleParameter() {
    }

    private String p1;

    @ThriftField(value=1, name="p1", requiredness=Requiredness.NONE)
    public String getP1() { return p1; }

    @ThriftField
    public void setP1(final String p1) { this.p1 = p1; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("p1", p1)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SimpleParameter other = (SimpleParameter)o;

        return
            Objects.equals(p1, other.p1);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            p1
        });
    }
}
