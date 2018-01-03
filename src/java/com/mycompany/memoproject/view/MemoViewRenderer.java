package com.mycompany.memoproject.view;

import com.mycompany.memoproject.model.Memo;
import java.util.List;

public class MemoViewRenderer {
    
    public static String renderResponse(List<Memo> memos, String err){
    String part1 = "!DOCTYPE html>" + "<html>" +"<head>"+"<meta charset=\"UTF-8\">"+
    "<title>My-memo</title>"+"<link rel=\"stylesheet\" type=\"text/css\" href=\"screen.css\">"+
    "</head>"+"<body>"+"<div id=\"container\">"+"<div id=\"header\">"+
    "<p><b>My-Memo</b>- a simple web app for managing memos</p>"+
    "</div>"+"<div id=\"content\">"+"<h1>Add Memo:</h1>"+
    "<form method=\"POST\" action=\"\">"+"<input type=\"text\" name=\"memo\" size=\"30\" placeholder=\"Enter your memo here\"/>"
    + "<button type=\"submit\" name=\"button\" value=\"save\">Add</button>";
    
    StringBuilder output = new StringBuilder(part1);
    if (err != null){
        System.out.println("err = " + err);
        output.append("<span style = \"color:red\">").append(err).append("</span>");
    }
    
    String part2 = "<hi>My memos</h1>";
    output.append(part2).append(renderTable(memos));
    
    String part3 = "</form>" + "</div>"+"<div id=\"footer\">"+
            "<p>(c) 2015 Schiesser/Schmollinger, MIT license</p>"+
            "</div>"+"</body>" + "</html>";
    output.append(part3);
    return output.toString();
    }
    
            
    private static String renderTable(List<Memo> memos){
     StringBuilder table = new StringBuilder();
     if(memos.isEmpty()){
            table.append("<p>Please add some memos.</p>");
     }else{
        table.append("<table><tr><th>Memo</th><th>Saved</th></tr>");//adding a header
     }//if there is entries, iterating over the list of memos objects and appends, for each entry- en row
        for (Memo memo : memos) {
             table.append("<tr><td>").append(memo.getDescription()).append("</td>");
             table.append("<td>").append(memo.getCreated()).append("</td></tr>");
        }//a button is created to let the user to delete all memo entries
    table.append("</table><br/><button type=\"submit\" name=\"button\" value=\"reset\">Reset list</button>");
        return table.toString();
    }
}
