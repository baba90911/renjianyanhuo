package com.example.demo.dao;

import com.example.demo.pojo.Rule;
import java.util.List;
import java.util.Map;

public interface RuleMapper {
    int deleteByPrimaryKey(String category);

    int insert(Rule record);

    Rule selectByPrimaryKey(String category);

    List<Rule> selectAll();

    int updateByPrimaryKey(Rule record);

    List<String> selectCategories();

    void  updateRuleByCategory(Map<String,Object> map);

    int   updateStartTimeByCategory(Map<String,Object> map);

    int  updateEndTimeByCategory(Map<String,Object> map);

    String selectRuleByCategory(String  category);
}