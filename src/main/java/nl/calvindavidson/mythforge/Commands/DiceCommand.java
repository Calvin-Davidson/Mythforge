package nl.calvindavidson.mythforge.Commands;

import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import nl.calvindavidson.mythforge.Dice.DiceParser;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class DiceCommand extends CommandBase {

    public DiceCommand() {
        super("roll", "Rolls the dice");
        this.setPermissionGroup(GameMode.Adventure); 
        this.setAllowsExtraArguments(true);
    }

    @Override
    protected void executeSync(@Nonnull CommandContext ctx) {
        assert this.getName() != null;

        var input = ctx.getInputString().replaceFirst(this.getName(), "");
        var parseResult = DiceParser.roll(input);

        ctx.sendMessage(Message.raw(
                "You rolled " + input + " → " +
                        Arrays.toString(parseResult.rolls()) +
                        " (Total: " + parseResult.total() + ")"
        ));
    }
}