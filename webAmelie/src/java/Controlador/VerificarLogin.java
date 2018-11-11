/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
@WebServlet(name = "VerificarLogin", urlPatterns = {"/VerificarLogin"})
public class VerificarLogin extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Iniciando Sesión</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Verificando Inicio de Sesión...</h1>");
            
            Conexion conexion = new Conexion(); // CREO OBJETO CONEXION PARA PODER LLAMAR EL METODO LISTAR

            Usuario userIngresado = new Usuario();
            userIngresado.setnombreUsuario(request.getParameter("NombreUsuario")); // SETEO LOS CAMPOS DESDE EL FORM AL OBJETO USUARIO
            userIngresado.setPassword(request.getParameter("Clave"));

            ArrayList<Usuario> credencialesBase = conexion.listar(); // RELLENO EL ARRAY DE USARIOS DESDE LA BASE DE DATOS A ESTE ARRALIST

                       
            out.print("<br>");
            boolean encontrado = false;
            
            for (Usuario user : credencialesBase) { // ITETERO EL ARRAYLIST SEGUN LA CANTIDAD DE OBJETOS EN LA LISTA
               
                if (user.getnombreUsuario().equals(userIngresado.getnombreUsuario()) && user.getPassword().equals(userIngresado.getPassword())) { // COMPARA EL OBJETO QUE VIENE DE LA LISTA Y LOS CAMPOS QUE VIENE DEL FORM
                    out.print("<h1>Redirigiendo...</h1>");
                    encontrado = true; // CAMBIO EL VALOR DE BOOLEANO PARA QUE NO PASE POR EL IF QUE INDICA QUE NO ESTA EL USUARIO EN LA BASE DE DATOS
                    response.sendRedirect("html/catalogo.jsp");
                    break;
                }
            }
            if (!encontrado){ // NEGACION DEL BOOLEANO PARA INDICAR QUE NO ESTA EL USUARIO INGRESADO EN LA BASE DE DATOS
                out.println("<h2>Usuario no existe, Porfavor Registrar para poder usar el Sitio Web</h2>");
                out.println("<br/><a href='html/signup.jsp'>Registrar</a>");
            }
            
        }catch (Exception e){
            System.out.println(e);
        }finally{
            out.println("</body>");
            out.println("</html>");
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