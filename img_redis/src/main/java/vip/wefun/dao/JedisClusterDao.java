package vip.wefun.dao;

public interface JedisClusterDao {
    Boolean exist(String key);

    String get(String key);

    String set(String key,String value);
}
