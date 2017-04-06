package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AsyncServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncServlet() {
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
        AsyncContext c = request.startAsync();
        w.println(Thread.currentThread().getName()+"<br>");
        c.start(() -> {
            w.println("<progress id='progress' max='100'></progress>");
            
            int i = 0;
            while (i < 100){
                w.println("<script> document.getElementById('progress').value =\""+i++ +"\";</script>");
                w.flush();
                try{Thread.sleep(100);}catch(Exception e){}
            }
            w.println(Thread.currentThread().getName()+"<br>");
            w.println("</body></html>");
            c.complete();
        });
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
