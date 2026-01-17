package nl.calvindavidson.mythforge.Commands.Dice;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import nl.calvindavidson.mythforge.Dice.DiceParser;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Objects;

public class RollCommand extends CommandBase {

    public RequiredArg<String> Roll;
    public boolean IsForDM;

    public RollCommand(boolean isForDM, String requiredPermission) {
        super("roll", "Rolls the dice");
        this.Roll = this.withRequiredArg("role properties", "provide your roll e.g. 1d20", ArgTypes.STRING);
        this.IsForDM = isForDM;
        this.requirePermission(requiredPermission);
    }

    @Override
    protected void executeSync(@Nonnull CommandContext ctx) {
        var parseResult = DiceParser.roll(ctx.get(Roll));
        CommandSender sender = ctx.sender();

        if (sender instanceof Player player) {
            if (this.IsForDM) {
                sender.sendMessage(Message.raw(
                        "You rolled " + ctx.get(Roll) + " → " +
                                Arrays.toString(parseResult.rolls()) +
                                " (Total: " + parseResult.total() + ")"
                ));
            } else {
                var players = player.getReference().getStore().getExternalData().getWorld().getPlayerRefs();

                players.forEach(playerRef -> {
                    if (Objects.requireNonNull(playerRef.getReference()).equals(player.getReference())) {
                        playerRef.sendMessage(Message.raw(
                                "You rolled " + ctx.get(Roll) + " → " +
                                        Arrays.toString(parseResult.rolls()) +
                                        " (Total: " + parseResult.total() + ")"
                        ));
                    } else {
                        playerRef.sendMessage(Message.raw(
                                sender.getDisplayName() + " rolled " + ctx.get(Roll) + " → " +
                                        Arrays.toString(parseResult.rolls()) +
                                        " (Total: " + parseResult.total() + ")"
                        ));
                    }
                });

            }
        }



    }
}