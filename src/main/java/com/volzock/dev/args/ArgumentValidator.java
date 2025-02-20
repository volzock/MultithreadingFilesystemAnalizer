package com.volzock.dev.args;

import com.volzock.dev.data.ArgumentHandlerData;
import lombok.NonNull;
import lombok.experimental.PackagePrivate;

import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static com.volzock.dev.exceptions.ArgumentException.NOT_VALID_TYPE;

@PackagePrivate
class ArgumentValidator {
    private ArgumentValidator() {}

    private static final String EXTENSION_REGEX = "\\.(\\w|\\d|\\.)+";

    private static final List<Function<ArgumentHandlerData, String>> VALIDATIONS = List.of(
            data -> {
                final var path = data.getPath();
                return path != null && Paths.get(path)
                        .toFile()
                        .exists() ? null : String.format("%s: that path don't exists", path);
            },
            data -> {
                final var ext = data.getExtension();
                if (ext != null && !ext.matches(EXTENSION_REGEX)) {
                    return String.format(NOT_VALID_TYPE, ext, EXTENSION_REGEX);
                }
                return null;
            }
    );

    public static @NonNull List<String> check(@NonNull ArgumentHandlerData data) {
        return VALIDATIONS.stream()
                .map(func -> func.apply(data))
                .filter(Objects::nonNull)
                .toList();
    }
}
