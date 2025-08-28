package forward.javaqna.domain.question.command;

import forward.javaqna.domain.question.command.dto.QuestionWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionCommandController {

    private final QuestionCommandService questionCommandService;

    /**
     * 질문 등록 뷰
     */
    @GetMapping("/write")
    public String writeForm(@ModelAttribute("question") QuestionWriteDto questionWriteDto, Model model) {
        model.addAttribute("isModify", false);
        return "question/write";
    }

    /**
     * 질문 등록 POST 처리
     * @return 상세페이지로 리다이렉트
     */
    //TODO: Validation 적용, 로그인된 사용자만 등록 가능
    @PostMapping("/write")
    public String writeQuestion(@ModelAttribute("question") QuestionWriteDto questionWriteDto) {
        Integer id = questionCommandService.writeQuestion(questionWriteDto);

        return "redirect:/question/find/" + id;
    }
}