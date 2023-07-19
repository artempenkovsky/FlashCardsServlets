package repositories.impl;


import models.FlashCard;
import models.FlashCardSet;
import repositories.FlashCardRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlashCardRepositoryImpl implements FlashCardRepository {
    private final DataSource myHikariDataSource;

    public FlashCardRepositoryImpl(DataSource myHikariDataSource) {
        this.myHikariDataSource = myHikariDataSource;
    }

    @Override
    public List<FlashCardSet> findAllCardSet() {
        List<FlashCardSet> flashCardSets = new ArrayList<>();
        try {
            Connection connection = myHikariDataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.flashcard_set");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                Integer countOfCard = resultSet.getInt("countOfCard");
                Integer countOfLearnedCard = resultSet.getInt("countOfLearnedCard");
                FlashCardSet flashCardSet = new FlashCardSet(id, title, countOfCard, countOfLearnedCard);
                //  FlashCardSet flashCardSet = new FlashCardSet(id, title, countOfCard, countOfLearnedCard);
                flashCardSets.add(flashCardSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flashCardSets;
    }

    @Override
    public List<FlashCard> findAllCards() {
        List<FlashCard> flashCardList = new ArrayList<>();
        try {
            Connection connection = myHikariDataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.flashcard");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String titleCard = resultSet.getString("title_cards");
                String question = resultSet.getString("question");
                String answer = resultSet.getString("answer");
                Boolean active = resultSet.getBoolean("learned");
                Long poolCardId = resultSet.getLong("set_id");
                FlashCard flashcard = new FlashCard(id, titleCard, question, answer, active, poolCardId);
                flashCardList.add(flashcard);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flashCardList;
    }

    @Override
    public List<FlashCard> findAllFlashCardBySetId(Long idSet) {
        List<FlashCard> flashCardList = new ArrayList<>();
        try (Connection connection = myHikariDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.flashcard WHERE set_id=?;");
            statement.setLong(1, idSet);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String titleCard = resultSet.getString("title_cards");
                String question = resultSet.getString("question");
                String answer = resultSet.getString("answer");
                Boolean active = resultSet.getBoolean("learned");
                Long poolCardId = resultSet.getLong("set_id");
                FlashCard flashcard = new FlashCard(id, titleCard, question, answer, active, poolCardId);
                flashCardList.add(flashcard);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flashCardList;
    }

    @Override
    public void setFlashCardIsLearned(long flashCardId) {
        try (Connection connection = myHikariDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.flashcard SET learned=true WHERE id=?");
            statement.setLong(1, flashCardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCountsOfFlashCardSet(Long flashCardSetId, int countOfCard, int countOfLearnedCard) {
        try (Connection connection = myHikariDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.flashcard_set SET  \"countOfCard\"=?, \"countOfLearnedCard\"=? WHERE id=? ;");
            statement.setInt(1, countOfCard);
            statement.setInt(2, countOfLearnedCard);
            statement.setLong(3, flashCardSetId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createFlashCardSet(String title) {
        try (Connection connection = myHikariDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.flashcard_set(title, \"countOfCard\", \"countOfLearnedCard\") VALUES ( ?, ?, ?);");
            statement.setString(1, title);
            statement.setInt(2, 0);
            statement.setInt(3, 0);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createFlashCard(String title, String question, String answer, Long idOfFlashCardSet) {
        try (Connection connection = myHikariDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO public.flashcard(title_cards, question, answer, learned, set_id) VALUES (?, ?, ?, ?, ?);");
            statement.setString(1, title);
            statement.setString(2, question);
            statement.setString(3, answer);
            statement.setBoolean(4, false);
            statement.setLong(5, idOfFlashCardSet);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}



