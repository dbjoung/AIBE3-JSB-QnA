package forward.javaqna.domain.answer.command;

import forward.javaqna.domain.answer.command.dto.AnswerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerCommandController {

    private final AnswerCommandService answerCommandService;

    @PostMapping("/create/{id}")
    public String createAnswer(@PathVariable("id") Integer questionId,
                               @RequestParam(name="content") String content,
                               Principal principal) {

        AnswerRequestDto answerRequestDto = new AnswerRequestDto(content);

        answerCommandService.createAnswer(
                principal.getName(),
                questionId,
                answerRequestDto
        );

        return String.format("redirect:/question/find/%d", questionId);
    }
}
