package com.example.demo.pojo;

import java.io.Serializable;

public class Exam implements Serializable {
    private String examNumber;

    private String studentNumber;

    private String password;

    private String schoolName;

    private String shoolCode;

    private String sex;

    private String studentName;

    private String className;

    private String pictureName;

    private String examRule;

    private String languageName;

    private String examType;

    private String isPrint;

    private String examClassroom;

    private String examTime;

    private String roomNumber;

    private String seatNumber;

    private String examClassroom2;

    private String examTime2;

    private String college;

    private String other1;

    private static final long serialVersionUID = 1L;

    public String getExamNumber() {
        return examNumber;
    }

    public void setExamNumber(String examNumber) {
        this.examNumber = examNumber == null ? null : examNumber.trim();
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber == null ? null : studentNumber.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getShoolCode() {
        return shoolCode;
    }

    public void setShoolCode(String shoolCode) {
        this.shoolCode = shoolCode == null ? null : shoolCode.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName == null ? null : pictureName.trim();
    }

    public String getExamRule() {
        return examRule;
    }

    public void setExamRule(String examRule) {
        this.examRule = examRule == null ? null : examRule.trim();
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName == null ? null : languageName.trim();
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType == null ? null : examType.trim();
    }

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint == null ? null : isPrint.trim();
    }

    public String getExamClassroom() {
        return examClassroom;
    }

    public void setExamClassroom(String examClassroom) {
        this.examClassroom = examClassroom == null ? null : examClassroom.trim();
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime == null ? null : examTime.trim();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber == null ? null : roomNumber.trim();
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber == null ? null : seatNumber.trim();
    }

    public String getExamClassroom2() {
        return examClassroom2;
    }

    public void setExamClassroom2(String examClassroom2) {
        this.examClassroom2 = examClassroom2 == null ? null : examClassroom2.trim();
    }

    public String getExamTime2() {
        return examTime2;
    }

    public void setExamTime2(String examTime2) {
        this.examTime2 = examTime2 == null ? null : examTime2.trim();
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1 == null ? null : other1.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", examNumber=").append(examNumber);
        sb.append(", studentNumber=").append(studentNumber);
        sb.append(", password=").append(password);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", shoolCode=").append(shoolCode);
        sb.append(", sex=").append(sex);
        sb.append(", studentName=").append(studentName);
        sb.append(", className=").append(className);
        sb.append(", pictureName=").append(pictureName);
        sb.append(", examRule=").append(examRule);
        sb.append(", languageName=").append(languageName);
        sb.append(", examType=").append(examType);
        sb.append(", isPrint=").append(isPrint);
        sb.append(", examClassroom=").append(examClassroom);
        sb.append(", examTime=").append(examTime);
        sb.append(", roomNumber=").append(roomNumber);
        sb.append(", seatNumber=").append(seatNumber);
        sb.append(", examClassroom2=").append(examClassroom2);
        sb.append(", examTime2=").append(examTime2);
        sb.append(", college=").append(college);
        sb.append(", other1=").append(other1);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}