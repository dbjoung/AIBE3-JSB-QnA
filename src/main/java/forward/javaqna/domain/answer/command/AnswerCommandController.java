package forward.javaqna.domain.answer.command;

import forward.javaqna.domain.answer.command.dto.AnswerRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
@Slf4j
public class AnswerCommandController {

    private final AnswerCommandService answerCommandService;

    @PostMapping("/write/{questionId}")
    public String writeAnswer(Principal principal,
                              @PathVariable("questionId") Integer questionId,
                              @Valid @ModelAttribute("answer") AnswerRequestDto answerRequestDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getAllErrors().getFirst().getDefaultMessage();
            redirectAttributes.addFlashAttribute("errorMsg", errorMsg);
            return "redirect:/question/find/" + questionId;
        }

        answerCommandService.createAnswer(principal.getName(), questionId, answerRequestDto);

        return "redirect:/question/find/" + questionId;
    }

    @PostMapping("/modify/{questionId}/{answerId}")
    public String modifyAnswer(Principal principal,
                               @PathVariable("questionId") Integer questionId,
                               @PathVariable("answerId") Integer answerId,
                               @Valid @ModelAttribute("answer") AnswerRequestDto answerRequestDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getAllErrors().getFirst().getDefaultMessage();
            redirectAttributes.addFlashAttribute("errorMsg", errorMsg);
            return "redirect:/question/find/" + questionId;
        }

        answerCommandService.modifyAnswer(principal.getName(), answerId, answerRequestDto);

        return "redirect:/question/find/" + questionId;
    }

    @PostMapping("/delete/{questionId}/{answerId}")
    public String deleteAnswer(Principal principal,
                               @PathVariable("questionId") Integer questionId,
                               @PathVariable("answerId") Integer answerId) {

        answerCommandService.deleteAnswer(principal.getName(), answerId);

        return "redirect:/question/find/" + questionId;
    }
}
