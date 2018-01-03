package com.mycompany.memoproject.controllers;

import com.mycompany.memoproject.model.Memo;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/MemoServlet", "/memos"})
public class MemoServlet extends HttpServlet{
    
    private static final long serialVersionnUID = -7843898075264520941L;
    private ServletConfig config;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendResponse(request, response); 
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config= config; 
    }
//direktno go vikame renderResponse() od MemoViewRender to generate the output. null is parametar, as we don't want to display any errors
//Besides the err attribute we are also calling getMemos to retrieve the user’s memos. Both, a potential error message 
//stored in variable err and the list of Memo objects are then sent to the MemoViewRenderer for updating the HTML page.
    
    private void sendResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
       config.getServletContext().getRequestDispatcher("/WEB-INF/memos.jsp").forward(request, response);
//frstly is retrieving a so we called RequestDispatcher(acts as a wrapper for servlet resources) for the JSP page by calling.
//we can use dispatcher to forward requests from one resourse to another
    }
    

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    request.setCharacterEncoding("UTF-8");
//we’re setting the encoding of the POST request parameters to be UTF-8
//get post parameter for each incoming POST request
//we store this parameter’s value in the variable button.
    final String button = request.getParameter("button");
    switch(button){//we are trying to find out which button the user actually pressed
        case "reset":
            actionReset(request);//method for deleting all previously stored values.
            sendResponse(request, response);
            break;
        case "save":
            actionAddMemo(request);
            sendResponse(request, response);
             break;
        default:
            //no action
         sendResponse(request, response);   
    }
  }

//help method -getMemos, whose task is to retrieve the stored Memo objects from the user’s session
private List<Memo>getMemos(HttpServletRequest request){
    HttpSession session = request.getSession();
    List<Memo> memos = (List<Memo>) session.getAttribute("memos");
////firstly receives an HttpSession object which stores information about the session of the current user.
//The method takes advantage of the so called lazy initialization pattern. In our case that means, if we don’t find a list
//of Memoobjects in the session’s attribute memos, we create a new LinkedList object for storing them.
//After successfully creating it for the first time, we store it in the user’s session
 //by calling the setAttribute method of the session object.
    if(memos == null){
    memos = new LinkedList<>();
    session.setAttribute("memos", memos);
        }
return memos;
    }


private synchronized void actionReset(HttpServletRequest request){
    List<Memo> memos = getMemos(request);
    memos.clear();
    }

private synchronized void actionAddMemo(HttpServletRequest request){
    String memoDescr = request.getParameter("memo");
    if(memoDescr != null && !memoDescr.isEmpty()){
    List<Memo> memos = getMemos(request);
    Memo memo = new Memo();//synchronized za da nema poveke threads istovremeno
//we’re firstly getting the value of the POST parameter memo which was sent by our HTML page.
//If the client is actually sending a memo description, we are creating a new Memo object
//with the description sent and the actual creation timestamp.
    memo.setDescription(memoDescr);
    memo.setCreated(new Date());
    //add todo list
    memos.add(memo);
//we are adding this object to the list of the user’s Memo objects which we are retrieving by calling the getMemos helper method.    
    }else{
//In case the user is not providing any value for the memo, set error message in reequest
//This attribute we will use in the sendResponse method where we are rendering the HTML page.
    request.setAttribute("err", "Please, enter a memo!");
    }
  }
}