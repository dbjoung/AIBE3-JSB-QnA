package forward.javaqna.domain.member.login;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import forward.javaqna.domain.member.core.Member;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/auth")
public class MemberLoginController {

    private final MemberLoginService memberService;

    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        try {
            Member member = memberService.login(username, password);
            session.setAttribute("loginedMemberUsername", member.getUsername()); // 세션에 로그인 정보 저장
            model.addAttribute("member", member);
            System.out.println("로그인 성공!");
            return "member/login_success";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/login";
        }
    }

    @PostMapping("/member/logout")
    public String doLogout(HttpSession session) {
        session.invalidate(); // 세션 종료
        System.out.println("로그아웃 성공!");
        return "redirect:/";
    }
}