//package DP;
//
//import javafx.scene.input.DataFormat;
//
//import java.math.BigDecimal;
//import java.text.DateFormat;
//import java.util.Date;
//import java.util.Scanner;
//import java.util.concurrent.Executors;
//import java.util.concurrent.LinkedBlockingDeque;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author Mingming
// * @Description
// * @Date Created in 13:05 2018/3/25
// * @Modificd By
// */
//public class knapsack01 {
//    public static void main(String[] args) {
//        Executors.newScheduledThreadPool(5).scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("开始时间:"+ DateFormat.getDateInstance().format(new Date()));
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("结束时间:"+ DateFormat.getDateInstance().format(new Date()));
//            }
//        },10,20,TimeUnit.MILLISECONDS);
//        new ThreadPoolExecutor(5,5,0L,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>()){
//            @Override
//            protected void beforeExecute(Thread t, Runnable r) {
//                super.beforeExecute(t, r);
//            }
//
//            @Override
//            protected void afterExecute(Runnable r, Throwable t) {
//                super.afterExecute(r, t);
//            }
//
//            @Override
//            protected void terminated() {
//                super.terminated();
//            }
//        };
//
//
//    }
//}
