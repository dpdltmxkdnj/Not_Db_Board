package myBoard.NOT_DB.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myBoard.NOT_DB.domain.member.Member;
import myBoard.NOT_DB.domain.member.MemberRepository;
import myBoard.NOT_DB.web.validation.form.EditMemberForm;
import myBoard.NOT_DB.web.validation.form.SaveMemberForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);

        return "basic/members";
    }
    @GetMapping("/{memberId}")
    public String member(Model model, @PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId);
        model.addAttribute("member", member);

        return "basic/member";
    }
    @GetMapping("/{memberId}/edit")
    public String memberEdit(Model model, @PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId);
        model.addAttribute("member", member);

        return "basic/memberEdit";
    }
    @PostMapping("/{memberId}/edit")
    public String memberEdits(Model model, @PathVariable Long memberId, @Validated @ModelAttribute("member") EditMemberForm form, BindingResult bindingResult) {



        if (bindingResult.hasErrors()) {
            log.info("bindingResult:{}",bindingResult);
            return "basic/memberEdit";
        }
        Member member = new Member(form.getCaption(), form.getContent());
        memberRepository.update(memberId,member);
        model.addAttribute("member", memberRepository.findById(memberId));

        return "redirect:/basic/members/{memberId}";
    }
    @GetMapping("/add")
    public String addMember(Model model) {
        model.addAttribute("member", new Member());
        return "basic/memberAdd";
    }
    @PostMapping("/add")
    public String addMembers(Model model, @Validated @ModelAttribute("member") SaveMemberForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("bindingResult:{}",bindingResult);
            return "basic/memberAdd";
        }
        Member member = new Member(form.getCaption(),form.getName(),form.getContent());
        memberRepository.save(member);
        redirectAttributes.addAttribute("id", member.getId());
        return "redirect:/basic/members/{id}";
    }
    @PostConstruct
    public void init() {
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();

        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // 포맷 적용
        String formatedNow = now.format(formatter);

        // 결과 출력
        System.out.println(formatedNow);  // 2021/06/17
        memberRepository.save(new Member("오늘의 일기", "정순영", "우하하하하하",formatedNow));
        memberRepository.save(new Member("오늘의 운지", "노무현", "운지",formatedNow));
    }
}
