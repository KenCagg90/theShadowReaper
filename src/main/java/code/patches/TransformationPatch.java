package code.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import code.CharacterFile;
import code.relics.Rhaast;
import code.relics.ShadowAssassin;

@SpirePatch(clz = AbstractDungeon.class, method = "nextRoomTransition", paramtypez = {})
public class TransformationPatch {

    @SpirePrefixPatch
    public static void applyTransformation() {
        if (AbstractDungeon.player instanceof CharacterFile) {
            CharacterFile player = (CharacterFile) AbstractDungeon.player;

            if (player.hasRelic(Rhaast.ID) && !player.isTransformedToRhaast()) {
                player.transformToRhaast();
            } else if (player.hasRelic(ShadowAssassin.ID) && !player.isTransformedToAssassin()) {
                player.transformToAssassin();
            }
        }
    }
}
