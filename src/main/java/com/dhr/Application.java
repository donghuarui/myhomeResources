package com.dhr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Class description
 *
 *
 * @version        Enter version here..., 18/12/15
 * @author         Enter your name here...
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
public class Application {

    /**
     * Method description
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
