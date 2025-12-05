package com.codearti.servicenotify.models.dto;

public record EventNotify(
        int id,
        String owner,
        boolean status
) { }
