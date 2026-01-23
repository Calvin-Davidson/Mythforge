package nl.trifox.mythforge.Dice;

import com.hypixel.hytale.server.core.Message;
import nl.trifox.mythforge.Configs.TextConfig;
import nl.trifox.mythforge.Consts.Macro;

import java.util.Arrays;

public class DiceResultFormatter {
    public static Message Format(Message message, DiceResult result) {
        return message.param(Macro.DiceTotalMacro, String.valueOf(result.total()))
                .param(Macro.DicesMacro, Arrays.toString(result.rolls()))
                .param(Macro.DiceInputMacro, result.expression());
    }
}
