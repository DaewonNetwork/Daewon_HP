package org.daewon.phreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.UserJoinDTO;
import org.daewon.phreview.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    public void joinGET() {
        log.info("join get");
    }

    @PostMapping("/join")
    public String joinPOST(UserJoinDTO userJoinDTO, RedirectAttributes redirectAttributes) {

        log.info("join post");
        log.info(userJoinDTO);

        try {
            userService.join(userJoinDTO);
        }catch (UserService.MidExistException e) {
            redirectAttributes.addFlashAttribute("error", "userId");
            return "redirect:/user/join";
        }

        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/user/login";

    }


}
