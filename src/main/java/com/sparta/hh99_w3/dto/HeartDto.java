package com.sparta.hh99_w3.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class HeartDto {
    private Long userId;

    public HeartDto(Long id) {
        this.userId = id;
    }

}
