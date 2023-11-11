package myBoard.NOT_DB.domain.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Member {
    private Long id;
    private String caption;
    private String name;
    private String content;
    private String date;
    public Member() {
    }
    public Member(String caption, String name, String content) {
        this.caption = caption;
        this.name = name;
        this.content = content;
    }
    public Member(String caption, String content) {
        this.caption = caption;
        this.content = content;
    }
    public Member(String caption, String name, String content,String date) {
        this.caption = caption;
        this.name = name;
        this.content = content;
        this.date=date;
    }
}
