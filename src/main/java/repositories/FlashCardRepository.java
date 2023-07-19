package repositories;

import models.FlashCard;
import models.FlashCardSet;

import java.util.List;

public interface FlashCardRepository {
    List<FlashCardSet> findAllCardSet();
    List<FlashCard> findAllCards();
    List<FlashCard> findAllFlashCardBySetId(Long id);

    void setFlashCardIsLearned(long flashCardId);
    FlashCard findFlashCardById(long flashCardId);

    void updateCountsOfFlashCardSet(Long flashCardSetId, int countOfCard, int countOfLearnedCard);

    void createFlashCardSet(String title);

    void createFlashCard(String title,  String question, String answer, Long idOfFlashCardSet);
}
