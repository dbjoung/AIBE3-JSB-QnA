package forward.javaqna.domain.question.command;

import forward.javaqna.domain.question.command.dto.QuestionWriteDto;
import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionCommandService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Integer writeQuestion(QuestionWriteDto questionWriteDto) {
        //TODO: 작성자 조회 후 toEntity에 전달
        Question savedQuestion = questionRepository.save(questionWriteDto.toEntity());
        return savedQuestion.getId();
    }
}
