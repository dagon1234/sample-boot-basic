package th.mfu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/quizzes") // Base URL for the QuizController
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    // Get all quizzes
    @GetMapping
    public Collection<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // Get a quiz by ID
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        return quizRepository.findById(id)
                .map(quiz -> ResponseEntity.ok(quiz))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create a new quiz
    @PostMapping
    public ResponseEntity<String> createQuiz(@RequestBody Quiz quiz) {
        quizRepository.save(quiz);
        return ResponseEntity.ok("Quiz created");
    }

    // Update a quiz
    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuiz(@PathVariable Long id, @RequestBody Quiz updatedQuiz) {
        if (!quizRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
        }

        updatedQuiz.setId(id); // Ensure the ID in the request body matches the path ID
        quizRepository.save(updatedQuiz);
        return ResponseEntity.ok("Quiz updated");
    }

    // Delete a quiz
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id) {
        if (!quizRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
        }

        quizRepository.deleteById(id);
        return ResponseEntity.ok("Quiz deleted");
    }
}
