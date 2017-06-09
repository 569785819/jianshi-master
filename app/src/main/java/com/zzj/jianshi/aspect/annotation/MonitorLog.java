package com.zzj.jianshi.aspect.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)//这个注解周期声明在 class 文件上
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})//可以注解构造函数和方法
public @interface MonitorLog {
}
