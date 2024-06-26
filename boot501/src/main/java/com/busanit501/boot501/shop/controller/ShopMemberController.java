package com.busanit501.boot501.shop.controller;


import com.busanit501.boot501.service.MemberService;
import com.busanit501.boot501.shop.dto.MemberFormDto;
import com.busanit501.boot501.shop.entity.ShopMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class ShopMemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "shop/member/memberForm";
    }

    @PostMapping(value = "/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "shop/member/memberForm";
        }

        try {
            ShopMember shopMember = ShopMember.createMember(memberFormDto, passwordEncoder);
//            memberService.saveMember(shopMember);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "shop/member/memberForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember(){
        return "shop/member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "shop/member/memberLoginForm";
    }

}