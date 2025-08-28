package forward.javaqna.domain.question.query;

import forward.javaqna.domain.question.core.QuestionRepository;
import forward.javaqna.domain.question.query.DTO.QuestionListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionQueryService {
    private final QuestionRepository questionRepository;

    public List<QuestionListDTO> getQuestionList() {
        return QuestionListDTO.fromEntity(questionRepository.findAll());
    }

}