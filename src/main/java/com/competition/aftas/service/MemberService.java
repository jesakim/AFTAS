package com.competition.aftas.service;
import com.competition.aftas.DTO.MemberDTO;
import com.competition.aftas.domain.Member;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface MemberService {
    MemberDTO saveMember(MemberDTO memberDTO);
    Member getMemberById(Integer num);
    List<MemberDTO> getAllMembers();
    MemberDTO updateMember(Integer num, MemberDTO updatedMemberDTO);
    void deleteMember(Integer num);
}
