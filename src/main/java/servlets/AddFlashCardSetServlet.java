package servlets;

import models.FlashCardSet;
import services.impl.FlashCardServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/add/cardSet")
public class AddFlashCardSetServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        ServletContext servletContext = getServletContext();
        FlashCardServiceImpl flashCardService = (FlashCardServiceImpl) servletContext.getAttribute("flashCardService");
        flashCardService.createFlashCardSet(title);
        List<FlashCardSet> sets = flashCardService.getAllCardSet();
        req.setAttribute("sets", sets);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/flashcardset.jsp");
        requestDispatcher.forward(req, resp);
    }
}
