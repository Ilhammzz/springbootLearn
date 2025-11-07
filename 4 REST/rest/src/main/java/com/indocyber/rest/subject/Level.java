package com.indocyber.rest.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Level {
    BAC("Bachelor"),
    MAS("Master"),
    PHD("Doctorate");

    private final String label;
}
