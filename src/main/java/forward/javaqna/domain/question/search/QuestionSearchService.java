package forward.javaqna.domain.question.search;

import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionSearchService {
    private final QuestionRepository questionRepository;


    public List<Question> questionSearch(@NotBlank String kwType, @NotBlank String keyword) {
        return switch (kwType) {
            case "title" -> {
                //return questionRepository.fin
            }
            case "content" -> {
            }
            case "all" -> {

            }
            default -> throw new IllegalArgumentException("올바른 키 타입이 넘어오지 않았습니다.");
        }
    }
}
