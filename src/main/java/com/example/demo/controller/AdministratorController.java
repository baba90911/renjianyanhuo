package com.example.demo.controller;
import com.example.demo.dao.ExamMapper;
import com.example.demo.dao.PicturesMapper;
import com.example.demo.pojo.*;
import com.example.demo.service.administratorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@RestController
@RequestMapping({"/v1/api/admin"})
@Api(description = "管理员操作")
@CrossOrigin
public class AdministratorController {

    Logger logger=LoggerFactory.getLogger(getClass());



    @Autowired
    PicturesMapper picturesMapper;

    @Autowired
    ExamMapper examMapper;


    @Autowired
    administratorService administratorService;

    @ApiOperation("导入学生图片")
    @PostMapping(value = "/studentImages",consumes = "multipart/*")
    public void addImages( MultipartHttpServletRequest request,HttpServletResponse response)throws IOException {
      administratorService.addImages(request,response);
    }





    @ApiOperation("导出考试信息数据模板")
    @GetMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletResponse response) throws IOException {
        administratorService.exportExcel(response);
    }

    @ApiOperation("根据类别导出用户信息数据模板")
    @GetMapping(value = "/export_byCategory")
    @ResponseBody
    public void exportByCategory(HttpServletResponse response,@RequestParam("exam_category")String category) throws IOException {
        administratorService.exportExcelByCategory(response,category);
    }

    @ApiOperation("根据类型导出用户信息数据模板")
    @GetMapping(value = "/export_byType")
    @ResponseBody
    public void exportByType(HttpServletResponse response,@RequestParam("exam_type") String type) throws IOException {
        administratorService.exportExcelByType(response,type);
    }



    @ApiOperation("查询准考证类型")
    @GetMapping({"get_all_categories"})
    public JsonData getAllCategories(){
        try{
            return JsonData.success(administratorService.selectCategories());
        }catch (Exception e){
            this.logger.error(e.getMessage());
            return JsonData.fail(e.getMessage());
        }
    }


    @ApiOperation("修改注意项")
    @PutMapping({"update_rule"})
    public void updateRule(String rule ,String category){
        administratorService.updateRuleByCategory(rule,category);
    }


    @ApiOperation("根据类型查询注意项")
    @PutMapping({"select_rule"})
    public String selectRule(String category){
        return administratorService.selectRuleByCategory(category);
    }




    @ApiOperation("删除过去考试信息")
    @DeleteMapping({"delete_exam"})
    public JsonData deleteStudnetByCategory(@RequestParam String category){return  JsonData.success(this.administratorService.deleteStudnetByCategory(category));}

    @ApiOperation("导入准考证数据")
    @PostMapping({"import_exam"})
    public JsonData exImport(@RequestParam(value = "filename") MultipartFile file, HttpSession session){
        boolean a=false;
        String filename=file.getOriginalFilename();
        try{
            a=administratorService.examImport(filename,file);
            a=true;
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.success(e);
        }
        return JsonData.success(a);
    }

    @ApiOperation("查询所有打印信息和百分比")
    @GetMapping({"get_allData"})
    public JsonData getAllData(){return  JsonData.success(administratorService.selectAllData());}

    @ApiOperation("查询类别考试打印信息和百分比")
    @GetMapping({"get_categoryData"})
    public JsonData getCategoryData(String category){return  JsonData.success(administratorService.selectCategoryData(category));}

    @ApiOperation("查询具体考试打印信息和百分比")
    @GetMapping({"get_typeData"})
    public JsonData getTypeData(String type){return  JsonData.success(administratorService.selectTypeData(type));}


    @ApiOperation("更新打印信息")
    @PutMapping({"update_print"})
    public JsonData updataPrintByExamNumber(String examNumber){return  JsonData.success(administratorService.updataPrintByExamNumber(examNumber));}

    @ApiOperation("更改准考证打印开始时间")
    @PutMapping({"update_startTime"})
    public JsonData updateStartTimeByCategory(String category, Date startTime){return  JsonData.success(administratorService.updateStartTimeByCategory(category,startTime));}

    @ApiOperation("更改准考证打印结束时间")
    @PutMapping({"update_endTime"})
    public JsonData updateEndTimeByCategory(String category, Date endTime){return  JsonData.success(administratorService.updateEndTimeByCategory(category,endTime));}

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

    @ApiOperation("增加新的考试类别和规则")
    @PostMapping({"insert_newRule"})
    public JsonData insertNewRule(@RequestBody Rule rule){
        System.out.println(rule.getExamName());
        return JsonData.success(administratorService.insertNewRule(rule));}


    @ApiOperation("查询已有考试类型")
    @GetMapping({"select_type"})
    public JsonData selectType(){return  JsonData.success(administratorService.selectType());}

    @ApiOperation("根据类型查询考试信息")
    @GetMapping({"select_byType"})
    public PageInfo selectByType(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size,String type)
    {
        PageHelper.startPage(start,size,"exam_number asc");
        List<Exam> exams=examMapper.selectByType(type);
        PageInfo<Exam> page = new PageInfo<>(exams);
        return page;
    }

    @ApiOperation("根据类别查询考试信息")
    @GetMapping({"select_byCategory"})
    public PageInfo selectByCategory(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size,String category)
    {
        PageHelper.startPage(start,size,"exam_number asc");
        List<Exam> exams=examMapper.selectByCategory(category);
        PageInfo<Exam> page = new PageInfo<>(exams);
        return page;
    }

}
