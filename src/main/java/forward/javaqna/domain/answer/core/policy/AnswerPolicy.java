package forward.javaqna.domain.answer.core.policy;

import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.member.core.Member;

public class AnswerPolicy {

    public static void checkAuthor(Answer answer, Member member) {
        if (answer.getMember() != member) throw new IllegalStateException("작성자가 아니므로 수정/삭제 할 수 없습니다.");
    }
}
