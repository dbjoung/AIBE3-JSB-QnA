package forward.javaqna.domain.question.core;

import forward.javaqna.domain.question.query.DTO.QuestionListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // EntityGraph를 사용해서 member 정보를 같이 가져오기
    // Page를 사용하고 있어 fetch join과 함께 사용시 오류 발생 위험
    @EntityGraph(attributePaths = {"member"})
    Page<Question> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"member", "answerList"})
    Optional<Question> findById(Integer id);
}
