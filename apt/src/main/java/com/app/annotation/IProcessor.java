package com.app.annotation;

import javax.annotation.processing.RoundEnvironment;

/**
 * Created by zhuzhejun on 2017/6/6.
 */

public interface IProcessor {
    void process(RoundEnvironment roundEnv, AnnotationProcessor annotationProcessor);
}