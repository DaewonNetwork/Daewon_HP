package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.AuthSignupDTO;
import org.daewon.phreview.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Log4j2
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService userService;

    @GetMapping("/signup")
    public void signupGET() {
        log.info("join get.....");
    }
    

    @Operation(summary = "회원가입 처리", description = "회원가입 요청을 처리합니다.")
    @PostMapping("/signup")
    public String signup(AuthSignupDTO userSignupDTO, RedirectAttributes redirectAttributes){
        log.info("signup post.....");
        log.info(userSignupDTO);

        try {
            userService.signup(userSignupDTO);
        } catch (AuthService.MidExistException e) {
            redirectAttributes.addFlashAttribute("error", "uid");
            return "redirect:/auth/signup";
        }

        redirectAttributes.addFlashAttribute("result", "success");
        return "redirect:/auth/signin";
    }
}