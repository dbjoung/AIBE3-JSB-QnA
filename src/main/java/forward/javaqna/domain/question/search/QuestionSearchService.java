package forward.javaqna.domain.question.search;

import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import forward.javaqna.domain.question.search.dto.SearchFormDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionSearchService {
    private final QuestionRepository questionRepository;

    public Page<Question> searchList(@Valid SearchFormDTO searchFormDTO, Pageable pageable) {
        String kwType = searchFormDTO.getKwType();
        String keyword = searchFormDTO.getKeyword();

        return switch(kwType) {
            case "title"-> questionRepository.findByTitleContainingIgnoreCase(keyword, pageable);
            case "content"-> questionRepository.findByContentContainingIgnoreCase(keyword, pageable);
            default -> questionRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword, pageable);
        };
    }
}
