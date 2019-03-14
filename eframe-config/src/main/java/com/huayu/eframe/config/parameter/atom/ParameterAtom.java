package com.huayu.eframe.config.parameter.atom;

import com.huayu.eframe.config.parameter.bo.Parameter;

import java.util.List;

/**
 * Created by Leo on 2018/9/9.
 */
public interface ParameterAtom
{
    List<Parameter> getAll();

    Parameter getByCode(String parameterCode);

    void delete(Parameter parameter);

    Parameter update(Parameter parameter) ;

    Parameter insert(Parameter parameter) ;
}
