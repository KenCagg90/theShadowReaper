package kayncode.patches;

import kayncode.cards.*;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.BottledFlame;
import com.megacrit.cardcrawl.relics.BottledLightning;
import com.megacrit.cardcrawl.relics.BottledTornado;

@SpirePatch(clz = AbstractDungeon.class, method = "nextRoomTransition", paramtypez = {})
public class KaynTransformCardsPatch {

    public static void transformToRhaast() {
        replaceCardsInDeckWithUpgradeCheck(BladesReach.ID, new BladesReachRhaast());
        replaceCardsInDeckWithUpgradeCheck(ReapingSlash.ID, new ReapingSlashRhaast());
        replaceCardsInDeckWithUpgradeCheck(Shadowstep.ID, new ShadowstepRhaast());
        replaceCardsInDeckWithUpgradeCheck(UmbralTrespass.ID, new UmbralTrespassRhaast());
        replaceCardsInDeckWithUpgradeCheck(FlurryOfBlades.ID, new FlurryOfBladesRhaast());
        replaceCardsInDeckWithUpgradeCheck(FrenziedStrike.ID, new FrenziedStrikeRhaast());
        replaceCardsInDeckWithUpgradeCheck(ShadowSight.ID, new ShadowSightRhaast());
        replaceCardsInDeckWithUpgradeCheck(MasterZedsTraining.ID, new DemonArmor());
    }

    public static void transformToAssassin() {
        replaceCardsInDeckWithUpgradeCheck(BladesReach.ID, new BladesReachAssassin());
        replaceCardsInDeckWithUpgradeCheck(ReapingSlash.ID, new ReapingSlashAssassin());
        replaceCardsInDeckWithUpgradeCheck(Shadowstep.ID, new ShadowstepAssassin());
        replaceCardsInDeckWithUpgradeCheck(UmbralTrespass.ID, new UmbralTrespassAssassin());
        replaceCardsInDeckWithUpgradeCheck(FlurryOfBlades.ID, new FlurryOfBladesAssassin());
        replaceCardsInDeckWithUpgradeCheck(FrenziedStrike.ID, new FrenziedStrikeAssassin());
        replaceCardsInDeckWithUpgradeCheck(ShadowSight.ID, new ShadowSightAssassin());
        replaceCardsInDeckWithUpgradeCheck(MasterZedsTraining.ID, new AssassinsTraining());
    }

    private static void replaceCardsInDeckWithUpgradeCheck(String targetCardId, AbstractCard replacementCard) {
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card.cardID.equals(targetCardId)) {
                AbstractCard newCard = replacementCard.makeCopy();
                if (card.upgraded) {
                    newCard.upgrade();
                }

                checkAndApplyBottledRelics(card, newCard);

                int index = AbstractDungeon.player.masterDeck.group.indexOf(card);
                AbstractDungeon.player.masterDeck.group.set(index, newCard);
            }
        }
    }

    private static void checkAndApplyBottledRelics(AbstractCard oldCard, AbstractCard newCard) {
        if (oldCard.inBottleFlame) {
            applyBottledRelic(newCard, BottledFlame.ID);
        }
        if (oldCard.inBottleLightning) {
            applyBottledRelic(newCard, BottledLightning.ID);
        }
        if (oldCard.inBottleTornado) {
            applyBottledRelic(newCard, BottledTornado.ID);
        }
    }

    private static void applyBottledRelic(AbstractCard newCard, String relicID) {
        switch (relicID) {
            case BottledFlame.ID:
                BottledFlame bottledFlame = (BottledFlame) AbstractDungeon.player.getRelic(BottledFlame.ID);
                if (bottledFlame != null) {
                    bottledFlame.card = newCard;
                    bottledFlame.setDescriptionAfterLoading();
                }
                newCard.inBottleFlame = true;
                break;
            case BottledLightning.ID:
                BottledLightning bottledLightning = (BottledLightning) AbstractDungeon.player.getRelic(BottledLightning.ID);
                if (bottledLightning != null) {
                    bottledLightning.card = newCard;
                    bottledLightning.setDescriptionAfterLoading();
                }
                newCard.inBottleLightning = true;
                break;
            case BottledTornado.ID:
                BottledTornado bottledTornado = (BottledTornado) AbstractDungeon.player.getRelic(BottledTornado.ID);
                if (bottledTornado != null) {
                    bottledTornado.card = newCard;
                    bottledTornado.setDescriptionAfterLoading();
                }
                newCard.inBottleTornado = true;
                break;
        }
    }
}
