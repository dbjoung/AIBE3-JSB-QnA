package forward.javaqna.domain.question.command;

import forward.javaqna.domain.answer.core.AnswerRepository;
import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.member.core.MemberRepository;
import forward.javaqna.domain.question.command.dto.QuestionModifyDto;
import forward.javaqna.domain.question.command.dto.QuestionWriteDto;
import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionCommandService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Integer writeQuestion(QuestionWriteDto questionWriteDto, String username) {
        Member writer = getMember(username);
        Question savedQuestion = questionRepository.save(questionWriteDto.toEntity(writer));
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
        question.modify(newTitle, newContent);
    }

    @Transactional
    public void delete(Integer questionId) {
        //TODO: Answer 엔티티 양방향 관계 논의 필요?
        answerRepository.deleteByQuestion_Id(questionId);
        questionRepository.deleteById(questionId);
    }

    private Member getMember(String username) {
        return memberRepository.findById(username)
                               .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));
    }
}
