package org.example;

import org.example.config.BaseCfg;
import org.example.config.DataJpaCfg;
import org.example.service.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

@Import({BaseCfg.class, DataJpaCfg.class})
public class Main {
    public static void main(String[] args) {
        var cxt = new AnnotationConfigApplicationContext(Main.class);

        SingerService bean = cxt.getBean(SingerService.class);
        bean.findAll().forEach(System.out::println);
        bean.findAll().forEach(System.out::println);
        cxt.close();
    }
}
