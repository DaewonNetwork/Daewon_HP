package org.daewon.phreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.dto.ReplyDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.service.PharmacyService;
import org.daewon.phreview.service.ReplyService;
import org.daewon.phreview.service.ReplyServiceImpl;
import org.daewon.phreview.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/sample")
public class SampleController {

    private final PharmacyService pharmacyService;
    private final ReviewService reviewService;
    private final ReplyService replyService;

    @GetMapping("/home")
    public void home() {
    }

    @GetMapping("/category")
    public void category() {
    }

    @GetMapping("/pharmacyInfo")
    public void pharmacyInfo(Long phId, Model model) {
        PharmacyDTO pharmacyDTO = pharmacyService.getPharmacyInfo(phId);

        List<ReviewDTO> reviewDTO = reviewService.getListOfPharmacy(phId);


        List<ReplyDTO> replyDTO = replyService.getListOfReview(2l);
        log.info("1번째인포:"+pharmacyDTO);
        log.info("1번째인포:"+reviewDTO);
        log.info("1번째인포:"+replyDTO);
        model.addAttribute("pharmacyDTO", pharmacyDTO);
        model.addAttribute("reviewDTO", reviewDTO);
        model.addAttribute("replyDTO", replyDTO);
    }
}