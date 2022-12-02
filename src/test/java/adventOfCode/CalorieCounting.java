package adventOfCode;

import java.util.Arrays;

public class CalorieCounting {
    private final long nbElves;

    public CalorieCounting(String input) {
        final String[] lines = input.split("\n");

        nbElves = Arrays.stream(lines)
                .filter(line -> line.equals(""))
                .count() + 1;
    }

    public long nbElves() {
        return nbElves;
    }
}
