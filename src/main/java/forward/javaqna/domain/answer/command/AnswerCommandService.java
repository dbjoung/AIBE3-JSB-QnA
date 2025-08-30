package forward.javaqna.domain.answer.command;

import forward.javaqna.domain.answer.command.dto.AnswerRequestDto;
import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.answer.core.AnswerRepository;
import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.member.core.MemberRepository;
import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerCommandService {

    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    public Integer createAnswer(String username, Integer questionId, AnswerRequestDto answerRequestDto) {
        Member member = getMember(username);
        Question question = getQuestion(questionId);

        Answer answer = answerRequestDto.toEntity(member, question);
        answerRepository.save(answer);

        return answer.getId();
    }

    public void modifyAnswer(String username, Integer answerId, AnswerRequestDto answerRequestDto) {
        Member member = getMember(username);
        Answer answer = getAnswer(answerId);
        if (answer.getMember() != member) throw new IllegalStateException("작성자가 아니므로 수정할 수 없습니다.");

        String newContent = answerRequestDto.getContent();
        answer.edit(newContent);
    }

    private Question getQuestion(Integer questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("질문이 존재하지 않습니다."));
    }

    private Member getMember(String username) {
        return memberRepository.findById(username)
                .orElseThrow(() -> new EntityNotFoundException("회원이 존재하지 않습니다."));
    }

    private Answer getAnswer(Integer answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new EntityNotFoundException("답변이 존재하지 않습니다."));
    }
}
