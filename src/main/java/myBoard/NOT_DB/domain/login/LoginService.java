package myBoard.NOT_DB.domain.login;

import lombok.RequiredArgsConstructor;
import myBoard.NOT_DB.domain.member.Member;
import myBoard.NOT_DB.domain.member.MemberRepository;
import myBoard.NOT_DB.web.login.LoginMember;
import myBoard.NOT_DB.web.login.LoginMemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginMemberRepository loginMemberRepository;
    /**
     * @return null이면 로그인 실패
     */
    public LoginMember login(String loginId, String password) {
        return loginMemberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
