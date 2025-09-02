package forward.javaqna.domain.answer.query;

import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.answer.core.AnswerRepository;
import forward.javaqna.domain.question.query.DTO.AnswerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerQueryService {

    private final AnswerRepository answerRepository;

    public Page<AnswerDTO> getPage(Integer questionId, int page) {
        // 한 페이지에 5개의 답변, 작성일 기준 오름차순 정렬
        Pageable pageable = PageRequest.of(page, 5, Sort.by("createDate").ascending());

        Page<Answer> answerPage = answerRepository.findByQuestionId(questionId, pageable);

        return answerPage.map(AnswerDTO::fromAnswer);
    }
}
