package com.example.demo.service;


import com.example.demo.pojo.result.exam_mess;
import com.example.demo.pojo.result.exam_message;
import com.example.demo.pojo.result.exam_name;

import java.util.List;

public interface StudentService {

     exam_mess findExamByStudentNumber(String number, String type);

     List<exam_name> selectTypeByStudentNumber(String studentNumber);

     String judgeTimeByCategory(String category);

     String selectCategoryByTypeAndStudentNumber(String studentNumber ,String type);

}
