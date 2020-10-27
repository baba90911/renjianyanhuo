package com.example.demo.dao;

import com.example.demo.pojo.Administrator;
import java.util.List;

public interface AdministratorMapper {
    int deleteByPrimaryKey(String accounts);

    int insert(Administrator record);

    Administrator selectByPrimaryKey(String accounts);

    List<Administrator> selectAll();

    int updateByPrimaryKey(Administrator record);
}