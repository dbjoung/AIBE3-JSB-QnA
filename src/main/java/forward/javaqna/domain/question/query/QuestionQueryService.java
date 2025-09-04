package forward.javaqna.domain.question.query;

import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import forward.javaqna.domain.question.query.DTO.QuestionDTO;
import forward.javaqna.domain.question.query.DTO.QuestionListDTO;
import forward.javaqna.domain.question.query.DTO.SearchFormDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionQueryService {
    private final QuestionRepository questionRepository;

    //질문 목록 페이징 처리
    @Transactional(readOnly = true)
    public Page<QuestionListDTO> getQuestionPaging(SearchFormDTO searchFormDTO, Pageable pageable) {
        int page = pageable.getPageNumber() - 1; //페이지 번호
        int size = 5; //페이지 당 질문 갯수

        Pageable remakePageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<Question> questionPage = null;
        //내림차순 정렬(최신 글 먼저 불러오기)
        if (searchFormDTO.isBothNull()) questionPage = questionRepository.findAllWithMember(remakePageable);
        else {
            if (searchFormDTO.iskwTypeNull()) searchFormDTO.setKwType("");
            if (searchFormDTO.iskeywordNull()) searchFormDTO.setKeyword("");
            String kwType = searchFormDTO.getKwType();
            String keyword = searchFormDTO.getKeyword();

            questionPage = switch(kwType) {
                case "title"-> questionRepository.findByTitleContainingIgnoreCase(keyword, remakePageable);
                case "content"-> questionRepository.findByContentContainingIgnoreCase(keyword, remakePageable);
                default -> questionRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword, remakePageable);
            };
        }

        return questionPage.map(QuestionListDTO::fromEntity);
    }

    //질문 단건 조회
    @Transactional(readOnly = true)
    public QuestionDTO getQuestionById(int id) {
        return QuestionDTO.fromQuestion(questionRepository.findWithMemberAndAnswers(id)
                .orElseThrow(() -> new IllegalArgumentException("Question not found")));
    }

}