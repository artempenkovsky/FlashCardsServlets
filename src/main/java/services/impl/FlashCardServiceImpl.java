package services.impl;


import models.FlashCard;
import models.FlashCardSet;
import repositories.FlashCardRepository;
import repositories.impl.FlashCardRepositoryImpl;
import services.FlashCardService;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

public class FlashCardServiceImpl implements FlashCardService {
    private DataSource dataSource;
    private FlashCardRepository flashCardRepository = new FlashCardRepositoryImpl(dataSource);

    public FlashCardServiceImpl(FlashCardRepository flashCardRepository) {
        this.flashCardRepository = flashCardRepository;
    }

    @Override
    public List<FlashCard> getAllFlashCardBySetId(Long setId) {
        return flashCardRepository.findAllFlashCardBySetId(setId);
    }

    @Override
    public List<FlashCardSet> getAllCardSet() {
        List<FlashCardSet> flashCardSets = flashCardRepository.findAllCardSet();
        return flashCardSets;
    }

    @Override
    public List<FlashCard> getAllCards() {
        List<FlashCard> flashCardList = flashCardRepository.findAllCards();
        return flashCardList;
    }

    @Override
    public void setFlashCardIsLearned(long flashCardId) {
        flashCardRepository.setFlashCardIsLearned(flashCardId);
    }

    @Override
    public void updateNumberOfCard(Long flashCardSetId) {
        List<FlashCard> cards = flashCardRepository.findAllCards()
                .stream()
                .filter(flashCard -> flashCard.getSetID().equals(flashCardSetId))
                .collect(Collectors.toList());
        int countOfCard=cards.size();
        int countOfLearnedCard= cards
                .stream()
                .filter(flashCard -> flashCard.isLearned())
                .collect(Collectors.toList())
                .size();
        flashCardRepository.updateCountsOfFlashCardSet(flashCardSetId,countOfCard,countOfLearnedCard);


    }

    @Override
    public void createFlashCardSet(String title) {
        flashCardRepository.createFlashCardSet(title);
    }

    @Override
    public void createFlashCard(String title,String question, String answer, Long idOfFlashCardSet) {
        flashCardRepository.createFlashCard(title, question,answer,idOfFlashCardSet);
        updateNumberOfCard(idOfFlashCardSet);
    }
}

