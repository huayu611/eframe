package com.huayu.eframe.server.flow.restful;

import java.util.Date;

public interface EffectiveExpireDateTime {

    default Date getExp() { return null;}

    default Date getEff() { return null;}
}
