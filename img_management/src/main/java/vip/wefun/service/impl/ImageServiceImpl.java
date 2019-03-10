package vip.wefun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.wefun.dao.JedisClusterDao;
import vip.wefun.mapper.ImageMapper;
import vip.wefun.pojo.Image;
import vip.wefun.service.ImageService;
import vip.wefun.util.JsonUtils;

import java.util.Iterator;
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
        return imageMapper.selectByExample( null );
    }

    @Override
    public int deleteImageById(int id) {
        int index = imageMapper.deleteByPrimaryKey( id );
        if (index > 0) {
            System.out.println( "删除成功:" + id );
            if (jedisClusterDaoImpl.exist( key )) {
                System.out.println( "存在" + key );
                String value = jedisClusterDaoImpl.get( key );
                if (value != null | value.equals( "" )) {
                    System.out.println( value );
                    System.out.println( key + "不为空" );
                    List<Image> imageList = JsonUtils.jsonToList( value, Image.class );
                    Iterator iterator = imageList.iterator();
                    while (iterator.hasNext()) {
                        if (((Image) iterator.next()).getId() == id) {
                            iterator.remove();
                        }
                    }
                    System.out.println( JsonUtils.objectToJson( imageList ) );
                    jedisClusterDaoImpl.set( key, JsonUtils.objectToJson( imageList ) );
                    System.out.println( jedisClusterDaoImpl.get( key ) );
                }
            }
        }
        return index;
    }
}
