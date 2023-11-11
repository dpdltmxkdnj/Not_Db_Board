package myBoard.NOT_DB.domain.member;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {
    private static final Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        member.setDate(date());
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long memberId, Member updateParam) {
        Member member = findById(memberId);

        member.setContent(updateParam.getContent());
        member.setCaption(updateParam.getCaption());


        member.setDate(date());
    }
    public String date(){
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();

        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        // 포맷 적용
        String formatedNow = now.format(formatter);
        return formatedNow;
    }

    public void clear() {
        store.clear();
    }
}
