package nl.trifox.mythforge.Commands.Character;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import nl.trifox.mythforge.Characters.CharacterData;
import nl.trifox.mythforge.Characters.PlayerCharacterService;
import nl.trifox.mythforge.Consts.CharacterField;
import nl.trifox.mythforge.UI.CharacterSheetUI;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static java.util.Map.entry;

public class CharacterSheetCommand extends AbstractPlayerCommand {

    private final PlayerCharacterService characterService;



    public CharacterSheetCommand(String name, String description, PlayerCharacterService characterService) {
        super(name, description);
        this.characterService = characterService;
    }


    @Override
    protected void execute(@NonNullDecl CommandContext ctx, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        var player = store.getComponent(ref, Player.getComponentType());

        if (player == null) return;


        UUID playerId = playerRef.getUuid();
        CharacterData character = characterService.getActivePlayerCharacter(playerId);

        if (character == null) {
            player.sendMessage(Message.raw("You have no active character. Use /char change <name> first."));
            return;
        }

        var sheetUI = new CharacterSheetUI(playerRef, CustomPageLifetime.CanDismiss, character);
        player.getPageManager().openCustomPage(ref, store, sheetUI);
    }
}

