package kayncode.relics.rare;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.TheShadowReaper;
import kayncode.relics.AbstractEasyRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;


import static kayncode.KaynMod.makeID;

public class Eclipse extends AbstractEasyRelic {
    public static final String ID = makeID("Eclipse");
    private static final int TEMP_HP_AMOUNT = 14;
    private boolean triggered = false;
    private int attackCount = 0;

    public Eclipse() {
        super(ID, RelicTier.RARE, LandingSound.HEAVY, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void atBattleStart() {
        triggered = false;
        attackCount = 0;
        this.grayscale = false;
        this.counter = 0;
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (!triggered && card.type == AbstractCard.CardType.ATTACK) {
            attackCount++;
            counter++;
            if (attackCount == 5) {
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, TEMP_HP_AMOUNT));
                AbstractDungeon.effectList.add(new BorderFlashEffect(com.badlogic.gdx.graphics.Color.GOLD, true)); // Flash effect
                triggered = true;
                this.grayscale = true;
            }
        }
    }

    @Override
    public void onVictory() {
        triggered = false; // Reset at the end of combat
        this.grayscale = false;
        this.counter = -1;
    }

    @Override
    public void atTurnStart() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            attackCount = 0; // Reset attack count at the start of each turn
            counter = 0;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + TEMP_HP_AMOUNT + DESCRIPTIONS[1];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Eclipse();
    }
}
