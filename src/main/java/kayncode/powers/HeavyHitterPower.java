package kayncode.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;



    public class HeavyHitterPower extends AbstractEasyPower {
        public static String ID = makeID(HeavyHitterPower.class.getSimpleName());
        private boolean hasTriggeredThisTurn = false;
        
        private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
        private AbstractCreature source;

        public HeavyHitterPower(AbstractCreature owner, int amount) {
            super(ID, powerStrings.NAME, PowerType.BUFF, false, owner, amount);
        }

    @Override
    public void atStartOfTurn() {
        hasTriggeredThisTurn = false;
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (!hasTriggeredThisTurn && card.costForTurn >= 2) {
            this.flash();
            this.addToBot(new GainEnergyAction(this.amount));
            hasTriggeredThisTurn = true;
        }
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }
}