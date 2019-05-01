package com.huayu.eframe.timetask.execute;

import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Leo on 2019/4/15.
 * 保存Task
 */
@Service
public class TaskMap
{
    private Map<String, Task> storeTask = new HashMap<>();

    public TaskMap(Set<Task> tasks)
    {
        if (CollectionUtils.isNotEmpty(tasks))
        {
            CollectionUtils.iterator(tasks, (c, v, i) ->
            {
                storeTask.put(v.getName(), v);
            });
        }
    }

    public Map<String, Task> getTimeTaskService()
    {
        return storeTask;
    }

    public Task getTimeTaskServiceByName(String typeName)
    {
        return storeTask.get(typeName);
    }
}
