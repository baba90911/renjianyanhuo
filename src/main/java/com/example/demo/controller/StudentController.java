package com.example.demo.controller;


import com.example.demo.pojo.JsonData;
import com.example.demo.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/v1/api/students"})
@Api(description = "学生操作")
@CrossOrigin
public class StudentController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    StudentService studentService;


    @ApiOperation("查询准考证数据")
    @GetMapping("/select_exam_message")
    public JsonData findExamByNumberAndType(@RequestParam String  number,@RequestParam String type) throws Exception{
        try{
            return JsonData.success(studentService.findExamByStudentNumber(number,type));
        }catch (Exception e){
            this.logger.error(e.getMessage());
            return JsonData.fail(e.getMessage());
        }
    }


    @ApiOperation("查询可打印考试")
    @GetMapping("/select_exam")
    public JsonData selectTypeByStudentNumber(@RequestParam String  studentumber) throws Exception{
        try{
            return JsonData.success(studentService.selectTypeByStudentNumber(studentumber));
        }catch (Exception e){
            this.logger.error(e.getMessage());
            return JsonData.fail(e.getMessage());
        }
    }

    @ApiOperation("判断打印时间")
    @GetMapping("/judge_time")
    public JsonData judgeTimeByCategory(String category) throws Exception{
        try{
            return JsonData.success(studentService.judgeTimeByCategory(category));
        }catch (Exception e){
            this.logger.error(e.getMessage());
            return JsonData.fail(e.getMessage());
        }
    }

    @ApiOperation("根据学号和类型查类别")
    @GetMapping({"select_categoryByTypeAndNumber"})
    public JsonData selectCategoryByTypeAndStudentNumber(String studentNumber ,String type){return  JsonData.success(studentService.selectCategoryByTypeAndStudentNumber(studentNumber,type));}



}
