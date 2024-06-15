package code.relics;

import code.CharacterFile;

import static code.ModFile.makeID;

public class Rhaast extends AbstractEasyRelic {
    public static final String ID = makeID("Rhaast");

    public Rhaast() {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK, CharacterFile.Enums.TODO_COLOR);
    }
}
