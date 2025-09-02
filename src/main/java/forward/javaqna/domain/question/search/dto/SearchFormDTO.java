package forward.javaqna.domain.question.search.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchFormDTO {
    @NotBlank
    String kwType;
    @NotBlank
    String keyword;
}
