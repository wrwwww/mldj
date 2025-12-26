package org.ml.mldj.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;

public class CodeGeneratorApplication {
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.117.129:3306/mailang";
//        String url = "jdbc:mysql://192.168.117.129:3306/hxds_cst";
        String username = "root";
        String password = "root";
        FastAutoGenerator.create(url, username, password)
                .dataSourceConfig(builder -> builder.driverClassName("com.mysql.cj.jdbc.Driver"))
                .globalConfig(builder -> builder
                        .author("mailang")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "code/")
                        .commentDate("yyyy-MM-dd").enableSpringdoc()
                )
                .packageConfig(builder -> builder
                                .parent("org.ml.mldj.model.mldj")
                                .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> builder.addTablePrefix("tb_")
                        .entityBuilder()
                        .enableLombok()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
