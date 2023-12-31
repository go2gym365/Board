package hwan.board.board_project.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hwan.board.board_project.domain.Member;
import hwan.board.board_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByUsername(username);
        if(findMember == null) {
            throw new UsernameNotFoundException("Not found: " + username);
        }
        log.info("findMember : {}", findMember);
        return User.builder()
                    .username(findMember.getUsername())
                    .password(findMember.getPassword())
                    .build();
    }

    @Transactional
    public Member saveMember(Member member) {
        duplicateDuplicateMember(member);

        return memberRepository.save(member);
    }

    public void duplicateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByUsernameOrNickname(member.getUsername(), member.getNickname());
        if(findMember.isPresent()) {
            throw new IllegalStateException("이미 존재하는 정보입니다.");
        }
    }

    public boolean usernameDuplicateCheck(String username) {
        return memberRepository.existsByUsername(username);
    }
    
    public boolean nicknameDuplicateCheck(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public Member findUsername(String username) {
        return memberRepository.findByUsernameEquals(username);
    }

    public Member findUserId(Long userId) {
        return memberRepository.findByUserId(userId);
    }
}
