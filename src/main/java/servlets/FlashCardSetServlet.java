package servlets;

import models.FlashCardSet;
import services.impl.FlashCardServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class FlashCardSetServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        FlashCardServiceImpl flashCardService = (FlashCardServiceImpl) servletContext.getAttribute("flashCardService");
        List<FlashCardSet> sets = flashCardService.getAllCardSet();
        req.setAttribute("sets", sets);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/flashcardset.jsp");
        requestDispatcher.forward(req, resp);
    }
}
