package nl.trifox.mythforge.Commands.Character;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import nl.trifox.mythforge.Characters.CharacterData;
import nl.trifox.mythforge.Characters.PlayerCharacterService;
import nl.trifox.mythforge.Consts.CharacterField;
import nl.trifox.mythforge.Consts.Permissions;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static java.util.Map.entry;

public class SetCharacterCommand extends CommandBase {

    private final RequiredArg<String> attributeArg;
    private final RequiredArg<String> valueArg;
    private final PlayerCharacterService characterService;

    // Aliases -> field
    private static final Map<String, CharacterField> FIELD_ALIASES = Map.ofEntries(
            entry("str", CharacterField.STRENGTH),
            entry("strength", CharacterField.STRENGTH),

            entry("dex", CharacterField.DEXTERITY),
            entry("dexterity", CharacterField.DEXTERITY),

            entry("con", CharacterField.CONSTITUTION),
            entry("constitution", CharacterField.CONSTITUTION),

            entry("int", CharacterField.INTELLIGENCE),
            entry("intelligence", CharacterField.INTELLIGENCE),

            entry("wis", CharacterField.WISDOM),
            entry("wisdom", CharacterField.WISDOM),

            entry("cha", CharacterField.CHARISMA),
            entry("charisma", CharacterField.CHARISMA),

            entry("hp", CharacterField.CURRENT_HP),
            entry("currenthp", CharacterField.CURRENT_HP),
            entry("hpcur", CharacterField.CURRENT_HP),

            entry("maxhp", CharacterField.MAX_HP),
            entry("hpmax", CharacterField.MAX_HP),

            entry("ac", CharacterField.ARMOR_CLASS),
            entry("armorclass", CharacterField.ARMOR_CLASS),

            entry("init", CharacterField.INITIATIVE),
            entry("initiative", CharacterField.INITIATIVE),

            entry("speed", CharacterField.SPEED),

            entry("race", CharacterField.RACE),
            entry("class", CharacterField.CLASS),
            entry("characterclass", CharacterField.CLASS),

            entry("level", CharacterField.LEVEL)
    );

    public SetCharacterCommand(String name, String description, PlayerCharacterService characterService) {
        super(name, description);
        this.characterService = characterService;
        this.requirePermission(Permissions.CharacterSetStat);

        this.attributeArg = withRequiredArg("attribute", "What to set (str, dex, hp, ac, race, class, level...)", ArgTypes.STRING);
        this.valueArg = withRequiredArg("value", "New value", ArgTypes.STRING); // keep string so it works for race/class too
    }

    @Override
    protected void executeSync(CommandContext ctx) {
        var sender = ctx.sender();

        UUID playerId = sender.getUuid();
        CharacterData character = characterService.getActivePlayerCharacter(playerId);

        if (character == null) {
            sender.sendMessage(Message.raw("You have no active character. Use /char change <name> first."));
            return;
        }

        String key = attributeArg.get(ctx).trim().toLowerCase(Locale.ROOT);
        CharacterField field = FIELD_ALIASES.get(key);

        if (field == null) {
            sender.sendMessage(Message.raw("Unknown attribute '" + key + "'. Example: /char set str 15, /char set race Human, /char set ac 16"));
            return;
        }

        String rawValue = valueArg.get(ctx).trim();

        try {
            field.apply(character, rawValue);
        } catch (IllegalArgumentException ex) {
            sender.sendMessage(Message.raw(ex.getMessage()));
            return;
        }

        characterService.saveCharacter(character);
        sender.sendMessage(Message.raw("Set " + field.displayName + " to " + field.readableValue(character) + "."));
    }
}

