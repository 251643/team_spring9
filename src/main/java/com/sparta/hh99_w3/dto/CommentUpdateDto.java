package com.sparta.hh99_w3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentUpdateDto {
    private Optional<String> content;
}
