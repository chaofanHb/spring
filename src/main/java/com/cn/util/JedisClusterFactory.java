package com.cn.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/11/24.
 */
public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {
    private Resource addressConfig;
    private String addressKeyPrefix;
    private JedisCluster jedisCluster;
    private Integer timeout;
    private Integer maxRedirections;
    private GenericObjectPoolConfig genericObjectPoolConfig;
    private Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");

    public JedisClusterFactory() {
    }

    public JedisCluster getObject() throws Exception {
        return this.jedisCluster;
    }

    public Class<?> getObjectType() {
        return this.jedisCluster != null?this.jedisCluster.getClass():JedisCluster.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
        Set haps = this.parseHostAndPort();
        this.jedisCluster = new JedisCluster(haps, this.timeout.intValue(), this.maxRedirections.intValue(), this.genericObjectPoolConfig);
    }

    private Set<HostAndPort> parseHostAndPort() throws Exception {
        try {
            Properties ex = new Properties();
            ex.load(this.addressConfig.getInputStream());
            HashSet haps = new HashSet();
            Iterator var3 = ex.keySet().iterator();

            while(var3.hasNext()) {
                Object key = var3.next();
                if(((String)key).startsWith(this.addressKeyPrefix)) {
                    String val = (String)ex.get(key);
                    boolean isIpPort = this.p.matcher(val).matches();
                    if(!isIpPort) {
                        throw new IllegalArgumentException("ip 或 port 不合法");
                    }

                    String[] ipAndPort = val.split(":");
                    HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
                    haps.add(hap);
                }
            }

            return haps;
        } catch (IllegalArgumentException var9) {
            throw var9;
        } catch (Exception var10) {
            throw new Exception("解析 jedis 配置文件失败", var10);
        }
    }

    public void setAddressConfig(Resource addressConfig) {
        this.addressConfig = addressConfig;
    }

    public void setAddressKeyPrefix(String addressKeyPrefix) {
        this.addressKeyPrefix = addressKeyPrefix;
    }

    public Integer getTimeout() {
        return this.timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setMaxRedirections(Integer maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

    public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {
        this.genericObjectPoolConfig = genericObjectPoolConfig;
    }
}
