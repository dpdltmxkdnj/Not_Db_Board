package myBoard.NOT_DB.web.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data

public class LoginMember {
    private Long id;
    @NotEmpty
    private String loginId; //로그인 ID
    @NotEmpty
    private String name; //사용자 이름
    @NotEmpty
    private String password;
}
