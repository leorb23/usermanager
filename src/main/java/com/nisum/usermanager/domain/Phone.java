package com.nisum.usermanager.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    private UUID id = UUID.randomUUID();
    private String number;
    private String cityCode;
    private String countryCode;
}
