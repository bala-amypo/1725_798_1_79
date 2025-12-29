package com.example.demo;



public class TestResultListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " - PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " - FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " - SKIP");
    }
}