package com.example.demo.service;

import com.example.demo.pojo.Rule;
import com.example.demo.pojo.result.Data;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


public interface administratorService {

    void updateRuleByCategory(String rule, String  category);

    String selectRuleByCategory(String category);

    void addImages( MultipartHttpServletRequest request, HttpServletResponse response)throws IOException;

    List<String> selectCategories();

    void exportExcel(HttpServletResponse response) throws IOException;

    boolean  examImport(String fileName,MultipartFile file)throws  Exception;

    boolean  deleteStudnetByCategory(String category);


    Data  selectAllData();

    Data  selectCategoryData(String category);

    Data  selectTypeData(String type);

    int updataPrintByExamNumber(String examNumber);

    int updateStartTimeByCategory(String category, Date startTime);

    int updateEndTimeByCategory(String category, Date endTime);

    int insertNewRule(Rule rule);

    List<String> selectType();

    void exportExcelByCategory(HttpServletResponse response,String category) throws IOException;

    void exportExcelByType(HttpServletResponse response,String type) throws IOException;


}
