package forward.javaqna.domain.answer.command;

import forward.javaqna.domain.answer.command.dto.AnswerCreateRequest;
import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.answer.core.AnswerRepository;
import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.member.core.MemberRepository;
import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class AnswerCommandServiceTest {

    @Autowired AnswerCommandService answerCommandService;
    @Autowired AnswerRepository answerRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired QuestionRepository questionRepository;

    Member member1;
    Question question1;

    @BeforeEach
    void setUp() {
        member1 = new Member("user1", "pass1", "User One");
        memberRepository.save(member1);

        question1 = questionRepository.save(
                Question.builder()
                        .title("테스트 질문")
                        .content("테스트 질문 내용")
                        .member(member1)
                        .build()
        );
    }

    @Test
    @DisplayName("답변 생성 테스트")
    void t1() {
        // given
        AnswerCreateRequest request = new AnswerCreateRequest();
        request.setContent("테스트 답변입니다.");
        request.setQuestionId(question1.getId());

        // when
        int answerId = answerCommandService.createAnswer(member1.getUsername(), request);

        // then
        Answer savedAnswer = answerRepository.findById(answerId).orElseThrow();
        assertThat(savedAnswer.getContent()).isEqualTo("테스트 답변입니다.");
        assertThat(savedAnswer.getMember().getUsername()).isEqualTo(member1.getUsername());
        assertThat(savedAnswer.getQuestion().getId()).isEqualTo(question1.getId());

        // 양방향 매핑 확인
        assertThat(question1.getAnswerList()).contains(savedAnswer);
    }
}