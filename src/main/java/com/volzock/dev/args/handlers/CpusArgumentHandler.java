package com.volzock.dev.args.handlers;

import com.volzock.dev.data.ArgumentHandlerData;
import lombok.NonNull;

public class CpusArgumentHandler implements ArgumentHandler {

    @Override
    public @NonNull ArgumentHandlerData.ArgumentHandlerDataBuilder handle(
            ArgumentHandlerData.@NonNull ArgumentHandlerDataBuilder data, @NonNull String arg) {
        return data.cpus(Long.valueOf(arg));
    }
}
