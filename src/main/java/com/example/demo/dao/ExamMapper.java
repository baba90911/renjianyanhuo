package com.example.demo.dao;

import com.example.demo.pojo.Exam;
import com.example.demo.pojo.result.exam_message;
import com.example.demo.pojo.result.exam_name;

import java.util.List;
import java.util.Map;

public interface ExamMapper {
    int deleteByPrimaryKey(String examNumber);

    int insert(Exam record);

    Exam selectByPrimaryKey(String examNumber);

    exam_message selectByStudentAndType(Map<String,Object> map);

    List<Exam> selectAll();

    int updateByPrimaryKey(Exam record);

    int selectAllStudentCount();

    int selectAllPrintCount();

    int selectPrintCountByCategory(String category);

    int selectPrintCountByType(String type);

    int selectStudentCountByCategory(String category);

    int selectStudentCountByType(String type);

    int selectByExamNumber(String examNumber);

    List<exam_name> selectTypeByStudentNumber(String studentNumber);

    String selectPasswordByStudent(String studentNumber);

    int deleteByCategory(String category);

    int updataPrintByExamNumber(String examNumber);

    List<String>  selectType();

    List<Exam>  selectByCategory(String category);

    List<Exam> selectByType(String type);

    String selectCategoryByTypeAndStudentNumber(Map<String,Object> map);

    int updatePictureByPictureName(String picture);

    List<String> selectPictureNameByCategory(String category);
}