package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class JsonData
          implements Serializable
         {
      private static final long serialVersionUID = 1L;
      private int code;
    /*    */   @JsonInclude(JsonInclude.Include.NON_NULL)
    /*    */   private String msg;
    /*    */   @JsonInclude(JsonInclude.Include.NON_NULL)
    /*    */   private Object data;
    /*    */
    /* 22 */   public JsonData() { this.code = 0; }
    /*    */
    /*    */
    /*    */
    /*    */   public JsonData(String msg) {
        /* 27 */     this.msg = msg;
        /* 28 */     this.code = -1;
        /*    */   }
    /*    */
    /*    */
    /*    */   public JsonData(Object data) {
        /* 33 */     this.data = data;
        /* 34 */     this.code = 0;
        /*    */   }
    /*    */
    /*    */
    /*    */
    /* 39 */   public static JsonData success() { return new JsonData(); }
    /*    */
    /*    */
    /*    */
    /*    */
    /* 44 */   public static JsonData success(Object data) { return new JsonData(data); }
    /*    */
    /*    */
    /*    */
    /*    */
    /* 49 */   public static JsonData fail(String msg) { return new JsonData(msg); }
    /*    */
    /*    */
    /*    */
    /*    */
    /* 54 */   public int getCode() { return this.code; }
    /*    */
    /*    */
    /*    */
    /*    */
    /* 59 */   public void setCode(int code) { this.code = code; }
    /*    */
    /*    */
    /*    */
    /*    */
    /* 64 */   public String getMsg() { return this.msg; }
    /*    */
    /*    */
    /*    */
    /*    */
    /* 69 */   public void setMsg(String msg) { this.msg = msg; }
    /*    */
    /*    */
    /*    */
    /*    */
    /* 74 */   public Object getData() { return this.data; }
    /*    */
    /*    */
    /*    */
    /*    */
    /* 79 */   public void setData(Object data) { this.data = data; }
    /*    */ }