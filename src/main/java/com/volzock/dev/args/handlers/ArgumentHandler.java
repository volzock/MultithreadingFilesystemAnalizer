package com.volzock.dev.args.handlers;

import com.volzock.dev.data.ArgumentHandlerData;
import lombok.NonNull;

public interface ArgumentHandler {
    @NonNull ArgumentHandlerData.ArgumentHandlerDataBuilder handle(
            @NonNull final ArgumentHandlerData.ArgumentHandlerDataBuilder data,
            @NonNull final String arg);
}
