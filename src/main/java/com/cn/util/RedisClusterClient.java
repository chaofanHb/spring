package com.cn.util;

import redis.clients.jedis.JedisCluster;

/**
 * Created by Administrator on 2017/11/29.
 */
public class RedisClusterClient {
    private JedisCluster jedisCluster;
/* docker run -d -p 5000:5000 --restart=always  --name=registry\
    -v /opt/registry-var/config/:/etc/docker/registry/ \
            -v /opt/registry-var/auth/:/auth/ \
            -e "REGISTRY_AUTH=htpasswd" \
            -e "REGISTRY_AUTH_HTPASSWD_REALM=Registry Realm" \
            -e REGISTRY_AUTH_HTPASSWD_PATH=/auth/htpasswd \
            -v /opt/registry-var/:/var/lib/registry/ \
    registry:2.5*/


    /*kubectl create secret docker-registry registrysecret --docker-server=registry.cn-hangzhou.aliyuncs.com \
            --docker-username=mofiu@163.com --docker-password=**** --docker-email=mofiu@163.com*/
    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public void set(String key,String val){
        jedisCluster.set(key,val);
    }

    public String get(String key){
        return jedisCluster.get(key);
    }
}
