package nl.trifox.mythforge.Dice;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class DiceParser {
    private static final Pattern DICE_PATTERN = Pattern.compile(
            "(\\d*)d(\\d+)([+-]\\d+)?(adv|dis)?(kh|kl)?(\\d+)?"
    );

    private static final Random RANDOM = new Random();

    public static DiceResult roll(String expression) {
        String expr = expression.replace(" ", "");
        Matcher matcher = DICE_PATTERN.matcher(expr);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid dice expression: " + expression);
        }

        int count = matcher.group(1).isEmpty() ? 1 : Integer.parseInt(matcher.group(1));
        int sides = Integer.parseInt(matcher.group(2));
        int modifier = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0;
        String advDis = matcher.group(4);
        String khkl = matcher.group(5);
        int pickCount = matcher.group(6) != null ? Integer.parseInt(matcher.group(6)) : count;

        int[] rolls;

        if (advDis != null && (advDis.equals("adv") || advDis.equals("dis"))) {
            if (count != 1 || sides != 20) {
                throw new IllegalArgumentException("Advantage/disadvantage is only valid for 1d20 rolls.");
            }
            int first = RANDOM.nextInt(sides) + 1;
            int second = RANDOM.nextInt(sides) + 1;
            rolls = new int[]{first, second};
            int total = advDis.equals("adv") ? Math.max(first, second) + modifier
                    : Math.min(first, second) + modifier;
            return new DiceResult(total, rolls, modifier, expression);

        } else {
            rolls = new int[count];
            for (int i = 0; i < count; i++) {
                rolls[i] = RANDOM.nextInt(sides) + 1;
            }

            int[] pickedRolls = rolls;
            if (khkl != null) {
                Arrays.sort(pickedRolls);
                if (khkl.equals("kh")) {
                    pickedRolls = Arrays.copyOfRange(pickedRolls, count - pickCount, count);
                } else {
                    pickedRolls = Arrays.copyOfRange(pickedRolls, 0, pickCount);
                }
            }

            int total = Arrays.stream(pickedRolls).sum() + modifier;
            return new DiceResult(total, pickedRolls, modifier, expression);
        }
    }

}