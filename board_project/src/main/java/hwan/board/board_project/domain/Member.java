package hwan.board.board_project.domain;

import org.springframework.security.crypto.password.PasswordEncoder;

import hwan.board.board_project.dto.MemberFormDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Builder
    public Member(String name, String nickname, String username, String password) {
        this.name = name;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
    }

    public static Member createMember(MemberFormDTO memberFormDTO, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                            .name(memberFormDTO.getName())
                            .nickname(memberFormDTO.getNickname())
                            .username(memberFormDTO.getUsername())
                            .password(passwordEncoder.encode(memberFormDTO.getPassword()))
                            .build();
        return member;
    }
}
