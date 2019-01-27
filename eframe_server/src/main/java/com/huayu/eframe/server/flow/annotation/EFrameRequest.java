package com.huayu.eframe.server.flow.annotation;

import com.huayu.eframe.server.tool.util.CollectionUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * Created by Administrator on 2018/8/12.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface EFrameRequest
{
    boolean required() default false;

    String defaultStrValue() default "";

    long defaultLongValue() default Long.MIN_VALUE;

    int defaultIntegerValue() default Integer.MIN_VALUE;

    boolean baseMeta() default false;

    String[] enumValue() default {};

    String bean() default "";

    String[] beans() default {};

    int length() default -1;

}
