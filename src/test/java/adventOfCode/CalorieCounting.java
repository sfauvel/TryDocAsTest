package adventOfCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CalorieCounting {
    private static class Elf {
        private final List<String> input;
        private final int total;

        private Elf(List<String> input) {
            this.input = input;
            this.total = getTotal(input);
        }

        private static int getTotal(List<String> input) {
            return input.stream()
                    .mapToInt(Integer::parseInt)
                    .sum();
        }

        public List<String> getInput() {
            return input;
        }

        public int getCalories() {
            return total;
        }
    }

    private final List<Elf> elves;

    public CalorieCounting(String input) {
        final String[] lines = input.split("\n");

        final List<List<String>> elvesInput = new ArrayList<>();
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
    }

    public long nbElves() {
        return elves.size();
    }

    public String getCaloriesFor(int elfNumber) {
        return Integer.toString(getElf(elfNumber).getCalories());
    }

    public List<String> getInputFor(int elfNumber) {
        return getElf(elfNumber).getInput();
    }

    private Elf getElf(int elfNumber) {
        return elves.get(elfNumber - 1);
    }

    private int etElfNumber(Elf elf) {
        return elves.indexOf(elf) + 1;
    }

    public int getElfWithMostCalories() {
        final int max = getMostCaloriesForOneElf();
        final Elf elfWithMostCalories = getCaloriesByElf().keySet().stream()
                .filter(elf -> elf.getCalories() == max)
                .findFirst()
                .get();
        return etElfNumber(elfWithMostCalories);
    }

    public int getMostCaloriesForOneElf() {
        return getCaloriesByElf().values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();
    }

    private Map<Elf, Integer> getCaloriesByElf() {
        return elves.stream().collect(Collectors.toMap(Function.identity(), Elf::getCalories));
    }
}
