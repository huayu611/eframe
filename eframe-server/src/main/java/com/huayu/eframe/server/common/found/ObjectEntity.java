package com.huayu.eframe.server.common.found;

/**
 * 组件之间可以通过实体跳板进行数据传递。在组件化中，尽量避免依赖，通过天窗操作
 * 若实例本身愿意被探知，可以实现此接口
 * Created by Leo on 2019/3/25.
 */
public interface ObjectEntity<T>
{
    String getName();

    default T getEntity(String code){return null;};

    default T getEntity(Long id){return null;};

    String getCode(Long id);

    Long getId(String code);
}
