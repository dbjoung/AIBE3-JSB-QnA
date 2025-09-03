package forward.javaqna.domain.question.Search;

import forward.javaqna.domain.question.command.QuestionCommandService;
import forward.javaqna.domain.question.command.dto.QuestionWriteDto;
import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.search.QuestionSearchService;
import forward.javaqna.domain.question.search.dto.SearchFormDTO;
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
    QuestionSearchService questionSearchService;

    @Test
    @DisplayName("검색 테스트 - 제목 검색")
    void t1() {
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목1", "검색내용1"));
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목2", "검색내용2"));
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목3", "검색내용3"));
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목4", "검색내용4"));

        Pageable pageable = PageRequest.of(0, 10);
        Page<Question> list = questionSearchService.searchList(new SearchFormDTO("title", "검색"), pageable);

        assertThat(list.getContent().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("검색 테스트 - 내용 검색")
    void t2() {
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목1", "검색내용1"));
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목2", "검색내용2"));
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목3", "검색내용3"));
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목4", "검색내용4"));

        Pageable pageable = PageRequest.of(0, 10);
        Page<Question> list = questionSearchService.searchList(new SearchFormDTO("content", "내용"), pageable);

        assertThat(list.getContent().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("검색 테스트 - ALL 검색")
    void t3() {
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목1pick", "검색내용1"));
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목2", "검색내용2"));
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목3", "검색내용3pick"));
        questionCommandService.writeQuestion(new QuestionWriteDto("검색제목4", "검색내용4"));

        Pageable pageable = PageRequest.of(0, 10);
        Page<Question> list = questionSearchService.searchList(new SearchFormDTO("", "pick"), pageable);

        assertThat(list.getContent().size()).isEqualTo(2);
    }
}
