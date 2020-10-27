package com.example.demo.dao;

import com.example.demo.pojo.Pictures;
import java.util.List;

public interface PicturesMapper {
    int deleteByPrimaryKey(String pictureName);

    int insert(Pictures record);

    Pictures selectByPrimaryKey(String pictureName);

    List<Pictures> selectAll();

    int updateByPrimaryKey(Pictures record);
}