package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FlashCard {
    private Long id;
    private String tittleFlashCard;
    private String question;
    private String answer;
    private boolean learned;
    private Long setID;
}
