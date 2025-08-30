package forward.javaqna.domain.answer.command;

import forward.javaqna.domain.answer.command.dto.AnswerRequestDto;
import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.answer.core.AnswerRepository;
import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class AnswerCommandServiceTest {

    @Autowired AnswerCommandService answerCommandService;
    @Autowired AnswerRepository answerRepository;
    @Autowired QuestionRepository questionRepository;
    @Autowired EntityManager em;

    Member member1;
    Question question1;

    @BeforeEach
    void setUp() {
        member1 = new Member("user1", "pass1", "User One");
        em.persist(member1);
        question1 = new Question("테스트 질문", "테스트 질문 내용", member1);
        em.persist(question1);
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("답변 생성 테스트")
    void t1() {

        // given
        AnswerRequestDto request = new AnswerRequestDto();
        request.setContent("테스트 답변입니다.");

        // when
        Integer answerId = answerCommandService.createAnswer(member1.getUsername(), question1.getId(), request);
        em.flush();
        em.clear();

        // then
        Answer savedAnswer = answerRepository.findById(answerId).orElseThrow();
        assertThat(savedAnswer.getContent()).isEqualTo("테스트 답변입니다.");
        assertThat(savedAnswer.getMember().getUsername()).isEqualTo(member1.getUsername());
        assertThat(savedAnswer.getQuestion().getId()).isEqualTo(question1.getId());

        // 양방향 매핑 확인
        Question findQuestion = questionRepository.findById(question1.getId()).get();
        assertThat(findQuestion.getAnswerList()).contains(savedAnswer);
    }

    @Test
    @DisplayName("답변 수정 성공 테스트")
    void t2() {
        // given
        AnswerRequestDto createRequest = new AnswerRequestDto();
        createRequest.setContent("원본 답변");
        Integer answerId = answerCommandService.createAnswer(member1.getUsername(), question1.getId(), createRequest);
        em.flush();
        em.clear();

        AnswerRequestDto modifyRequest = new AnswerRequestDto();
        modifyRequest.setContent("수정된 답변");

        // when
        answerCommandService.modifyAnswer(member1.getUsername(), answerId, modifyRequest);
        em.flush();
        em.clear();

        // then
        Answer modifiedAnswer = answerRepository.findById(answerId).orElseThrow();
        assertThat(modifiedAnswer.getContent()).isEqualTo("수정된 답변");
    }

    @Test
    @DisplayName("작성자가 아닌 경우 답변 수정 실패")
    void t3() {
        // given
        AnswerRequestDto createRequest = new AnswerRequestDto();
        createRequest.setContent("원본 답변");
        Integer answerId = answerCommandService.createAnswer(member1.getUsername(), question1.getId(), createRequest);
        em.flush();
        em.clear();

        Member member2 = new Member("user2", "pass2", "User Two");
        em.persist(member2);
        em.flush();
        em.clear();

        AnswerRequestDto modifyRequest = new AnswerRequestDto();
        modifyRequest.setContent("수정된 답변");

        // then
        assertThrows(
                IllegalStateException.class,
                () -> answerCommandService.modifyAnswer(member2.getUsername(), answerId, modifyRequest)
        );
    }
}