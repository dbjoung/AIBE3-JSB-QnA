package forward.javaqna.domain.question.Search;

import forward.javaqna.domain.member.join.MemberJoinService;
import forward.javaqna.domain.member.join.dto.SignUpFormDTO;
import forward.javaqna.domain.question.command.QuestionCommandService;
import forward.javaqna.domain.question.command.dto.QuestionWriteDto;
import forward.javaqna.domain.question.query.DTO.QuestionListDTO;
import forward.javaqna.domain.question.query.DTO.SearchFormDTO;
import forward.javaqna.domain.question.query.QuestionQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@Rollback
public class QuestionSearchServiceTests {
    @Autowired
    QuestionCommandService  questionCommandService;

    @Autowired
    QuestionQueryService questionQueryService;

    @Autowired
    MemberJoinService memberJoinService;

    @BeforeEach
    public void setup() {
        memberJoinService.addMember(new SignUpFormDTO("asd", "1234", "1234", "asd"));
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목1pick", "검색내용1"), "asd");
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목2", "검색내용2"), "asd");
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목3", "검색내용3pick"), "asd");
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목4", "검색내용4"), "asd");
    }

    @Test
    @DisplayName("검색 테스트 - 제목 검색")
    void t1() {
        Pageable pageable = PageRequest.of(1, 10);
        Page<QuestionListDTO> list = questionQueryService.getQuestionPaging(new SearchFormDTO("title", "제목"), pageable);

        assertThat(list.getContent().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("검색 테스트 - 내용 검색")
    void t2() {
        Pageable pageable = PageRequest.of(1, 10);
        Page<QuestionListDTO> list = questionQueryService.getQuestionPaging(new SearchFormDTO("content", "내용"), pageable);

        assertThat(list.getContent().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("검색 테스트 - ALL 검색")
    void t3() {
        Pageable pageable = PageRequest.of(1, 10);
        Page<QuestionListDTO> list = questionQueryService.getQuestionPaging(new SearchFormDTO("", "pick"), pageable);

        assertThat(list.getContent().size()).isEqualTo(2);
    }
}
