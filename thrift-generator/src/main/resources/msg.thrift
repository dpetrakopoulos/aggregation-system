namespace java com.dpetrakopoulos.thrift

struct ThriftMessage {
    1: i16 v,
    2: i64 time,
    3: string m
}

exception InvalidOperationException {
    1: i32 code,
    2: string description
}

service MessageService {

    void save(1:ThriftMessage message) throws (1:InvalidOperationException e)
}
