package forward.javaqna.domain.question.command;

import forward.javaqna.domain.question.command.dto.QuestionWriteDto;
import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class QuestionCommandServiceTest {

    @Autowired QuestionCommandService questionCommandService;
    @Autowired QuestionRepository questionRepository;

    @Test
    @DisplayName("질문 등록 DTO를 전달하면 질문 엔티티가 저장된다.")
    void writeQuestion() {
        //given
        QuestionWriteDto writeDto = new QuestionWriteDto("test title", "test content");

        //when
        Integer savedId = questionCommandService.writeQuestion(writeDto);

        //then
        Question question = questionRepository.findById(savedId).get();

        assertThat(question).isNotNull();
        assertThat(question.getTitle()).isEqualTo(writeDto.title());
        assertThat(question.getContent()).isEqualTo(writeDto.content());
    }
}