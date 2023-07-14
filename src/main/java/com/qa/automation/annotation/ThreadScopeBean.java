package com.qa.automation.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

@Bean
@Scope("browserscope")
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadScopeBean {
}
