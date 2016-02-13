package com.eavteam.touchball.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class MyProperties {

    public static final Properties app = new Properties();

    public static void init(){

        File propertiesDir = new File(System.getProperty("user.dir"), "properties");

        initFromFile(app,propertiesDir + "/app.properties");

    }

    private static void initFromFile(java.util.Properties props, String filepath) {
        try{
            FileInputStream stream = new FileInputStream(filepath);
            props.load(stream);
            stream.close();
        } catch (Exception e) {
            System.out.println("Error initializing props: " + e.getMessage());
        }
    }
}
