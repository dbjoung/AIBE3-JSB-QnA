package forward.javaqna.domain.question.core;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    default Question getQuestionById(Integer questionId) {
        return findById(questionId).orElseThrow(() -> new EntityNotFoundException("Cannot found question for: " + questionId));
    }
}
