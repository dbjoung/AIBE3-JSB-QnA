package forward.javaqna.domain.member.join.dto;

import forward.javaqna.domain.member.core.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class SignUpFormDTO {
    @NotBlank(message = "아이디를 입력해주세요.")
    String username;
    @Setter
    @NotBlank(message = "패스워드를 입력해주세요.")
    String password;
    @NotBlank(message = "패스워드를 한번 더 입력해주세요.")
    String passwordConfirm;
    @NotBlank(message = "닉네임을 입력해주세요.")
    String nickname;

    public Member toMember() {
        return new Member(username, password, nickname);
    }
}