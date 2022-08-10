package com.sparta.hh99_w3.controller;

import com.sparta.hh99_w3.config.UserDetailsImpl;
import com.sparta.hh99_w3.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/heart")
public class HeartController {

    private final HeartService hearService;


    @PostMapping("/{postId}")
    public void addLike(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        hearService.addLike(postId,userDetails.getUser());
    }

}
