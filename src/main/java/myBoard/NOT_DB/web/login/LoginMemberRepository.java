package myBoard.NOT_DB.web.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class LoginMemberRepository {
    private static Map<Long, LoginMember> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용
    public LoginMember save(LoginMember LoginMember) {
        LoginMember.setId(++sequence);
        log.info("save: LoginMember={}", LoginMember);
        store.put(LoginMember.getId(), LoginMember);
        return LoginMember;
    }
    public LoginMember findById(Long id) {
        return store.get(id);
    }
    public Optional<LoginMember> findByLoginId(String loginId) {
        List<LoginMember> all = findAll();



        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }
    public List<LoginMember> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }
}
