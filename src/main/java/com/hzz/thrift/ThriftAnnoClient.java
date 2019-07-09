package com.hzz.thrift;

import com.facebook.nifty.client.FramedClientConnector;
import com.facebook.nifty.client.NiftyClientChannel;
import com.facebook.nifty.duplex.TDuplexProtocolFactory;
import com.facebook.nifty.duplex.TProtocolPair;
import com.facebook.nifty.duplex.TTransportPair;
import com.facebook.swift.service.ThriftClientManager;
import com.google.common.net.HostAndPort;
import com.google.common.util.concurrent.ListenableFuture;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.slf4j.MDC;

import java.util.concurrent.ExecutionException;

/**
 * @author: hezz
 */
public class ThriftAnnoClient {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TException {
        ThriftClientManager clientManager = new ThriftClientManager();
        ListenableFuture<?> channel = clientManager.createChannel(new FramedClientConnector(HostAndPort.fromParts("localhost", 1234),
                new TDuplexProtocolFactory() {
                    @Override
                    public TProtocolPair getProtocolPair(TTransportPair tTransportPair) {
                        return TProtocolPair.fromSeparateProtocols(new TBinaryProtocol(tTransportPair.getInputTransport()),
                                new MyOutProtocol(tTransportPair.getOutputTransport()));
                    }
                }));
        NiftyClientChannel ncc = (NiftyClientChannel) channel.get();
        MDC.put("traceId", "1001");
        MDC.put("user", "testuser2");
        ThriftAnnoService simpleService = clientManager.createClient(ncc, ThriftAnnoService.class);
        System.out.println(simpleService.change("bbccbb"));
        clientManager.close();
    }
}
