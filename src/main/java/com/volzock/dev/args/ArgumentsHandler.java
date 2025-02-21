package com.volzock.dev.args;

import com.volzock.dev.args.handlers.ArgumentHandler;
import com.volzock.dev.args.handlers.ExtensionArgumentHandler;
import com.volzock.dev.args.handlers.PathArgumentHandler;
import com.volzock.dev.data.ArgumentHandlerData;
import com.volzock.dev.exceptions.ArgumentException;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.volzock.dev.exceptions.ArgumentException.EMPTY_ARGUMENTS;
import static com.volzock.dev.exceptions.ArgumentException.UNKNOWN_ARGUMENT;

public class ArgumentsHandler {

    private ArgumentsHandler(){}

    private static final Map<String, ArgumentHandler> HANDLERS = Map.ofEntries(
            Map.entry("path", new PathArgumentHandler()),
            Map.entry("ext", new ExtensionArgumentHandler())
    );

    public static @NonNull ArgumentHandlerData getArguments(@NonNull String[] args) {
        var dataBuilder = ArgumentHandlerData.builder();

        for (var entry : buildArguments(args).entrySet()) {
            dataBuilder = handle(entry.getKey(), entry.getValue(), dataBuilder);
        }

        final var result = dataBuilder.build();
        final var errors = ArgumentValidator.check(result);
        if (!errors.isEmpty()) {
            throw new ArgumentException(errors.stream().collect(Collectors.joining(System.lineSeparator())));
        }
        return result;
    }

    private static @NonNull ArgumentHandlerData.ArgumentHandlerDataBuilder handle(@NonNull String arg,
            @NonNull String value, @NonNull ArgumentHandlerData.ArgumentHandlerDataBuilder dataBuilder) {
        if (!HANDLERS.containsKey(arg)) {
            throw new ArgumentException(UNKNOWN_ARGUMENT + arg);
        }
        return HANDLERS.get(arg).handle(dataBuilder, value);
    }

    private static @NonNull Map<String, String> buildArguments(@NonNull String[] args) {
        if (args.length == 0) {
            throw new ArgumentException(EMPTY_ARGUMENTS);
        }
        if (!HANDLERS.containsKey(args[0])) {
            throw new ArgumentException(UNKNOWN_ARGUMENT + args[0]);
        }

        final var result = new HashMap<String, String>();
        var currentArgument = args[0];
        var currentValue = new StringBuilder();

        for (int i = 1; i < args.length; i++) {
            final var arg = args[i];

            if (HANDLERS.containsKey(arg)) {
                result.put(currentArgument, currentValue.toString());
                currentValue = new StringBuilder();
                currentArgument = arg;
            } else {
                currentValue.append(arg);
            }
        }
        result.put(currentArgument, currentValue.toString());

        return result;
    }

}
