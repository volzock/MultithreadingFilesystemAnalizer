package com.volzock.dev.args.handlers;

import com.volzock.dev.data.ArgumentHandlerData;
import lombok.NonNull;
import lombok.experimental.PackagePrivate;

@PackagePrivate
public class PathArgumentHandler implements ArgumentHandler {

    @Override
    public @NonNull ArgumentHandlerData.ArgumentHandlerDataBuilder handle(
            ArgumentHandlerData.@NonNull ArgumentHandlerDataBuilder data, @NonNull String arg) {
        return data.path(arg);
    }
}
