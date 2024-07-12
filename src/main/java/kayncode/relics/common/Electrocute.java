package kayncode.relics.common;

import kayncode.TheShadowReaper;
import kayncode.actions.ElectrocuteAction;
import kayncode.relics.AbstractEasyRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static kayncode.KaynMod.makeID;

public class Electrocute extends AbstractEasyRelic {
    public static final String ID = makeID("Electrocute");
    private static final int DAMAGE_AMOUNT = 6;
    private static final int ATTACKS_NEEDED = 3;
    private int attackCounter = 0;
    private boolean hasTriggeredThisTurn = false;

    public Electrocute() {
        super(ID, RelicTier.COMMON, LandingSound.MAGICAL, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + DAMAGE_AMOUNT + this.DESCRIPTIONS[1];
    }

    @Override
    public void atTurnStart() {
        this.attackCounter = 0;
        this.hasTriggeredThisTurn = false;
        this.grayscale = false;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && !this.hasTriggeredThisTurn) {
            ++this.attackCounter;

            if (this.attackCounter == ATTACKS_NEEDED) {
                triggerEffect(action.target);
            }
        }
    }

    private void triggerEffect(AbstractCreature target) {
        this.flash();
        this.attackCounter = 0;
        this.hasTriggeredThisTurn = true;
        this.grayscale = true;
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ElectrocuteAction(target, DAMAGE_AMOUNT));
    }

    @Override
    public void onVictory() {
        this.attackCounter = 0;
    }
}
