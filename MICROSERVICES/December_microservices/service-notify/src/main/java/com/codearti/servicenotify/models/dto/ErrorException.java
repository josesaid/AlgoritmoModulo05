package com.codearti.servicenotify.models.dto;

import java.util.List;

public record ErrorException(
        String code,
        String description,
        List<String> detail
){ }

