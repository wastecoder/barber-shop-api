package br.com.barber_shop_api.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record SaveScheduleRequest(
        @NotNull
        @JsonProperty("startAt")
        OffsetDateTime startAt,
        @NotNull
        @JsonProperty("endAt")
        OffsetDateTime endAt,
        @NotNull
        @JsonProperty("clientId")
        Long clientId
) {}
