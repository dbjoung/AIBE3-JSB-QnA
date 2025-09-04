package forward.javaqna.domain.member.join;

import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.member.core.policy.MemberJoinPolicy;
import forward.javaqna.domain.member.join.dto.SignUpFormDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user/auth")
@RequiredArgsConstructor
@Controller
public class MemberJoinController {
    private final MemberJoinService memberJoinService;

    @GetMapping("/signup")
    public String viewSignup(@ModelAttribute(name = "form") SignUpFormDTO signUpForm) {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(
            @ModelAttribute(name = "form") @Valid SignUpFormDTO signUpForm,
            BindingResult bindingResult,
            Model model
    ) {

        if (!MemberJoinPolicy.isPasswordMatch(signUpForm.getPassword(), signUpForm.getPasswordConfirm()))
            bindingResult.rejectValue("passwordConfirm", "error.passwordConfirm", "입력한 비밀번호가 다릅니다.");

        if (!bindingResult.hasFieldErrors("username") && memberJoinService.hasSameUsername(signUpForm.getUsername()))
            bindingResult.rejectValue("username", "error.username.duplicate", "이미 있는 사용자 아이디입니다.");

        if (bindingResult.hasErrors()) return "member/signup";

        Member newMember = memberJoinService.addMember(signUpForm);
        model.addAttribute("member", newMember);

        return "/member/signup_result";
    }
}
