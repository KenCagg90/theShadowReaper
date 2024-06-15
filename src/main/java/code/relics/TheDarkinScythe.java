package code.relics;

import code.CharacterFile;

import static code.ModFile.makeID;

public class TheDarkinScythe extends AbstractEasyRelic {
    public static final String ID = makeID("TheDarkinScythe");

    public TheDarkinScythe() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, CharacterFile.Enums.TODO_COLOR);
    }
}
