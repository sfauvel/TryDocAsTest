package adventOfCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sfvl.doctesting.junitextension.SimpleApprovalsExtension;
import org.sfvl.doctesting.utils.NoTitle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@DisplayName(value = "Day 1: Calorie Counting")
public class Day1Doc {
    @RegisterExtension
    static SimpleApprovalsExtension doc = new SimpleApprovalsExtension();

    @NoTitle
    @Test
    public void description() {
        doc.write(
                "Santa's reindeer typically eat regular reindeer food, but they need a lot of magical energy to deliver presents on Christmas. For that, their favorite snack is a special type of star fruit that only grows deep in the jungle. The Elves have brought you on their annual expedition to the grove where the fruit grows.",
                "To supply enough magical energy, the expedition needs to retrieve a minimum of fifty stars by December 25th. Although the Elves assure you that the grove has plenty of fruit, you decide to grab any fruit you see along the way, just in case.",
                "Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!",
                "The jungle must be too overgrown and difficult to navigate in vehicles or access from the air; the Elves' expedition traditionally goes on foot. As your boats approach land, the Elves begin taking inventory of their supplies. One important consideration is food - in particular, the number of Calories each Elf is carrying (your puzzle input).",
                "The Elves take turns writing down the number of Calories contained by the various meals, snacks, rations, etc. that they've brought with them, one item per line. Each Elf separates their own inventory from the previous Elf's inventory (if any) by a blank line."
        );
    }

    @Test
    public void global_example() {
        String input = String.join("\n",
                "1000",
                "2000",
                "3000",
                "",
                "4000",
                "",
                "5000",
                "6000",
                "",
                "7000",
                "8000",
                "9000",
                "",
                "10000");

        final CalorieCounting calorieCounting = new CalorieCounting(input);
        doc.write("For example, suppose the Elves finish writing their items' Calories and end up with the following list:",
                "",
                "----",
                input,
                "----",
                "",
                "This list represents the Calories of the food carried by " + calorieCounting.nbElves() + " Elves:",
                "",
                ""
        );

        doc.write(IntStream.rangeClosed(1, 5)
                        .mapToObj(elfNumber -> formatElfResult(
                                Integer.toString(elfNumber),
                                calorieCounting.getInputFor(elfNumber),
                                calorieCounting.getCaloriesFor(elfNumber)))
                .collect(Collectors.joining("\n\n")),
                "",
                "");

        final String mostCaloriesForOneElf = "24000";
        final String elfWithMostCalories = "4";
        doc.write("In case the Elves get hungry and need extra snacks, " +
                "they need to know which Elf to ask: they'd like to know " +
                "how many Calories are being carried by the Elf carrying " +
                "the most Calories. " +
                "In the example above, this is " + mostCaloriesForOneElf + " (carried by the Elf number " + elfWithMostCalories + ").");
//
//    Find the Elf carrying the most Calories. How many total Calories is that Elf carrying?
//
    }

    private String formatElfResult(String elfNumber, List<String> input_calories, String total) {
        if (input_calories.isEmpty()) {
            return "The Elf number " + elfNumber + " is not carrying food.";
        } else if (input_calories.size() == 1) {
            if (input_calories.get(0).equals(total)) {
                return "The Elf number " + elfNumber + " is carrying one food item with " + input_calories.get(0) + " Calories.";
            } else {
                return "The Elf number " + elfNumber + " is carrying one food item with " + input_calories + " Calories, a total of " + total + " Calories.";
            }
        } else {
            String formatted_input_calories = format_input_calories(input_calories);
            return "The Elf number " + elfNumber + " is carrying food with " + formatted_input_calories + " Calories, a total of " + total + " Calories.";
        }
    }

    private String format_input_calories(List<String> input_calories) {
        final int lastIndex = input_calories.size() - 1;
        return input_calories.stream()
                .limit(lastIndex)
                .collect(Collectors.joining(", "))
                + " and "
                + input_calories.get(lastIndex);

    }
}


