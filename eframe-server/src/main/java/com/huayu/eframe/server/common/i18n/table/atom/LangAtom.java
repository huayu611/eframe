package com.huayu.eframe.server.common.i18n.table.atom;

import com.huayu.eframe.server.common.i18n.table.bo.Lang;

import java.util.List;

/**
 * Created by Leo on 2018/9/8.
 */
public interface LangAtom
{
    List<Lang> getAll();

    List<Lang>  save(String langCode,List<Lang> values);

    Lang  save(Lang lang);

    List<Lang> update(String langCode,List<Lang> values);

    void delete(Lang lang);

    List<Lang> getLangsByCode(String langCode);
}
