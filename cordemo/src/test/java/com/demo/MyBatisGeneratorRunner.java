package com.demo;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyBatisGeneratorRunner {
    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;

        // 加载generatorConfig.xml
        InputStream config = MyBatisGeneratorRunner.class
                .getClassLoader()
                .getResourceAsStream("generator/generatorConfig.xml");

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration configObj = cp.parseConfiguration(config);

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator generator = new MyBatisGenerator(configObj, callback, warnings);

        generator.generate(null);

        warnings.forEach(System.out::println);
        System.out.println("逆向工程执行完成");
    }
}