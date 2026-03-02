package com.example.stickers.dto;

public record LoginResponseDto(String message, UserDto user, String jwtToken) {
}
