package com.cn.redis2;

import java.io.Closeable;

/**
 * Created by Administrator on 2017/6/8.
 */
public abstract class SerializeTranscoder {

    public abstract byte[] serialize(Object value);

    public abstract Object deserialize(byte[] in);

    public void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                System.out.println("Unable to close ");
            }
        }
    }
}
