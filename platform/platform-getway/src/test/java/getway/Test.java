package getway;

import com.platform.cloud.GetwayApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.AntPathMatcher;

@SpringBootTest(classes = GetwayApplication.class)
public class Test{

    @org.junit.jupiter.api.Test
    void contextLoads(){
        AntPathMatcher antPathMatcher = new AntPathMatcher();///platform-manager-biz/v2/api-docs
        System.out.println(antPathMatcher.match("/**/v2/api-docs/**","/platform-manager-biz/v2/api-docs/v2/api-docs"));
    }

}
