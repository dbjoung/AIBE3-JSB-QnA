package forward.javaqna.domain.question.core;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // EntityGraph를 사용해서 member 정보를 같이 가져오기
    // Page를 사용하고 있어 fetch join과 함께 사용시 오류 발생 위험
    @EntityGraph(attributePaths = {"member"})
    @Query("SELECT q from Question q")
    Page<Question> findAllWithMember(Pageable pageable);

    @EntityGraph(attributePaths = {"member", "answerList"})
    @Query("SELECT q FROM Question q WHERE q.id = :id")
    Optional<Question> findWithMemberAndAnswers(Integer id);
  
    default Question getQuestionById(Integer questionId) {
        return findById(questionId).orElseThrow(() -> new EntityNotFoundException("Cannot found question for: " + questionId));
    }

    @EntityGraph(attributePaths = {"member"})
    Page<Question> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    @EntityGraph(attributePaths = {"member"})
    Page<Question> findByContentContainingIgnoreCase(String content, Pageable pageable);
    @EntityGraph(attributePaths = {"member"})
    Page<Question> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content, Pageable pageable);
}
