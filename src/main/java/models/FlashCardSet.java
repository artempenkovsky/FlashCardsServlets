package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlashCardSet {
    private Long id;
    private String title;

    private int countOfCard;
    private int countOfLearnedCard;
    private List<FlashCard> flashCardList;


    public FlashCardSet(Long id, String title, int countOfCard, int countOfLearnedCard) {
        this.id = id;
        this.title = title;
        this.countOfCard = countOfCard;
        this.countOfLearnedCard = countOfLearnedCard;
    }
}
