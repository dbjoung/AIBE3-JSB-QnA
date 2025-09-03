package forward.javaqna.domain.question.core.policy;

import forward.javaqna.domain.question.core.Question;

public class QuestionPolicy {

    public static void checkAuthor(Question question, String username) {
        if (!question.getMember().getUsername().equals(username)) {
            throw new IllegalStateException("작성자가 아니므로 수정/삭제 할 수 없습니다.");
        }
    }
}
