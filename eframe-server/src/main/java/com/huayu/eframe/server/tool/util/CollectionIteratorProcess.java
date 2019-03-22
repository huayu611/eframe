package com.huayu.eframe.server.tool.util;

import java.util.Collection;

@FunctionalInterface
public interface CollectionIteratorProcess<Type>
{
  void process(Collection coll, Type value, int index);
}
