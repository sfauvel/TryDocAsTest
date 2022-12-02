package adventOfCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CalorieCounting {
    private static class Elf {
        final List<String> input;

        private Elf(List<String> input) {
            this.input = input;
        }

        public List<String> getInput() {
            return input;
        }
    }
    private final long nbElves;

    private final List<List<String>> elvesInput = new ArrayList<>();
    private final List<Elf> elves;

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

        elves = elvesInput.stream()
                .map(Elf::new)
                .collect(Collectors.toList());
        nbElves = elvesInput.size();
    }

    public long nbElves() {
        return nbElves;
    }

    public String getCaloriesFor(int elfNumber) {
        return Integer.toString(
                getCaloriesFor(getInputFor(elfNumber)));
    }

    private int getCaloriesFor(List<String> input) {
        return input.stream()
                .mapToInt(Integer::parseInt)
                .sum();
    }

    public List<String> getInputFor(int elfNumber) {
        return elves.get(elfNumber -1).getInput();
    }

    public int getElfWithMostCalories() {
        final List<Integer> collect = elvesInput.stream()
                .map(this::getCaloriesFor)
                .collect(Collectors.toList());
        final int max = collect.stream()
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
        return collect.indexOf(max) + 1;
    }

    public int getMostCaloriesForOneElf() {
        final List<Integer> collect = elvesInput.stream()
                .map(this::getCaloriesFor)
                .collect(Collectors.toList());
        final int max = collect.stream()
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
        return max;
    }
}
