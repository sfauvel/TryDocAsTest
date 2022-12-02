package adventOfCode;

import java.util.ArrayList;
import java.util.List;

public class CalorieCounting {
    private final long nbElves;

    private final List<List<String>> elvesInput = new ArrayList<>();

    public CalorieCounting(String input) {
        final String[] lines = input.split("\n");

        int currentElf = 1;
        List<String> currentInput = new ArrayList<>();
        elvesInput.add(currentInput);
        for (String line : lines) {
            if (line.isEmpty()) {
                currentInput = new ArrayList<>();
                elvesInput.add(currentInput);
            } else {
                currentInput.add(line);
            }
        }
        nbElves = elvesInput.size();
    }

    public long nbElves() {
        return nbElves;
    }

    public String getCaloriesFor(int elfNumber) {
        return Integer.toString(
                getInputFor(elfNumber).stream()
                        .mapToInt(Integer::parseInt)
                        .sum());
    }

    public List<String> getInputFor(int elfNumber) {
        return elvesInput.get(elfNumber - 1);
    }
}
