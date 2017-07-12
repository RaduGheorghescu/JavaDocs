package ro.teamnet.hello;

import org.apache.log4j.Logger;

/**
 * Created by Radu.Gheorghescu on 7/10/2017.
 */
public class HelloWorld {
    /**
     * method for saying hello
     */
    static final Logger logger = Logger.getLogger(HelloWorld.class.getName());
    public void sayHello(){
        System.out.println("Hello World!");
    }

    /**
     * method for returning a key
     * @return - The HelloWorld key
     */
    public String returnHelloKey(){
        return "HelloKey";
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
        logger.debug("DEBUG -> Enters in sayHello() method from HelloWorld");
        logger.info("INFO -> Enters in returnHelloKey from HelloWorld");

    }
}

