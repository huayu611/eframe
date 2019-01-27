package com.huayu.eframe.server.security.menu.repository;

import com.huayu.eframe.server.security.menu.bo.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/8/12.
 */
public interface MenuRepository extends JpaRepository<Menu,Long>
{
}
