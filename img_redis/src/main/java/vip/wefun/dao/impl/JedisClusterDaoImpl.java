package vip.wefun.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;
import vip.wefun.dao.JedisClusterDao;

@Repository
public class JedisClusterDaoImpl implements JedisClusterDao {

    @Autowired
    JedisCluster jedisCluster;

    @Override
    public Boolean exist(String key) {
        return jedisCluster.exists( key );
    }

    @Override
    public String get(String key) {
        return jedisCluster.get( key );
    }

    @Override
    public String set(String key, String value) {
        return jedisCluster.set( key, value );
    }
}
