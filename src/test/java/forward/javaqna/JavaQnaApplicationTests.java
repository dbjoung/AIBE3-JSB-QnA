package forward.javaqna;

import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class JavaQnaApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("테스트 동작")
    void t1() {
        questionRepository.save(new Question("test1", "내용1"));
    }
}
