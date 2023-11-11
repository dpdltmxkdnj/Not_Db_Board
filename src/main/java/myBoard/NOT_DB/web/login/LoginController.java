package myBoard.NOT_DB.web.login;

import lombok.RequiredArgsConstructor;
import myBoard.NOT_DB.domain.login.LoginService;
import myBoard.NOT_DB.domain.member.Member;
import myBoard.NOT_DB.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginMemberRepository loginMemberRepository;
    private final LoginService loginService;

//    @GetMapping("/error-500")
//    public void error500(HttpServletResponse response) throws IOException {
//        response.sendError(500);
//    }
//    @GetMapping("/ex")
//    public void exception(HttpServletResponse response) throws IOException {
//        throw new RuntimeException();
//    }
    @GetMapping("/")
    public String LoginHome(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "login/loginHome";
        }
        LoginMember loginMember = (LoginMember)session.getAttribute(SessionConst.LOGIN_MEMBER);
        //로그인
        if (loginMember == null) {
            return "login/loginHome";
        }
        model.addAttribute("loginMember", loginMember);
        return "login/memberLoginHome";
    }
    @GetMapping("/members/add")
    public String memberLoginAdd(@ModelAttribute LoginMember loginMember) {
        return "login/memberAdd";
    }
    @PostMapping("/members/add")
    public String memberLoginAddSave(@Validated @ModelAttribute LoginMember loginMember, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login/memberAdd";
        }
        loginMemberRepository.save(loginMember);
        return "redirect:/";
    }
    @GetMapping("/login")
    public String memberLogin(@ModelAttribute("loginMember") LoginForm loginMember) {
        return "login/memberLogin";
    }
    @PostMapping("/login")
    public String memberLoginSave(Model model, @Validated @ModelAttribute("loginMember") LoginForm loginMember, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/memberLogin";
        }
        LoginMember loginMember1 = loginService.login(loginMember.getLoginId(), loginMember.getPassword());
        if (loginMember1 == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/memberLogin";
        }
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember1);

        return "redirect:/";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }


}
