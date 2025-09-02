package forward.javaqna.domain.question.query;

import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import forward.javaqna.domain.question.query.DTO.QuestionDTO;
import forward.javaqna.domain.question.query.DTO.QuestionListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionQueryService {
    private final QuestionRepository questionRepository;

    //질문 목록 페이징 처리
    @Transactional(readOnly = true)
    public Page<QuestionListDTO> getQuestionPaging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; //페이지 번호
        int size = 5; //페이지 당 질문 갯수

        //내림차순 정렬(최신 글 먼저 불러오기)
        Page<Question> questionPage = questionRepository.findAllWithMember(PageRequest.of(page, size, Sort.by("id").descending()));

        return questionPage.map(QuestionListDTO::fromEntity);
    }

    //질문 단건 조회
    @Transactional(readOnly = true)
    public QuestionDTO getQuestionById(int id) {
        return QuestionDTO.fromQuestion(questionRepository.findWithMemberAndAnswers(id)
                .orElseThrow(() -> new IllegalArgumentException("Question not found")));
    }

}