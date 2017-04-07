package com.example.servlet;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

public class AsyncServletListener implements AsyncListener {

    @Override
    public void onComplete(AsyncEvent arg0) throws IOException {
        System.out.println("onComplete");
    }

    @Override
    public void onError(AsyncEvent arg0) throws IOException {
        System.out.println("onError");
    }

    @Override
    public void onStartAsync(AsyncEvent arg0) throws IOException {
        System.out.println("onStartAsync");
    }

    @Override
    public void onTimeout(AsyncEvent arg0) throws IOException {
        System.out.println("ontimeout");
    }

}
