package com.codestates.member.repository;

import com.codestates.member.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {
    // TODO 예제 코드에 나와있는 쿼리 메서드를 정의해보세요.
    Optional<Member> findByEmail(String email);

}
