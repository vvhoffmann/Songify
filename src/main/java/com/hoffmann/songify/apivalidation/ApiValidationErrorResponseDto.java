package com.hoffmann.songify.apivalidation;

import java.util.List;

public record ApiValidationErrorResponseDto(List<String> message) {
}
