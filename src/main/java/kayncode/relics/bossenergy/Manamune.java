package kayncode.relics.bossenergy;

import kayncode.TheShadowReaper;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import kayncode.relics.AbstractEasyRelic;

import static kayncode.KaynMod.makeID;

public class Manamune extends AbstractEasyRelic {
    public static final String ID = makeID("Manamune");
    private static final int ATTACKS_NEEDED = 3;
    private boolean triggeredThisTurn = false;

    public Manamune() {
        super(ID, RelicTier.BOSS, LandingSound.SOLID, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void atTurnStart() {
        this.counter = 0;
        triggeredThisTurn = false;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && !triggeredThisTurn) {
            ++this.counter;
            if (this.counter >= ATTACKS_NEEDED) {
                this.counter = 0;
                triggeredThisTurn = true;
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new GainEnergyAction(1));
            }
        }
    }

    @Override
    public void onVictory() {
        this.counter = -1; // Reset the counter at the end of combat
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Manamune();
    }
}