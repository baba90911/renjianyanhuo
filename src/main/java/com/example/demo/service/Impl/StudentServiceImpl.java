package com.example.demo.service.Impl;


import com.example.demo.dao.ExamMapper;
import com.example.demo.dao.RuleMapper;
import com.example.demo.pojo.Rule;
import com.example.demo.pojo.result.exam_mess;
import com.example.demo.pojo.result.exam_message;
import com.example.demo.pojo.result.exam_name;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    ExamMapper examMapper;

    @Autowired
    RuleMapper ruleMapper;

    @Override
    public exam_mess findExamByStudentNumber(String number, String type) {
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("studentNumber", number);
        params.put("type", type);
        exam_message exam_message1=examMapper.selectByStudentAndType(params);
        String rule=exam_message1.getExamRule();
        System.out.println(rule);
        String[] a=rule.split("；");
        List<String> b=new LinkedList<>();
        for(int i=0;i<a.length-1;i++){
            b.add(a[i]+"；");
        }b.add(a[a.length-1]);
        exam_mess message=new exam_mess();
        message.setUrl(exam_message1.getUrl());
        message.setExamNumber(exam_message1.getExamNumber());
        message.setStudentNumber(exam_message1.getStudentNumber());
        message.setSchoolName(exam_message1.getSchoolName());
        message.setShoolCode(exam_message1.getShoolCode());
        message.setSex(exam_message1.getSex());
        message.setStudentName(exam_message1.getStudentName());
        message.setClassName(exam_message1.getClassName());
        message.setPictureName(exam_message1.getPictureName());
        message.setExamRule(b);
        message.setExamName(exam_message1.getExamName());
        message.setLanguageName(exam_message1.getLanguageName());
        message.setExamType(exam_message1.getExamType());
        message.setIsPrint(exam_message1.getIsPrint());
        message.setExamClassroom(exam_message1.getExamClassroom());
        message.setExamTime(exam_message1.getExamTime());
        message.setRoomNumber(exam_message1.getRoomNumber());
        message.setSeatNumber(exam_message1.getSeatNumber());
        message.setExamClassroom2(exam_message1.getExamClassroom2());
        message.setExamTime2(exam_message1.getExamTime2());
        message.setCollege(exam_message1.getCollege());
        message.setOther1(exam_message1.getOther1());
        return message;
    }

    @Override
    public List<exam_name> selectTypeByStudentNumber(String studentNumber) {
        return examMapper.selectTypeByStudentNumber(studentNumber);
    }

    @Override
    public String judgeTimeByCategory(String category) {
        String mes=null;
        Rule rule=ruleMapper.selectByPrimaryKey(category);
        Date startTime=rule.getStartTime();
        Date endTime=rule.getEndTime();
        Date now=new Date();
        if(now.getTime()<startTime.getTime())mes="打印时间未到";
        else if(now.getTime()>endTime.getTime())mes= "打印时间已过";
        else mes="可以打印";
        return mes;
    }

    @Override
    public String selectCategoryByTypeAndStudentNumber(String studentNumber, String type) {
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("studentNumber", studentNumber);
        params.put("type", type);
        System.out.println(studentNumber+"-studentSImpl:selectCategoryByTypeAndStudentNumber20191129-"+type);
        return examMapper.selectCategoryByTypeAndStudentNumber(params);
    }
}
