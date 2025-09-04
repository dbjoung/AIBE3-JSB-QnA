package forward.javaqna.domain.question.query;

import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.member.core.MemberRepository;
import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import forward.javaqna.domain.question.query.DTO.QuestionDTO;
import forward.javaqna.domain.question.query.DTO.QuestionListDTO;
import forward.javaqna.domain.question.query.DTO.SearchFormDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@ActiveProfiles("test")
@SpringBootTest
public class QuestionQueryServiceTests {
    @Autowired
    QuestionQueryService questionQueryService;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    private MemberRepository memberRepository;

    int testId;

    @BeforeEach
    public void setup() { //Question 엔티티에 @Setter 어노테이션 추가 후 테스트
        memberRepository.save(new Member("forward", "1234", "dasol"));

        Question question1 = new Question("Test Question 1", "Test Question 1", memberRepository.getReferenceById("forward"));
        Question a = questionRepository.save(question1);
        testId = a.getId();

        Question question2 = new Question("Test Question 2", "Test Question 2", memberRepository.getReferenceById("forward"));
        questionRepository.save(question2);
    }

    @Test
    @DisplayName("질문 목록 조회")
    void t1() {
        Pageable pageable = PageRequest.of(1, 5);
        Page<QuestionListDTO> questionList = questionQueryService.getQuestionPaging(new SearchFormDTO("", ""), pageable);

        questionList.getContent().forEach(q ->
                System.out.println("질문 ID : " + q.getId())
        );

        assertThat(questionList.getContent()).hasSize(2);
    }

    @Test
    @DisplayName("질문 상세 조회")
    void t2() {
        QuestionDTO questionDTO;
        questionDTO = questionQueryService.getQuestionById(testId);
        assertThat(questionDTO.getTitle()).isEqualTo("Test Question 1");
        assertThat(questionDTO.getMember().getUsername()).isEqualTo("forward");
    }
}
