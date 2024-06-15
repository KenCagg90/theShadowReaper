package code.relics;

import code.CharacterFile;

import static code.ModFile.makeID;

public class ShadowAssassin extends AbstractEasyRelic {
    public static final String ID = makeID("ShadowAssassin");

    public ShadowAssassin() {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK, CharacterFile.Enums.TODO_COLOR);
    }
}
