namespace java thrift.api

struct SimpleParameter {
    1:string p1
    2:string p2
    3:string p3
}

service SimpleService {
    string m1(1:SimpleParameter param)
}