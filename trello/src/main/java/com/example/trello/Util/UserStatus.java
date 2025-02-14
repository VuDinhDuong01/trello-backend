package com.example.trello.Util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserStatus {
    @JsonProperty("active")
    Active,
    @JsonProperty("inactive")
    InActive,
    @JsonProperty("none")
    None
}
