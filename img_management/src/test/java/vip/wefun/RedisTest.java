package vip.wefun;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.wefun.dao.JedisClusterDao;
import vip.wefun.pojo.Image;
import vip.wefun.util.JsonUtils;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-*.xml")
public class RedisTest {
    @Autowired
    JedisClusterDao jedisClusterDaoImpl;
    String key = "imageList";
    String eq;

    @Before
    public void before() {
        System.out.println( "=====================================================================================================================================================================================================================" );
    }

    @After
    public void after() {
        System.out.println( "=====================================================================================================================================================================================================================" );
    }

    @Test
    public void deleteTest() {
        System.out.println( jedisClusterDaoImpl.get( key ) );
        String value = jedisClusterDaoImpl.get( key );
        if (value != null && !value.equals( "" )) {
            System.out.println( value );
            System.out.println( "不为空" );
            List<Image> imageList = JsonUtils.jsonToList( value, Image.class );
            Iterator iterator = imageList.iterator();
            while (iterator.hasNext()) {
                if (((Image) iterator.next()).getId() == 18) {
                    iterator.remove();
                }
            }
            System.out.println( JsonUtils.objectToJson( imageList ) );
            eq = JsonUtils.objectToJson( imageList );
            System.out.println( jedisClusterDaoImpl.set( key, JsonUtils.objectToJson( imageList ) ) );
        }
    }

    @Test
    public void getTest() {
        System.out.println( jedisClusterDaoImpl.get( key ) );
    }

    @Test
    public void setTest() {
        System.out.println( jedisClusterDaoImpl.set( key, "[{\"id\":19,\"url\":\"https://www.cnblogs.com/Skins/coffee/images/bg_title.gif\"},{\"id\":20,\"url\":\"https://www.cnblogs.com/Skins/coffee/images/bg_title.gif\"}]" ) );
        System.out.println( jedisClusterDaoImpl.get( key ) );
    }
}
