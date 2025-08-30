package forward.javaqna.domain.question.command;

import forward.javaqna.domain.question.command.dto.QuestionModifyDto;
import forward.javaqna.domain.question.command.dto.QuestionWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    //TODO: Validation 적용, 질문 등록자만 수정 가능
    @PostMapping("/modify/{id}")
    public String editQuestion(@PathVariable("id") Integer questionId, @ModelAttribute QuestionModifyDto questionEditDto) {
        questionCommandService.modify(questionEditDto);

        return "redirect:/question/find/" + questionId;
    }

    /**
     * 질문 삭제 POST 처리
     * @param questionId 삭제 대상 ID
     * @return 질문 리스트 페이지로 리다이렉트
     */
    //TODO: 질문 등록자만 삭제 가능
    @PostMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Integer questionId) {
        questionCommandService.delete(questionId);

        return "redirect:/question/list";
    }
}