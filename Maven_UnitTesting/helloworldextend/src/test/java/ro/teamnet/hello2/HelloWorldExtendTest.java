package ro.teamnet.hello2;

import org.junit.Test;

/**
 * Created by Radu.Gheorghescu on 7/10/2017.
 */

public class HelloWorldExtendTest {
    @Test
    public void test() throws Exception {
        new HelloWorldExtend().extendSayHello();
    }

}
