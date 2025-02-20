package com.volzock.dev;

import com.volzock.dev.args.ArgumentsHandler;
import com.volzock.dev.exceptions.ArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.volzock.dev.exceptions.ArgumentException.EMPTY_ARGUMENTS;

public class ArgumentHandlerTest {

    @Test
    void testEmpty() {
        final var thrown = Assertions.assertThrows(ArgumentException.class, () -> {
            final var args = new String[0];
            ArgumentsHandler.getArguments(args);
        });

        Assertions.assertEquals(EMPTY_ARGUMENTS, thrown.getMessage());
    }

    @Test
    void testOnePath() {
        final var thrown = Assertions.assertThrows(ArgumentException.class, () -> {
            final String[] args = {"path"};
            ArgumentsHandler.getArguments(args);
        });

        Assertions.assertEquals(": that path don't exists", thrown.getMessage());
    }

    @Test
    void testExistsPath() {
        Assertions.assertDoesNotThrow(() -> {
            final String[] args = {"path", "."};
            ArgumentsHandler.getArguments(args);
        });
    }

    // протестировать extension
}
