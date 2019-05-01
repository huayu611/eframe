package com.huayu.eframe.global.multipart.upload.ruler;

import com.huayu.eframe.server.tool.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leo on 2019/3/30.
 */
@Service
public class UploadRulerExecute
{
    private Map<String, UploadRuler> rulerCache;

    public UploadRulerExecute(List<UploadRuler> uploadRulerList)
    {
        rulerCache = new HashMap<>();
        CollectionUtils.iterator(uploadRulerList, (c, v, i) ->
        {
            rulerCache.put(v.getUploadType(), v);
        });
    }

    public UploadRuler getUploadRuler(String ruleType)
    {
        return rulerCache.get(ruleType);
    }
}
