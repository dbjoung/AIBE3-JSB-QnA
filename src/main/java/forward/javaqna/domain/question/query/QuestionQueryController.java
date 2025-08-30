package forward.javaqna.domain.question.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionQueryController {
    private final QuestionQueryService questionQueryService;

    //Question 목록 불러오기
    @GetMapping("/list")
    public String getQuestionList(Model model) {
        model.addAttribute("questionList", questionQueryService.getQuestionList());
        return  "question/list";
    }

    //Question 상세보기 불러오기
    @GetMapping("/find/{id}")
    public String getQuestionDetail(Model model, @PathVariable("id") int id) {
        model.addAttribute("question", questionQueryService.getQuestionById(id));
        return  "question/find";
    }
}
