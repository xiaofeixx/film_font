import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiaofei
 * @Classname test
 * @Description 个人项目，仅供学习
 * @Date 2020/5/21 15:36
 * @Created by xiaofei
 */
@SpringBootTest
public class test {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public static void main(String[] args) {
     Object o  = new SimpleHash("md5","123456",null,2);
        System.out.println(o);
    }
}
