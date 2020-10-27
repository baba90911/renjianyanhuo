package com.example.demo.service.Impl;

import com.example.demo.dao.AdministratorMapper;
import com.example.demo.dao.ExamMapper;
import com.example.demo.pojo.Administrator;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    AdministratorMapper administratorMapper;


    @Autowired
    ExamMapper examMapper;

    @Override
    public boolean login(User user) {
        Boolean judge=false;
        if(user.getType()==0){
            String password=examMapper.selectPasswordByStudent(user.getUsername());

            if(password==null){
                judge=false;
            }else {
                if(password.equals(user.getPassword())){
                    judge=true;
                }
                else{
                    judge=false;
                }
            }
        }else if(user.getType()==1){
            Administrator administrator=administratorMapper.selectByPrimaryKey(user.getUsername());

            if(administrator==null){
                judge=false;
            }else{
                if(administrator.getPassword().equals(user.getPassword())){
                    judge=true;
                }else{
                    judge=false;
                }
            }

        }
        return  judge;
    }
}
