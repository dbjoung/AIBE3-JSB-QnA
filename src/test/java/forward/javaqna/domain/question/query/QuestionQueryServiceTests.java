package forward.javaqna.domain.question.query;

import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import forward.javaqna.domain.question.query.DTO.QuestionDTO;
import forward.javaqna.domain.question.query.DTO.QuestionListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@ActiveProfiles("test")
@SpringBootTest
public class QuestionQueryServiceTests {
    @Autowired
    QuestionQueryService questionQueryService;
    @Autowired
    QuestionRepository questionRepository;

    @BeforeEach
    public void setup() { //Question 엔티티에 @Setter 어노테이션 추가 후 테스트
        questionRepository.deleteAll();
        Question question = new Question();
        question.setTitle("Test Question 1");
        question.setContent("Test Question 1");
        questionRepository.save(question);
        Question question1 = new Question();
        question1.setTitle("Test Question 2");
        question1.setContent("Test Question 2");
        questionRepository.save(question1);
    }

    @Test
    @DisplayName("질문 목록 조회")
    void t1() {
        List<QuestionListDTO> questionList = questionQueryService.getQuestionList();

        for(QuestionListDTO questionListDTO : questionList){
            System.out.println("질문 ID : " + questionListDTO.getId());
        }

        assertThat(questionList).hasSize(2);
    }

    @Test
    @DisplayName("질문 상세 조회")
    void t2() {
        QuestionDTO questionDTO = questionQueryService.getQuestionById(1);
        assertThat(questionDTO.getTitle()).isEqualTo("Test Question 1");
    }
}
