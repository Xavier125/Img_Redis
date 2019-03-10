package vip.wefun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.wefun.dao.JedisClusterDao;
import vip.wefun.mapper.ImageMapper;
import vip.wefun.pojo.Image;
import vip.wefun.service.ImageService;
import vip.wefun.util.JsonUtils;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageMapper imageMapper;

    @Autowired
    JedisClusterDao jedisClusterDaoImpl;

    String key = "imageList";

    @Override
    public List<Image> show() {

        //如果redis中存在imageList
        if (jedisClusterDaoImpl.exist( key )) {
            String value = jedisClusterDaoImpl.get( key );
            if (value != null | value.equals( "" )) {
                System.out.println("redis中存在数据");
                return JsonUtils.jsonToList( value, Image.class );
            }
        }
        //如果redis中没有imageList,从MySQL中获取
        List<Image> imageList = imageMapper.selectByExample( null );
        //获取之后存入redis
        jedisClusterDaoImpl.set( key, JsonUtils.objectToJson( imageList ) );
        return imageList;
    }

}
