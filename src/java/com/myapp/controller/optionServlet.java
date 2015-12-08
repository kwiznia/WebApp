/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.controller;

import com.myapp.dao.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "optionServlet", urlPatterns = {"/optionServlet"})
public class optionServlet extends HttpServlet {

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
        //************Error messages to pass it to the JSP****************//
        String messageCal = "No has introducido calorías";
        String messageTimeMin = "No has introducido mínimo de tiempo";
        String messageTimeMax = "No has introducido máximo de tiempo";

        //*************Start session*************************//
        HttpSession session = request.getSession();
        List lIngredients = (List) session.getAttribute("Ingredientes");

        response.setContentType("text/html;charset=UTF-8");
        
        //****************Create a list with the ingredients the user introduce*************//
        if (lIngredients == null) {
            lIngredients = new ArrayList();
            session.setAttribute("Ingredientes", lIngredients);
        }
        //********get parameters from the interface********************//
        String ingrediente = request.getParameter("Ingredientes");
        String cal = request.getParameter("Calorias");
        String tiempoMaximo = request.getParameter("TiempoMax");
        String tiempoMinimo = request.getParameter("TiempoMin");

        String action = request.getParameter("action"); //elegimos a qué pantalla pasar en función de la acción que nos llegue de la interfaz

        //****************Find all receipes********************//
        if ("Buscar todas las recetas".equalsIgnoreCase(action)) {
            request.setAttribute("AllReceipes", AllRecipesDao.getAllReceipes());
            request.getRequestDispatcher("receipes.jsp").forward(request, response);

        //*************Find recipes per ingredients**********//
        } else if ("Buscar por ingredientes".equalsIgnoreCase(action)) {
            lIngredients.add(ingrediente);
            if (ingrediente.isEmpty()) {
                lIngredients.remove(ingrediente);
            }
            request.setAttribute("AllIngredients", SomeRecipesDao.getSomeReceipes(lIngredients));
            request.getRequestDispatcher("perIngredient.jsp").forward(request, response);

            //***********The user want to add more than one ingredient***********//
        } else if ("Agregar ingrediente".equalsIgnoreCase(action)) {
            if (!lIngredients.contains(ingrediente)) {
                lIngredients.add(ingrediente);
            }
            if (ingrediente.isEmpty()) {
                lIngredients.remove(ingrediente);
            }
            request.getSession().setAttribute("message", lIngredients);
            response.sendRedirect("option.jsp");
            ingrediente = request.getParameter("Ingredientes");
            action = request.getParameter("action");

            //***********Get recipes with a maximum of calories*********//
        } else if ("Buscar por calorias maximas".equalsIgnoreCase(action)) {
            if (!cal.isEmpty()) {
                request.setAttribute("AllIngredients", RecipesCaloriesDao.getRecipesCalories(cal));
                request.getRequestDispatcher("perIngredient.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("messageNok", messageCal);
                response.sendRedirect("option.jsp");
            }

            //*****************Find recipes order by preparation time***************//
        } else if ("Buscar por tiempo de preparacion".equalsIgnoreCase(action)) {
            request.setAttribute("AllReceipes", RecipesTimeDao.getReceipesTime());
            request.getRequestDispatcher("receipes.jsp").forward(request, response);

            //*********Find recipes with a maximum preparation time*********//
        } else if ("Buscar por tiempo maximo de preparacion".equalsIgnoreCase(action)) {
            if (!tiempoMaximo.isEmpty()) {
                request.setAttribute("AllReceipes", RecipesMaxTimeDao.getReceipesMaxTime(tiempoMaximo));
                request.getRequestDispatcher("receipes.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("messageMax", messageTimeMax);
                response.sendRedirect("option.jsp");
            }
            //*********Find recipes with a minimum preparation time*********//
        } else if ("Buscar por tiempo minimo de preparacion".equalsIgnoreCase(action)) {
            if (!tiempoMinimo.isEmpty()) {
                request.setAttribute("AllReceipes", RecipesMinTimeDao.getReceipesMinTime(tiempoMinimo));
                request.getRequestDispatcher("receipes.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("messageMin", messageTimeMin);
                response.sendRedirect("option.jsp");
            }
             //*********Find recipes with a enclosed preparation time*********//
        } else if ("Buscar por tiempo acotado de preparacion".equalsIgnoreCase(action)) {
            if (!tiempoMinimo.isEmpty() && !tiempoMaximo.isEmpty()) {
                request.setAttribute("AllReceipes", RecipesBetweenMinMaxDao.getReceipesBetweenMinMax(tiempoMinimo, tiempoMaximo));
                request.getRequestDispatcher("receipes.jsp").forward(request, response);
            } else if (tiempoMinimo.isEmpty() && tiempoMaximo.isEmpty()) {
                request.getSession().setAttribute("messageMinEnclosed", messageTimeMin);
                request.getSession().setAttribute("messageMaxEnclosed", messageTimeMax);
                response.sendRedirect("option.jsp");
            } else {
                request.getSession().setAttribute("messageMaxEnclosed", messageTimeMax);
                response.sendRedirect("option.jsp");
            }
            {
            }
            //*********Find recipes per difficulty*********//
        } else if ("Buscar recetas por dificultad".equalsIgnoreCase(action)) {
            request.setAttribute("AllReceipes", RecipesDifficultyDao.getReceipesDifficulty());
            request.getRequestDispatcher("receipes.jsp").forward(request, response);
            
            //*********Find recipes with low and medium difficulty*********//
        } else if ("Buscar recetas de dificultad baja y media".equalsIgnoreCase(action)) {
            request.setAttribute("AllReceipes", RecipesEasyMedDifDao.getReceipesEasyMediumDif());
            request.getRequestDispatcher("receipes.jsp").forward(request, response);
            
            //*********Find recipes with medium and high difficulty*********//
        } else if ("Buscar recetas de dificultad media y alta".equalsIgnoreCase(action)) {
            request.setAttribute("AllReceipes", RecipesMedHardDifDao.getReceipesMediumHardDif());
            request.getRequestDispatcher("receipes.jsp").forward(request, response);
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
