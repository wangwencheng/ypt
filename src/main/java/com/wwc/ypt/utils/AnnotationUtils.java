package com.wwc.ypt.utils;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;

public class AnnotationUtils {
    public static boolean isPresentTypeOrMethod(HandlerMethod handlerMethod, Class<? extends Annotation> annotationType) {
        return AnnotatedElementUtils.hasAnnotation(handlerMethod.getBeanType(), annotationType)
                || AnnotatedElementUtils.hasAnnotation(handlerMethod.getMethod(), annotationType);
    }
}
