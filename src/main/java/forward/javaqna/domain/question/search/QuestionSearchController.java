package forward.javaqna.domain.question.search;

import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.search.dto.SearchFormDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionSearchController {
    private final QuestionSearchService questionSearchService;

    @GetMapping("/search")
    public String findById(
            @ModelAttribute(name = "form") SearchFormDTO searchFormDTO,
            @PageableDefault(page = 0) Pageable pageable,
            Model model) {

        if (searchFormDTO.iskwTypeNull()) searchFormDTO.setKwType("");
        if (searchFormDTO.iskeywordNull()) searchFormDTO.setKeyword("");

        Page<Question> page = questionSearchService.searchList(searchFormDTO, pageable);

        model.addAttribute("page", page);

        return "question/search";
    }
}
