package com.huayu.eframe.server.mvc.token;

import com.huayu.eframe.server.tool.basic.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Leo on 2018/9/15.
 */
@Service
public class DefaultTokenMirror extends AbstractTokenMirror
{


    @Override
    public List<AuthView> loadAuthView(String primary)
    {
        return null;
    }
}
