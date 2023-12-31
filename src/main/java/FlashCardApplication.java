import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import repositories.FlashCardRepository;
import repositories.impl.FlashCardRepositoryImpl;
import services.FlashCardService;
import services.impl.FlashCardServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FlashCardApplication implements ServletContextListener {
    public static final String DEPENDENCY_DATA_SOURCE = "dataSource";
    public static final String DEPENDENCY_FLASHCARD_SERVICE = "flashCardService";
    @Override
    public void contextInitialized(ServletContextEvent event) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(System.getenv("URL"));
        hikariConfig.setUsername(System.getenv("USER"));
        hikariConfig.setPassword(System.getenv("PASSWORD"));
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        //JdbcDatabase db = new JdbcDatabase(dataSource);

        //FlashcardDeckRepository deckRepo = new FlashcardDeckJdbcRepository(db);
        FlashCardRepository flashCardRepository = new FlashCardRepositoryImpl(dataSource);

        //FlashcardDeckService deckService = new FlashcardDeckServiceImpl(deckRepo);
        FlashCardService flashCardService = new FlashCardServiceImpl(flashCardRepository);
        //TrainingService trainingService = new TrainingServiceImpl(deckRepo, cardRepo);

        ServletContext context = event.getServletContext();
        context.setAttribute(DEPENDENCY_DATA_SOURCE, dataSource);
        //context.setAttribute(DEPENDENCY_FLASHCARD_DECK_SERVICE, deckService);
        context.setAttribute(DEPENDENCY_FLASHCARD_SERVICE, flashCardService);
        //context.setAttribute(DEPENDENCY_TRAINING_SERVICE, trainingService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
