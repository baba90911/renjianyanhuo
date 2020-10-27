package com.example.demo.service.Impl;

import com.example.demo.dao.*;
import com.example.demo.exception.MyException;
import com.example.demo.pojo.*;
import com.example.demo.pojo.result.Data;
import com.example.demo.service.administratorService;
import com.example.demo.util.ImageUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

@Service
public class administratorImpl implements administratorService {

    @Autowired
    ExamMapper examMapper;

    @Autowired
    RuleMapper ruleMapper;

    @Autowired
    PicturesMapper picturesMapper;



    @Override
    public void updateRuleByCategory(String rule, String category) {
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("rule", rule);
        params.put("category", category);
        ruleMapper.updateRuleByCategory(params);
    }

    @Override
    public String selectRuleByCategory(String category) {
        return ruleMapper.selectRuleByCategory(category);
    }

    @Override
    public void addImages(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        MultiValueMap<String,MultipartFile> map =request.getMultiFileMap();
        List<MultipartFile> list =map.get("uploadify");
        for(MultipartFile pho1: list) {

                String picturename = pho1.getOriginalFilename();
                String studentnumber = picturename.substring(0, picturename.length() - 4);
                //String folder = "img/";
                String filepath="C:/Users/Administrator/Desktop/zjgsdxzkzjdyxt/src/main/resources/static/img";

                File imageFolder = new File(filepath);
                File file = new File(imageFolder, picturename);
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                //String path = file.getAbsolutePath();
                String path="Http://10.11.139.79:8080/"+picturename;
                pho1.transferTo(file);
                BufferedImage img = ImageUtil.change2jpg(file);
                ImageIO.write(img, "jpg", file);
                Pictures pictures = new Pictures();
                pictures.setPictureUrl(path);
                pictures.setPictureName(studentnumber);
                picturesMapper.insert(pictures);
                examMapper.updatePictureByPictureName(studentnumber);
                System.out.println("插入成功");

        }
        response.sendRedirect("http://10.11.139.79:8080/administrator.html");

    }

    @Override
    public List<String> selectCategories() {
        return ruleMapper.selectCategories();
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("获取excel测试表格");

        HSSFRow row = null;

        row = sheet.createRow(0);//创建第一个单元格
        row.setHeight((short) (26.25 * 20));
        row.createCell(0).setCellValue("考试信息列表");//为第一行单元格设值

        /*为标题设计空间
         * firstRow从第1行开始
         * lastRow从第0行结束
         *
         *从第1个单元格开始
         * 从第3个单元格结束
         */
        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion(rowRegion);

      /*CellRangeAddress columnRegion = new CellRangeAddress(1,4,0,0);
      sheet.addMergedRegion(columnRegion);*/


        /*
         * 动态获取数据库列 sql语句 select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='user' and table_schema='test'
         * 第一个table_name 表名字
         * 第二个table_name 数据库名称
         * */
        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));//设置行高
        row.createCell(0).setCellValue("准考证号");//为第一个单元格设值
        row.createCell(1).setCellValue("学号");
        row.createCell(2).setCellValue("密码");//为第二个单元格设值
        row.createCell(3).setCellValue("学校名称");//为第三个单元格设值
        row.createCell(4).setCellValue("学校代码");//为第一个单元格设值
        row.createCell(5).setCellValue("性别");
        row.createCell(6).setCellValue("考生姓名");//为第二个单元格设值
        row.createCell(7).setCellValue("班级名称");//为第三个单元格设值
        row.createCell(8).setCellValue("图片名称");//为第一个单元格设值
        row.createCell(9).setCellValue("考生须知(类别)");
        row.createCell(10).setCellValue("语种名称");//为第二个单元格设值
        row.createCell(11).setCellValue("考试类型");//为第三个单元格设值
        row.createCell(12).setCellValue("是否打印(导入时填0)");
        row.createCell(13).setCellValue("考试教室");//为第二个单元格设值
        row.createCell(14).setCellValue("考试时间");//为第三个单元格设值
        row.createCell(15).setCellValue("考场号");//为第一个单元格设值
        row.createCell(16).setCellValue("座位号");
        row.createCell(17).setCellValue("考试教室2(可为空)");//为第二个单元格设值
        row.createCell(18).setCellValue("考试时间2(可为空)");//为第三个单元格设值
        row.createCell(19).setCellValue("学院信息");//为第一个单元格设值
        row.createCell(20).setCellValue("其他信息2(判断照片是否上传初始为0,上传后为1)");

        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 13; i++) {
            sheet.autoSizeColumn(i);
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=user.xls");//默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
    }


    @Override
    public boolean examImport(String fileName, MultipartFile file) throws Exception {
        boolean notNull =false;
        List<Exam> examList=new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }

        Exam exam;
        for(int r=2;r<=sheet.getLastRowNum();r++){
            Row row=sheet.getRow(r);
            if(row==null){
                continue;
            }
            exam =new Exam();
            if(row.getCell(0).getCellType()!=1){
                throw  new MyException("导入失败(第"+(r+1)+"行,请设为文本格式)");
            }
            String examNumber =row.getCell(0).getStringCellValue();
            if(examNumber==null || examNumber.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,准考证号未填写)");
            }

            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String studentNumber = row.getCell(1).getStringCellValue();
            if(studentNumber==null || studentNumber.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,学号未填写)");
            }

            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String password = row.getCell(2).getStringCellValue();
            if(password==null || password.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,密码未填写)");
            }

            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String schoolName = row.getCell(3).getStringCellValue();
            if(schoolName==null || schoolName.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,学校名称未填写)");
            }

            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String shoolCode = row.getCell(4).getStringCellValue();
            if(shoolCode==null || shoolCode.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,学校代码未填写)");
            }

            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String sex = row.getCell(5).getStringCellValue();
            if(sex==null || sex.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,性别未填写)");
            }

            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String studentName = row.getCell(6).getStringCellValue();
            if(studentName==null || studentName.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,考生姓名未填写)");
            }

            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String className = row.getCell(7).getStringCellValue();
            if(className==null || className.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,班级名称未填写)");
            }


            row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String pictureName = row.getCell(8).getStringCellValue();
            if(pictureName==null || pictureName.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,图片名称未填写)");
            }

            row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String examRule = row.getCell(9).getStringCellValue();
            if(examRule==null || examRule.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,考试类别（规则）未填写)");
            }

            row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String languageName = row.getCell(10).getStringCellValue();
            if(languageName==null || languageName.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,语种名称未填写)");
            }

            row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String examType = row.getCell(11).getStringCellValue();
            if(examType==null || examType.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,考试类型未填写)");
            }


            row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String isPrint = row.getCell(12).getStringCellValue();
            if(isPrint==null || isPrint.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,是否打印未填写)");
            }


            String examClassroom=null;
            if(row.getCell(13) == null){
                row.createCell(13).setCellValue("空");
            }else {
                examClassroom =row.getCell(13).getStringCellValue();
                //之前有值的getCell（）可以返回值，之前没有值会返回null
            }
            if(examClassroom==null || examClassroom.isEmpty()){
                System.out.println("考试教室未填写，但是没事");
            }



            String examTime=null;
            if(row.getCell(14) == null){
                row.createCell(14).setCellValue("空");
            }else {
                examTime =row.getCell(14).getStringCellValue();
                //之前有值的getCell（）可以返回值，之前没有值会返回null
            }
            if(examTime==null || examTime.isEmpty()){
                System.out.println("考试时间未填写，但是没事");
            }

            row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String roomNumber = row.getCell(15).getStringCellValue();
            if(roomNumber==null || roomNumber.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,考场号未填写)");
            }

            row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String seatNumber = row.getCell(16).getStringCellValue();
            if(seatNumber==null || seatNumber.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,座位号未填写)");
            }

            String examClassroom2=null;
            if(row.getCell(17) == null){
                row.createCell(17).setCellValue("空");
            }else {
                examClassroom2 =row.getCell(17).getStringCellValue();
                //之前有值的getCell（）可以返回值，之前没有值会返回null
            }
            if(examClassroom2==null || examClassroom2.isEmpty()){
                System.out.println("考试教室2未填写，但是没事");
            }

            String examTime2=null;
            if(row.getCell(18) == null){
                row.createCell(18).setCellValue("空");
            }else {
                examTime2 =row.getCell(18).getStringCellValue();
                //之前有值的getCell（）可以返回值，之前没有值会返回null
            }
            if(examTime2==null || examTime2.isEmpty()){
                System.out.println("考试时间2未填写，但是没事");
            }

            String college=null;
            if(row.getCell(19) == null){
                row.createCell(19).setCellValue("空");
            }else {
                college =row.getCell(19).getStringCellValue();
                //之前有值的getCell（）可以返回值，之前没有值会返回null
            }
            if(college==null || college.isEmpty()){
                System.out.println("学院未填写，但是没事");
            }

            String other1=null;
            if(row.getCell(20) == null){
                row.createCell(20).setCellValue("空");
            }else {
                other1 =row.getCell(20).getStringCellValue();
                //之前有值的getCell（）可以返回值，之前没有值会返回null
            }
            if(other1==null || other1.isEmpty()){
                System.out.println("其他信息2未填写，但是没事");
            }

            exam.setExamNumber(examNumber);
            exam.setStudentNumber(studentNumber);
            exam.setPassword(password);
            exam.setSchoolName(schoolName);
            exam.setShoolCode(shoolCode);
            exam.setSex(sex);
            exam.setStudentName(studentName);
            exam.setClassName(className);
            exam.setPictureName(pictureName);
            exam.setExamRule(examRule);
            exam.setLanguageName(languageName);
            exam.setExamType(examType);
            exam.setIsPrint(isPrint);
            exam.setExamClassroom(examClassroom);
            exam.setExamTime(examTime);
            exam.setRoomNumber(roomNumber);
            exam.setSeatNumber(seatNumber);
            if(examClassroom2!=null){
                exam.setExamClassroom2(examClassroom2);
            }
            if(examTime2!=null){
                exam.setExamTime2(examTime2);
            }
            if(college!=null){
                exam.setCollege(college);
            }
            if(other1!=null){
                exam.setOther1(other1);
            }
            examList.add(exam);
        }

        for(Exam exam1 :examList){
            String examNumber =exam1.getExamNumber();
            int cnt =examMapper.selectByExamNumber(examNumber);
            if(cnt==0){
                examMapper.insert(exam1);
                System.out.println("插入"+exam1);
            }else{
                examMapper.updateByPrimaryKey(exam1);
                System.out.println("更新"+exam1);
            }
        }
        return notNull;
    }

    @Override
    public boolean deleteStudnetByCategory(String category) {
        List<String> lists=examMapper.selectPictureNameByCategory(category);
        File folder = new File("C:/Users/Administrator/Desktop/zjgsdxzkzjdyxt/src/main/resources/static/img");
        File[] files = folder.listFiles();
        for(int j=0;j<lists.size();j++){
            for(File file:files){
                if(file.getName().equals(lists.get(j)+".jpg")){
                    file.delete();
                }
            }
        }
        int i=ruleMapper.deleteByPrimaryKey(category);
        if(i>0) return true;
        else return false;
    }



    @Override
    public Data selectAllData() {
        Data data =new Data();
        int i=examMapper.selectAllStudentCount();
        int j=examMapper.selectAllPrintCount();
        data.setAll(i);
        data.setPrint(j);
        double k=(float)j/i;
        NumberFormat nt = NumberFormat.getPercentInstance();
        nt.setMinimumFractionDigits(2);
        data.setPercent(nt.format(k));
        return data;
    }

    @Override
    public Data selectCategoryData(String category) {
        Data data =new Data();
        int i=examMapper.selectStudentCountByCategory(category);
        int j=examMapper.selectPrintCountByCategory(category);
        data.setAll(i);
        data.setPrint(j);
        double k=(float)j/i;
        NumberFormat nt = NumberFormat.getPercentInstance();
        nt.setMinimumFractionDigits(2);
        data.setPercent(nt.format(k));
        return data;
    }

    @Override
    public Data selectTypeData(String type) {
        Data data =new Data();
        int i=examMapper.selectStudentCountByType(type);
        int j=examMapper.selectPrintCountByType(type);
        data.setAll(i);
        data.setPrint(j);
        double k=(float)j/i;
        NumberFormat nt = NumberFormat.getPercentInstance();
        nt.setMinimumFractionDigits(2);
        data.setPercent(nt.format(k));
        return data;
    }

    @Override
    public int updataPrintByExamNumber(String examNumber) {
        return examMapper.updataPrintByExamNumber(examNumber);
    }

    @Override
    public int updateStartTimeByCategory(String category, Date startTime) {
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("startTime", startTime);
        params.put("category", category);
        return ruleMapper.updateStartTimeByCategory(params);
    }

    @Override
    public int updateEndTimeByCategory(String category, Date endTime) {
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("endTime", endTime);
        params.put("category", category);
        return ruleMapper.updateEndTimeByCategory(params);
    }

    @Override
    public int insertNewRule(Rule rule) {
        return ruleMapper.insert(rule);
    }

    @Override
    public List<String> selectType() {
        return examMapper.selectType();
    }

    @Override
    public void exportExcelByCategory(HttpServletResponse response, String category) throws IOException {
        List<Exam> exams = examMapper.selectByCategory(category);

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("获取excel测试表格");

        HSSFRow row = null;

        row = sheet.createRow(0);//创建第一个单元格
        row.setHeight((short) (26.25 * 20));
        row.createCell(0).setCellValue("学生信息列表");//为第一行单元格设值


        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion(rowRegion);


        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));//设置行高
        row.createCell(0).setCellValue("准考证号");//为第一个单元格设值
        row.createCell(1).setCellValue("学号");
        row.createCell(2).setCellValue("密码");//为第二个单元格设值
        row.createCell(3).setCellValue("学校名称");//为第三个单元格设值
        row.createCell(4).setCellValue("学校代码");//为第一个单元格设值
        row.createCell(5).setCellValue("性别");
        row.createCell(6).setCellValue("考生姓名");//为第二个单元格设值
        row.createCell(7).setCellValue("班级名称");//为第三个单元格设值
        row.createCell(8).setCellValue("图片名称");//为第一个单元格设值
        row.createCell(9).setCellValue("考生须知(类别)");
        row.createCell(10).setCellValue("语种名称");//为第二个单元格设值
        row.createCell(11).setCellValue("考试类型");//为第三个单元格设值
        row.createCell(12).setCellValue("是否打印(导入时填0)");
        row.createCell(13).setCellValue("考试教室");//为第二个单元格设值
        row.createCell(14).setCellValue("考试时间");//为第三个单元格设值
        row.createCell(15).setCellValue("考场号");//为第一个单元格设值
        row.createCell(16).setCellValue("座位号");
        row.createCell(17).setCellValue("考试教室2(可为空)");//为第二个单元格设值
        row.createCell(18).setCellValue("考试时间2(可为空)");//为第三个单元格设值
        row.createCell(19).setCellValue("学院信息");//为第一个单元格设值
        row.createCell(20).setCellValue("其他信息2(可为空)");

        for (int i = 0; i < exams.size(); i++) {
            row = sheet.createRow(i + 2);
            Exam exam=exams.get(i);
            row.createCell(0).setCellValue(exam.getExamNumber());
            row.createCell(1).setCellValue(exam.getStudentNumber());
            row.createCell(2).setCellValue(exam.getPassword());
            row.createCell(3).setCellValue(exam.getSchoolName());
            row.createCell(4).setCellValue(exam.getShoolCode());
            row.createCell(5).setCellValue(exam.getSex());
            row.createCell(6).setCellValue(exam.getStudentName());
            row.createCell(7).setCellValue(exam.getClassName());
            row.createCell(8).setCellValue(exam.getPictureName());
            row.createCell(9).setCellValue(exam.getExamRule());
            row.createCell(10).setCellValue(exam.getLanguageName());
            row.createCell(11).setCellValue(exam.getExamType());
            row.createCell(12).setCellValue(exam.getIsPrint());
            row.createCell(13).setCellValue(exam.getExamClassroom());
            row.createCell(14).setCellValue(exam.getExamTime());
            row.createCell(15).setCellValue(exam.getRoomNumber());
            row.createCell(16).setCellValue(exam.getSeatNumber());
            row.createCell(17).setCellValue(exam.getExamClassroom2());
            row.createCell(18).setCellValue(exam.getExamTime2());
            row.createCell(19).setCellValue(exam.getCollege());
            row.createCell(20).setCellValue(exam.getOther1());
        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 20; i++) {
            sheet.autoSizeColumn(i);
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=user.xls");//默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
    }

    @Override
    public void exportExcelByType(HttpServletResponse response, String type) throws IOException {
        List<Exam> exams = examMapper.selectByType(type);

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("获取excel测试表格");

        HSSFRow row = null;

        row = sheet.createRow(0);//创建第一个单元格
        row.setHeight((short) (26.25 * 20));
        row.createCell(0).setCellValue("学生信息列表");//为第一行单元格设值


        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
        sheet.addMergedRegion(rowRegion);


        row = sheet.createRow(1);
        row.setHeight((short) (22.50 * 20));//设置行高
        row.createCell(0).setCellValue("准考证号");//为第一个单元格设值
        row.createCell(1).setCellValue("学号");
        row.createCell(2).setCellValue("密码");//为第二个单元格设值
        row.createCell(3).setCellValue("学校名称");//为第三个单元格设值
        row.createCell(4).setCellValue("学校代码");//为第一个单元格设值
        row.createCell(5).setCellValue("性别");
        row.createCell(6).setCellValue("考生姓名");//为第二个单元格设值
        row.createCell(7).setCellValue("班级名称");//为第三个单元格设值
        row.createCell(8).setCellValue("图片名称");//为第一个单元格设值
        row.createCell(9).setCellValue("考生须知(类别)");
        row.createCell(10).setCellValue("语种名称");//为第二个单元格设值
        row.createCell(11).setCellValue("考试类型");//为第三个单元格设值
        row.createCell(12).setCellValue("是否打印(导入时填0)");
        row.createCell(13).setCellValue("考试教室");//为第二个单元格设值
        row.createCell(14).setCellValue("考试时间");//为第三个单元格设值
        row.createCell(15).setCellValue("考场号");//为第一个单元格设值
        row.createCell(16).setCellValue("座位号");
        row.createCell(17).setCellValue("考试教室2(可为空)");//为第二个单元格设值
        row.createCell(18).setCellValue("考试时间2(可为空)");//为第三个单元格设值
        row.createCell(19).setCellValue("学院信息");//为第一个单元格设值
        row.createCell(20).setCellValue("其他信息2(可为空)");

        for (int i = 0; i < exams.size(); i++) {
            row = sheet.createRow(i + 2);
            Exam exam=exams.get(i);
            System.out.println(exam.getClassName());
            row.createCell(0).setCellValue(exam.getExamNumber());
            row.createCell(1).setCellValue(exam.getStudentNumber());
            row.createCell(2).setCellValue(exam.getPassword());
            row.createCell(3).setCellValue(exam.getSchoolName());
            row.createCell(4).setCellValue(exam.getShoolCode());
            row.createCell(5).setCellValue(exam.getSex());
            row.createCell(6).setCellValue(exam.getStudentName());
            row.createCell(7).setCellValue(exam.getClassName());
            row.createCell(8).setCellValue(exam.getPictureName());
            row.createCell(9).setCellValue(exam.getExamRule());
            row.createCell(10).setCellValue(exam.getLanguageName());
            row.createCell(11).setCellValue(exam.getExamType());
            row.createCell(12).setCellValue(exam.getIsPrint());
            row.createCell(13).setCellValue(exam.getExamClassroom());
            row.createCell(14).setCellValue(exam.getExamTime());
            row.createCell(15).setCellValue(exam.getRoomNumber());
            row.createCell(16).setCellValue(exam.getSeatNumber());
            row.createCell(17).setCellValue(exam.getExamClassroom2());
            row.createCell(18).setCellValue(exam.getExamTime2());
            row.createCell(19).setCellValue(exam.getCollege());
            row.createCell(20).setCellValue(exam.getOther1());
        }
        sheet.setDefaultRowHeight((short) (16.5 * 20));
        //列宽自适应
        for (int i = 0; i <= 20; i++) {
            sheet.autoSizeColumn(i);
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=user.xls");//默认Excel名称
        wb.write(os);
        os.flush();
        os.close();
    }




}
