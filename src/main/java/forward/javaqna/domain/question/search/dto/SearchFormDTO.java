package forward.javaqna.domain.question.search.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SearchFormDTO {
    private String kwType;
    private String keyword;

    public SearchFormDTO(String kwType, String keyword) {
        this.kwType = kwType;
        this.keyword = keyword;
    }

    public boolean iskwTypeNull() {
        return kwType == null;
    }
    public boolean iskeywordNull() {
        return keyword == null;
    }
}
