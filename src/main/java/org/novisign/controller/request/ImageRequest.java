package org.novisign.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ImageRequest(@NotBlank(message = "URL cannot be empty.") String url,
                           @NotNull(message = "Duration cannot be null.") @Positive(message = "Duration must be positive.") Double duration) {
}
