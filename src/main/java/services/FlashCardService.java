package services;

import models.FlashCard;
import models.FlashCardSet;

import java.util.List;

public interface FlashCardService {
    List<FlashCard> getAllFlashCardBySetId(Long setId);
    List<FlashCardSet> getAllCardSet();
    List<FlashCard> getAllCards();
    void setFlashCardIsLearned(long flashCardId);

    void updateNumberOfCard(Long flashCardSetId);

    void createFlashCardSet(String title);

    void createFlashCard(String title,  String question, String answer, Long idOfFlashCardSet);
}
