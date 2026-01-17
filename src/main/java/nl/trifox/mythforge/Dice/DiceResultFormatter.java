package nl.trifox.mythforge.Dice;

import nl.trifox.mythforge.Configs.TextConfig;

import java.util.Arrays;

public class DiceResultFormatter {
    public static String Format(String input, DiceResult result) {
        return input.replace(TextConfig.DiceTotalMacro, String.valueOf(result.total()))
                .replace(TextConfig.DicesMacro, Arrays.toString(result.rolls()))
                .replace(TextConfig.DiceInputMacro, result.expression());
    }
}
