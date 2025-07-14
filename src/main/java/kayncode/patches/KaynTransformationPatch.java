package kayncode.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import kayncode.TheShadowReaper;
import kayncode.relics.Rhaast;
import kayncode.relics.ShadowAssassin;

@SpirePatch(clz = AbstractDungeon.class, method = "nextRoomTransition", paramtypez = {})
public class KaynTransformationPatch {

    @SpirePrefixPatch
    public static void applyTransformation() {
        if (AbstractDungeon.player instanceof TheShadowReaper) {
            TheShadowReaper player = (TheShadowReaper) AbstractDungeon.player;

            if (player.hasRelic(Rhaast.ID) && !player.isTransformedToRhaast()) {
                player.transformToRhaast();
            } else if (player.hasRelic(ShadowAssassin.ID) && !player.isTransformedToAssassin()) {
                player.transformToAssassin();
            }
        }
    }
}
