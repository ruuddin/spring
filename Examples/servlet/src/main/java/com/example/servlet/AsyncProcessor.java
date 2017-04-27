
package com.example.servlet;

import java.io.PrintWriter;

import javax.servlet.AsyncContext;

public class AsyncProcessor implements Runnable {

    private AsyncContext asyncContext;

    public AsyncProcessor(
            AsyncContext context){
        this.asyncContext = context;
    }

    @Override
    public void run() {
        try {
            PrintWriter w = asyncContext.getResponse().getWriter();
            w.println("<progress id='progress' max='100'></progress>");

            int i = 0;
            while (i < 100) {
                w.println("<script> document.getElementById('progress').value =\"" + i++
                        + "\";</script>");
                w.flush();
                Thread.sleep(100);
            }
            w.println(Thread.currentThread().getName() + "<br>");
            w.println("</body></html>");
            asyncContext.complete();
        }
        catch (Exception e) {}
    }

}
