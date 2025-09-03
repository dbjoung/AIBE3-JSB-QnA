package forward.javaqna.domain.question.command;

import forward.javaqna.domain.question.command.dto.QuestionModifyDto;
import forward.javaqna.domain.question.command.dto.QuestionWriteDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

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
    @PostMapping("/write")
    public String writeQuestion(@Valid @ModelAttribute("question") QuestionWriteDto questionWriteDto,
                                BindingResult bindingResult,
                                Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question/write";
        }

        Integer id = questionCommandService.writeQuestion(questionWriteDto, principal.getName());

        return "redirect:/question/find/" + id;
    }

    /**
     * 질문 수정 뷰
     */
    @GetMapping("/modify/{id}")
    public String editForm(@PathVariable("id") Integer questionId, Model model) {
        QuestionModifyDto editDto = questionCommandService.findByIdForModify(questionId);

        model.addAttribute("question", editDto);
        model.addAttribute("isModify", true);

        return "question/write";
    }

    /**
     * 질문 수정 POST 처리
     * @param questionId 수정 대상 ID
     * @return 상세페이지로 리다이렉트
     */
    @PostMapping("/modify/{id}")
    public String editQuestion(@PathVariable("id") Integer questionId,
                               @Valid @ModelAttribute("question") QuestionModifyDto questionEditDto,
                               BindingResult bindingResult,
                               Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question/write";
        }

        questionCommandService.modify(questionEditDto, principal.getName());

        return "redirect:/question/find/" + questionId;
    }

    /**
     * 질문 삭제 POST 처리
     * @param questionId 삭제 대상 ID
     * @return 질문 리스트 페이지로 리다이렉트
     */
    @PostMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Integer questionId,
                                 Principal principal) {
        questionCommandService.delete(questionId, principal.getName());

        return "redirect:/question/list";
    }
}