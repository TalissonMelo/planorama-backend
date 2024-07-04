package com.liberbox.legend.controller.request;

import javax.validation.constraints.NotBlank;

public record LegendRequest(@NotBlank String color, @NotBlank String description) {
}
