package th.mfu;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    // You can define custom query methods here if needed
    // For example, finding quizzes by question text or any other criteria

    List<Quiz> findByQuestionContaining(String keyword);

}
