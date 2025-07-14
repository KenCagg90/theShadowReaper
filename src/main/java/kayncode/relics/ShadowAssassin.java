package kayncode.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnAfterUseCardRelic;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.ui.FtueTip;
import kayncode.TheShadowReaper;
import kayncode.patches.KaynTransformCardsPatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.DamageInfo;

import static kayncode.KaynMod.makeID;

public class ShadowAssassin extends AbstractEasyRelic implements OnAfterUseCardRelic {
    public static final String ID = makeID("ShadowAssassin");
    private static final int DAMAGE_THRESHOLD = 25;
    private int damageCounter = 0;


    public ShadowAssassin() {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK, TheShadowReaper.Enums.SHADOWREAPER_COLOR);

    }

    @Override
    public void onEquip() {
        CardCrawlGame.sound.play("ASSASSIN_TRANSFORM", 0.0F);
        KaynTransformCardsPatch.transformToAssassin();
    }


    public void onObtainCard(AbstractCard c) {
        KaynTransformCardsPatch.transformToAssassin();
    }


    @Override
    public void onUnequip() {
        if (AbstractDungeon.player instanceof TheShadowReaper) {
            ((TheShadowReaper) AbstractDungeon.player).resetToDefaultAppearance();
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {

        if (info.owner == AbstractDungeon.player && damageAmount > 0 && info.type == DamageInfo.DamageType.NORMAL) {
            damageCounter += damageAmount;
            this.counter = damageCounter; // Update the counter to reflect accumulated damage

            }
        }

    @Override
    public void onAfterUseCard(AbstractCard abstractCard, UseCardAction useCardAction) {
        if (damageCounter >= DAMAGE_THRESHOLD) {
            int cardsToDraw = damageCounter / DAMAGE_THRESHOLD;
            //if (cardsToDraw > 2) cardsToDraw = 2;
            damageCounter = damageCounter % DAMAGE_THRESHOLD;
            this.flash();
            this.addToBot(new DrawCardAction(AbstractDungeon.player, cardsToDraw));
            this.counter = damageCounter; // Update the counter after drawing cards
        }
    }

    @Override
    public void onVictory() {
        this.damageCounter = 0; // Reset the counter at the end of combat
        this.counter = -1;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }




}