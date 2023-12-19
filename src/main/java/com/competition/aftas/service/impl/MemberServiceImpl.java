package com.competition.aftas.service.impl;
import com.competition.aftas.DTO.MemberDTO;
import com.competition.aftas.domain.Member;
import com.competition.aftas.repository.MemberRepository;
import com.competition.aftas.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDTO saveMember(MemberDTO memberDTO) {
        Member member = new Member();
        BeanUtils.copyProperties(memberDTO, member);
        Member savedMember = memberRepository.save(member);
        BeanUtils.copyProperties(savedMember, memberDTO);
        return memberDTO;
    }
    @Override
    public Member getMemberById(Integer id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        return optionalMember.orElse(null);
    }
    @Override
    public List<MemberDTO> getAllMembers() {
        List<Member> memberList = memberRepository.findAll();
        return memberList.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public MemberDTO updateMember(Integer num, MemberDTO updatedMemberDTO) {
        Optional<Member> optionalMember = memberRepository.findById(num);
        if (optionalMember.isPresent()) {
            Member existingMember = optionalMember.get();
            BeanUtils.copyProperties(updatedMemberDTO, existingMember);
            Member updatedMember = memberRepository.save(existingMember);
            BeanUtils.copyProperties(updatedMember, updatedMemberDTO);
            return updatedMemberDTO;
        }
        return null;
    }
    @Override
    public void deleteMember(Integer num) {
        memberRepository.deleteById(num);
    }
    private MemberDTO convertEntityToDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(member, memberDTO);
        return memberDTO;
    }
}
