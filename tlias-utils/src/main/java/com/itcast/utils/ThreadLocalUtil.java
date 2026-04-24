package com.itcast.utils;

/**
 * @Description ThreadLocalUtil
 * @Author kight-tom
 * @Date 2026-04-15  16:16
 */
public class ThreadLocalUtil {

    //定义一个ThreadLocal存储员工Id
    //每个线程都会创建，且存储空间互不干扰
    private static ThreadLocal<Integer> loginIdThreadLocal = new ThreadLocal<>();

    public static void setLoginId(Integer empId){
        loginIdThreadLocal.set(empId);
    }

    public static Integer getLoginId(){
        return loginIdThreadLocal.get();
    }

    public static void removeLoginId(){
        loginIdThreadLocal.remove();
    }
}
