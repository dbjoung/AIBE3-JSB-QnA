package forward.javaqna.domain.member.login;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import forward.javaqna.domain.member.core.Member;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/auth")
public class MemberLoginController {

    @Getter
    @Setter
    public static class LoginForm{
        @Size(min=3, max= 25)
        @NotBlank(message = "유저 ID는 필수 항목입니다.")
        private String username;

        @NotBlank(message = "패스워드는 필수 항목입니다.")
        private String password;
    }

    @GetMapping("/login")
    public String showLogin(LoginForm loginForm) {
        return "member/login";
    }
}