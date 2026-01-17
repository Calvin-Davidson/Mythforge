package nl.trifox.mythforge.Configs;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class TextConfig {

    public static String DiceInputMacro = "[DiceInput]";
    public static String DicesMacro = "[Dices]";
    public static String DiceTotalMacro = "[DiceTotal]";

    public static final BuilderCodec<TextConfig> CODEC =
            BuilderCodec.builder(TextConfig.class, TextConfig::new)
                    .append(new KeyedCodec<String>("DMDiceRollText", Codec.STRING),
                            (exConfig, aDouble, extraInfo) -> exConfig.DMDiceRollText = aDouble,  // Setter
                            (exConfig, extraInfo) -> exConfig.DMDiceRollText)                     // Getter
                    .add()
                    .append(new KeyedCodec<String>("DiceRollOthersText", Codec.STRING),
                            (textConfig, s, extraINfo) -> textConfig.DiceRollOthersText = s,
                            (textConfig, extraInfo) -> textConfig.DiceRollOthersText)
                    .add()
                    .append(new KeyedCodec<String>("DiceRollSelfText", Codec.STRING),
                            (textConfig, s, extraINfo) -> textConfig.DiceRollSelfText = s,
                            (textConfig, extraInfo) -> textConfig.DiceRollSelfText)
                    .add()
                    .build();

    public String DMDiceRollText =
            String.format(
                    "You DM rolled %s  →  %s  →  (Total: %s)",
                    TextConfig.DiceInputMacro,
                    TextConfig.DicesMacro,
                    TextConfig.DiceTotalMacro
            );

    public String DiceRollOthersText =
            String.format(
                    "Another player rolled %s  →  %s  →  (Total: %s)",
                    TextConfig.DiceInputMacro,
                    TextConfig.DicesMacro,
                    TextConfig.DiceTotalMacro
            );

    public String DiceRollSelfText =
            String.format(
                    "You rolled %s  →  %s  →  (Total: %s)",
                    TextConfig.DiceInputMacro,
                    TextConfig.DicesMacro,
                    TextConfig.DiceTotalMacro
            );

    public TextConfig() {

    }
}
