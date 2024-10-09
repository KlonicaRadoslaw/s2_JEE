/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.lab1;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.util.ArrayList;

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
        
        String wynik = Oblicz(request);
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
        else{
            wartosc.add(wynik);
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
    
    public String showHistory(ArrayList<String> list){
        String temp = "";
        for(String s: list){
            temp+=s+"<br>";
        }
        return temp;
    }
    
    public String Oblicz(HttpServletRequest request){
        try{
            String sA = request.getParameter("a");
            String sB = request.getParameter("b");
            if((sA == null) || (sA.trim().equals("")) || (sB == null) || (sB.trim().equals(""))){
                return "Brak parametru";
            }
            Float a=Float.valueOf(sA);
            Float b=Float.valueOf(sB);
            String action=request.getParameter("action");
            switch(action){
                case "+":{
                    return a + " + " + b + " = " + (a+b);
                }
                case "-":{
                    return a + " - " + b + " = " + (a-b);
                }
                case "/":{
                    if(b==0){
                        return "Nie dziel przez 0!";
                    }
                    return a + " / " + b + " = " + (a/b);

                }
                case "*":{
                    return a + " * " + b + " = " + (a*b);
                }
                default:
                    return "Oops! Cusko nie halo :c";
            }
        } catch(NumberFormatException e){
            return "Błąd formatu liczby!";
        }
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
    }// </editor-fold>

}
