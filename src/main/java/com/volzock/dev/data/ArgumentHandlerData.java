package com.volzock.dev.data;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Data
@FieldDefaults(level = PRIVATE)
public class ArgumentHandlerData {
    String path;
    String extension;
    Long cpus;
}
