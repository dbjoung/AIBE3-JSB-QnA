package forward.javaqna.domain.question.query;

import forward.javaqna.domain.answer.query.AnswerQueryService;
import forward.javaqna.domain.question.query.DTO.AnswerDTO;
import forward.javaqna.domain.question.query.DTO.QuestionDTO;
import forward.javaqna.domain.question.query.DTO.QuestionListDTO;
import forward.javaqna.domain.question.query.DTO.SearchFormDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionQueryController {
    private final QuestionQueryService questionQueryService;
    private final AnswerQueryService answerQueryService;

    //Question 목록 불러오기
    @GetMapping("/list") //기본 페이지 '1'페이지로 지정
    public String getQuestionList(
            @ModelAttribute(name = "form") SearchFormDTO searchFormDTO,
            Model model,
            @PageableDefault(page = 1)
            Pageable pageable) {

        Page<QuestionListDTO> questionListDTOPage = questionQueryService.getQuestionPaging(searchFormDTO, pageable);

        int pageLimit = 5; //보여줄 최대 페이지 갯수
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / pageLimit))) - 1) * pageLimit + 1;//첫 페이지
        int endPage = Math.min((startPage + pageLimit - 1), questionListDTOPage.getTotalPages());//마지막 페이지
        model.addAttribute("questionList", questionListDTOPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return  "question/list";
    }

    //Question 상세보기 불러오기
    @GetMapping("/find/{id}")
    public String getQuestionDetail(Model model, @PathVariable("id") int id,
                                    @RequestParam(value = "page", defaultValue = "0") int page) {

        QuestionDTO question = questionQueryService.getQuestionById(id);
        Page<AnswerDTO> answerPage = answerQueryService.getPage(id, page);

        model.addAttribute("question", question);
        model.addAttribute("answerPage", answerPage);

        return "question/find";
    }
}
