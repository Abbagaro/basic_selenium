package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AmazonConfig {
    public static Properties properties;


    String path = "src/main/resources/amazon.property";

    public AmazonConfig(){


        properties = new Properties();

        try {

            FileInputStream fis = new FileInputStream(path);
            properties.load(fis);


        }catch (IOException e){

            e.printStackTrace();

        }

    }
    public static String getUrl(){

        String url = properties.getProperty("url");//getProperty gets the value of the key name you provide

        //to check if the key doesn't have a null value

        if (url != null){

            return url;

        }else {

            throw new RuntimeException("url key has no value");

        }
    }

}

