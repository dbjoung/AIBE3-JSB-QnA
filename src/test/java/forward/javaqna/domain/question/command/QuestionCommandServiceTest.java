package forward.javaqna.domain.question.command;

import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.question.command.dto.QuestionModifyDto;
import forward.javaqna.domain.question.command.dto.QuestionWriteDto;
import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import jakarta.persistence.EntityManager;
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
    @Autowired EntityManager em;

    @Test
    @DisplayName("질문 등록 DTO를 전달하면 질문 엔티티가 저장된다.")
    void writeQuestion() {
        //given
        QuestionWriteDto writeDto = new QuestionWriteDto("test title", "test content");

        //when
        Integer savedId = questionCommandService.writeQuestion(writeDto);
        em.flush();
        em.clear();

        //then
        Question question = questionRepository.findById(savedId).get();

        assertThat(question).isNotNull();
        assertThat(question.getTitle()).isEqualTo(writeDto.title());
        assertThat(question.getContent()).isEqualTo(writeDto.content());
    }

    @Test
    @DisplayName("해당 ID로 질문 수정 뷰에 보여줄 DTO를 반환한다.")
    void findByIdForModify() {
        //given
        Question savedQuestion = questionRepository.save(new Question("title", "content", null));
        int questionId = savedQuestion.getId();

        //when
        QuestionModifyDto result = questionCommandService.findByIdForModify(questionId);

        //then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(questionId);
        assertThat(result.title()).isEqualTo(savedQuestion.getTitle());
        assertThat(result.content()).isEqualTo(savedQuestion.getContent());
    }

    @Test
    @DisplayName("질문 수정 DTO를 넘겨 기존의 질문 엔티티를 수정한다.")
    void modify() {
        //given
        Question savedQuestion = questionRepository.save(new Question("title", "content", null));
        QuestionModifyDto modifyDto = new QuestionModifyDto(savedQuestion.getId(), "new title", "new content");

        //when
        questionCommandService.modify(modifyDto);
        em.flush();
        em.clear();

        //then
        Question question = questionRepository.findById(modifyDto.id()).get();
        assertThat(question).isNotNull();
        assertThat(question.getTitle()).isEqualTo(modifyDto.title());
        assertThat(question.getContent()).isEqualTo(modifyDto.content());
    }

    @Test
    @DisplayName("질문 삭제 시 관련된 답변들도 삭제된다.")
    void delete() {
        //given
        Question savedQuestion = questionRepository.save(new Question("title", "content", null));

        Answer a1 = new Answer();
        a1.setQuestion(savedQuestion);
        a1.setContent("answer1");
        em.persist(a1);

        Answer a2 = new Answer();
        a2.setQuestion(savedQuestion);
        a2.setContent("answer1");
        em.persist(a2);

        //when
        questionCommandService.delete(savedQuestion.getId());
        em.flush();
        em.clear();

        //then
        Question question = questionRepository.findById(savedQuestion.getId()).orElse(null);
        Answer deletedA1 = em.find(Answer.class, a1.getId());
        Answer deletedA2 = em.find(Answer.class, a2.getId());

        assertThat(question).isNull();
        assertThat(deletedA1).isNull();
        assertThat(deletedA2).isNull();
    }
}