package forward.javaqna.domain.answer.command;

import forward.javaqna.domain.answer.command.dto.AnswerCreateRequest;
import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.answer.core.AnswerRepository;
import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.member.core.MemberRepository;
import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
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

    public int createAnswer(String username, AnswerCreateRequest answerCreateRequest) {
        Member member = memberRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("회원 없음"));

        Question question = questionRepository.findById(answerCreateRequest.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("질문 없음"));

        Answer answer = answerCreateRequest.toEntity(member, question);
        answerRepository.save(answer);

        return answer.getId();
    }
}
