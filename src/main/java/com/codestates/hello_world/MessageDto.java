package com.codestates.hello_world;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MessageDto {
    @NotBlank
    private String message;
}
