package code.relics;

import code.CharacterFile;
import code.patches.KaynTransformCardsPatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.DamageInfo;

import static code.ModFile.makeID;

public class ShadowAssassin extends AbstractEasyRelic {
    public static final String ID = makeID("ShadowAssassin");
    private static final int DAMAGE_THRESHOLD = 25;
    private int damageCounter = 0;


    public ShadowAssassin() {
        super(ID, RelicTier.SPECIAL, LandingSound.CLINK, CharacterFile.Enums.SHADOWREAPER_COLOR);

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
        if (AbstractDungeon.player instanceof CharacterFile) {
            ((CharacterFile) AbstractDungeon.player).resetToDefaultAppearance();
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (info.owner == AbstractDungeon.player && damageAmount > 0 && info.type != DamageInfo.DamageType.HP_LOSS) {
            damageCounter += damageAmount;
            this.counter = damageCounter; // Update the counter to reflect accumulated damage
            if (damageCounter >= DAMAGE_THRESHOLD) {
                int cardsToDraw = damageCounter / DAMAGE_THRESHOLD;
                damageCounter = damageCounter % DAMAGE_THRESHOLD;
                this.flash();
                this.addToBot(new DrawCardAction(AbstractDungeon.player, cardsToDraw));
                this.counter = damageCounter; // Update the counter after drawing cards
            }
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
