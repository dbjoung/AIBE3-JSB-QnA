package forward.javaqna.domain.answer.core;

import forward.javaqna.domain.question.core.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    void deleteByQuestion(Question question);

    Page<Answer> findByQuestionId(Integer questionId, Pageable pageable);

    @Query("select a from Answer a join fetch a.member where a.id = :id")
    Optional<Answer> findByIdEager(@Param("id") Integer id);
}
