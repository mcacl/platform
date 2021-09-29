package manager;

import com.platform.cloud.ManagerApplication;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ManagerApplication.class)
//@RunWith(SpringRunner.class)
public class test{

    @Test
    void contextLoads(){
        String codeStr = String.valueOf(2);
        System.out.println(StringUtils.leftPad(codeStr,5,"0"));
    }

}
