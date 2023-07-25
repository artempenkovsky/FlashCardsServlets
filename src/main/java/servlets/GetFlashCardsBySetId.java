package servlets;

import models.FlashCard;
import services.impl.FlashCardServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/getflashcards")
public class GetFlashCardsBySetId extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        FlashCardServiceImpl flashCardService = (FlashCardServiceImpl) servletContext.getAttribute("flashCardService");
        Long flashCardSetId = Long.valueOf(req.getParameter("flashCardSetId"));
        List<FlashCard> cards = flashCardService.getAllFlashCardBySetId(flashCardSetId)
                .stream()
                .filter(flashCard -> !flashCard.isLearned())
                .collect(Collectors.toList());
        servletContext.setAttribute("cards", cards);
        servletContext.setAttribute("flashCardSetId",flashCardSetId);
        getServletContext().getRequestDispatcher("/answerflashcard.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flashCardSetId = Long.valueOf(req.getParameter("flashCardSetId"));
        String knowledge = req.getParameter("knowledge");
        Long flashCardId = Long.valueOf(req.getParameter("flashCardId"));
        ServletContext servletContext = getServletContext();
        FlashCardServiceImpl flashCardService = (FlashCardServiceImpl) servletContext
                .getAttribute("flashCardService");
        if (knowledge.equals("YES")){
            flashCardService.setFlashCardIsLearned(flashCardId);
            flashCardService.updateNumberOfCard(flashCardSetId);
        }
        List<FlashCard> cards = flashCardService.getAllFlashCardBySetId(flashCardSetId)
                .stream()
                .filter(flashCard -> !flashCard.isLearned())
                .collect(Collectors.toList());
        servletContext.setAttribute("cards", cards);
        doGet(req,resp);
    }
}
