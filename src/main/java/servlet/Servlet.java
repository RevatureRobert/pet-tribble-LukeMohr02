package servlet;

import dao.GenericDAO;
import dao.LabDAO;
import dao.TribbleDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tribble")
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        GenericDAO labDAO = new LabDAO();
        GenericDAO tribbleDAO = new TribbleDAO();

        String url = request.getRequestURI().replaceFirst("./tribble/", "");

        switch(url) {
            case "create":
                // Need method to convert request to Tribble
                break;
            case "delete":
                // Need method to convert request to Tribble
                break;
            case "get":
                // Need method to convert request to Tribble
                break;
            case "update":
                // Need method to convert request to Tribble
                break;
            default:
                break;
        }
    }
}
