/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author student
 */
@WebServlet(name = "CalcServlet", urlPatterns = {"/CalcServlet"})
public class CalcServlet extends HttpServlet {
    
    private final static String SESSION_HISTORY_ID = "calcHistory";
    private final static String COOKIE_NAME = "cookieski";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String helloString = "";
        
        HttpSession sesja = request.getSession(true);
        ArrayList<String> wartosc = (ArrayList<String>) sesja.getAttribute(SESSION_HISTORY_ID);
        if (wartosc==null) { //nie ma szukanego obiektu w sesji
            wartosc = new ArrayList<String>();
            sesja.setAttribute(SESSION_HISTORY_ID,wartosc);
        }
        if(request.getParameter("clear") != null){
            wartosc = new ArrayList<String>();
            sesja.setAttribute(SESSION_HISTORY_ID,wartosc);
        }
        
        
        Cookie[] cookies = request.getCookies();
        if ( cookies != null ){ 
            for(int i=0; i<cookies.length; i++) {
                Cookie c=cookies[i];
                if (COOKIE_NAME.equals(c.getName()))
                    helloString = "Witaj po raz kolejny :)";
                else{
                    helloString = "Witaj po raz pierwszy";
                    response.addCookie(new Cookie(COOKIE_NAME,"true"));
                }
            }
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(request.getParameter("first") == null || request.getParameter("first").trim().equals("") || request.getParameter("second") == null || request.getParameter("second").trim().equals("")){
                out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalcServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.print("<h1>Brakuje ktoregos elementu</h1>");
            out.println("</body>");
            out.println("</html>");
            }
            else {
                Float first=Float.parseFloat(request.getParameter("first"));
            Float second=Float.parseFloat(request.getParameter("second"));
            String operation=request.getParameter("operation");
            double wynik = 0.0;
            
            switch(operation){
                case "+":
                    wynik = first + second;
                    break;
                case "-":
                    wynik = first - second;
                    break;
                case "*":
                    wynik = first * second;
                    break;
                case "/":
                    if (second == 0){
                        out.println("Nie dziel przez 0");
                        break;
                    }
                    else{
                        wynik = first / second;
                        break;
                    }
            }
            wartosc.add(Float.toString(first) + " " + operation + " " + Float.toString(second) + " = " + Double.toString(wynik));
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalcServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>"+helloString+"</h2>");
            out.println("<div>");
            out.println("<a href='/LAB1/calc.html'>Powrót do formularza</a>");
            out.println("<a href='"+request.getRequestURL()+"?clear'>Wyczyść historię sesji</a>");
            out.println("</div>");
            out.println("<h1>Wynik obliczeń:</h1>");
            out.println("<div>"+wynik+"</div>");
            out.println("<h1>Historia obliczeń z sesji:</h1>");
            out.println("<div>"+showHistory(wartosc)+"</div>");
            out.println("</body>");
            out.println("</html>");
            }
            
            }
            
            
        }

    private String showHistory(ArrayList<String> list) {
        String temp = "";
        for(String s: list){
            temp+=s+"<br>";
        }
        return temp;
    }

   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
