package forward.javaqna.domain.question.command;

import forward.javaqna.domain.answer.core.AnswerRepository;
import forward.javaqna.domain.question.command.dto.QuestionModifyDto;
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
    private final AnswerRepository answerRepository;

    @Transactional
    public Integer writeQuestion(QuestionWriteDto questionWriteDto) {
        //TODO: 작성자 조회 후 toEntity에 전달
        Question savedQuestion = questionRepository.save(questionWriteDto.toEntity());
        return savedQuestion.getId();
    }

    public QuestionModifyDto findByIdForModify(Integer questionId) {
        Question question = questionRepository.getQuestionById(questionId);
        return QuestionModifyDto.from(question);
    }

    @Transactional
    public void modify(QuestionModifyDto questionEditDto) {
        Integer id = questionEditDto.id();
        String newTitle = questionEditDto.title();
        String newContent = questionEditDto.content();

        Question question = questionRepository.getQuestionById(id);
        question.edit(newTitle, newContent);
    }

    @Transactional
    public void delete(Integer questionId) {
        //TODO: Answer 엔티티 양방향 관계 논의 필요?
        answerRepository.deleteByQuestion_Id(questionId);
        questionRepository.deleteById(questionId);
    }
}
