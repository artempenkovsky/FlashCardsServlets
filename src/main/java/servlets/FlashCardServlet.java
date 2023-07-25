package servlets;

import models.FlashCard;
import services.impl.FlashCardServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/flashcard")
public class FlashCardServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        FlashCardServiceImpl flashCardService = (FlashCardServiceImpl) servletContext.getAttribute("flashCardService");
        List<FlashCard> cards = flashCardService.getAllCards();
        servletContext.setAttribute("cards", cards);
        getServletContext().getRequestDispatcher("/flashcard.jsp").forward(req, resp);
    }
}
