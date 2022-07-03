package com.codestates.member.service;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.entity.Member;
import com.codestates.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * V2
 *  - 메서드 구현
 *  - DI 적용
 */
@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        // TODO should business logic

        verifyExistsEmail(member.getEmail());
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        // TODO should business logic
        Member foundMember = findVerifiedMember(member.getMemberId());

        //OfNullable을 사용하면 null값이 있더라도 NullPointerException이 발생하지 않는다.
        //null이 아닌 경우에는 다음 메서드인 ifPresent() 메서드를 호출할 수 있다!
        Optional.ofNullable(member.getName())
                .ifPresent(name -> foundMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone -> foundMember.setPhone(phone));

        throw new BusinessLogicException(ExceptionCode.NOT_IMPLEMENTATION);
    }

    public Member findMember(long memberId) {
        // TODO should business logic
        return findVerifiedMember(memberId);
    }

    public List<Member> findMembers() {
        // TODO should business logic
        return (List<Member>) memberRepository.findAll();
    }

    public void deleteMember(long memberId) {
        // TODO should business logic
        Member foundMember = findVerifiedMember(memberId);
        memberRepository.delete(foundMember);

    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        return member.get();

        /*
        Another approach:
        Member findMember = member.orElseThrow(() -> new BusinessLogicException(ExceptionCOde.MEMBER_NOT_FOUND)):
        return findMember;
         */
    }

    public void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }
}
