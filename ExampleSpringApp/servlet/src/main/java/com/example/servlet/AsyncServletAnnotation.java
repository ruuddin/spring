package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AsyncServletAnnotation", urlPatterns = {"/asyncAnnotationServlet"}, asyncSupported = true)
public class AsyncServletAnnotation extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncServletAnnotation() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        PrintWriter w = response.getWriter();
        w.println("<html><head><title>AsyncProgressServlet</title></head>");
        w.println(Thread.currentThread().getName()+"<br>");

        final AsyncContext c = request.startAsync(request, response);
        c.addListener(new AsyncServletListener());
        c.setTimeout(10000); //fires a timeout event from listener
        
        final ExecutorService ex = Executors.newCachedThreadPool();
        ex.execute(new AsyncProcessor(c));
        ex.shutdown();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
