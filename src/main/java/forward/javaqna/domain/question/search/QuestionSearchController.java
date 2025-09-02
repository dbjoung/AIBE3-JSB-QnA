package forward.javaqna.domain.question.search;

import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.search.dto.SearchFormDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionSearchController {
    private final QuestionSearchService questionSearchService;

    @GetMapping("/list")
    public String findById(
            @ModelAttribute(name = "form") @Valid SearchFormDTO searchFormDTO,
            BindingResult bindingResult,
            Model model) {

        List<Question> findedList = questionSearchService.questionSearch(searchFormDTO.getKwType(), searchFormDTO.getKeyword());

        return "question/tem_list";
    }
}
