package com.competition.aftas.controller;
import com.competition.aftas.DTO.MemberDTO;
import com.competition.aftas.domain.Member;
import com.competition.aftas.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/{num}")
    public Member getMember(@PathVariable Integer num) {
        return memberService.getMemberById(num);
    }
    @GetMapping
    public List<MemberDTO> getAllMembers() {
        return memberService.getAllMembers();
    }
    @PostMapping
    public MemberDTO createMember(@RequestBody MemberDTO memberDTO) {
        return memberService.saveMember(memberDTO);
    }
    @PutMapping("/{num}")
    public MemberDTO updateMember(@PathVariable Integer num, @RequestBody MemberDTO updatedMemberDTO) {
        return memberService.updateMember(num, updatedMemberDTO);
    }
    @DeleteMapping("/{nu")
    public void deleteMember(@PathVariable Integer num) {
        memberService.deleteMember(num);
    }
}