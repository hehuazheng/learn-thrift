namespace java thrift.api

struct SimpleParameter {
    1:string p1
}

service SimpleService {
    string m1(1:SimpleParameter param)
}