package code.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static code.ModFile.makeID;

public class PerfectFormPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("PerfectFormPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private int attackCounter = 0;
    private int skillCounter = 0;

    public PerfectFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, powerStrings.NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            attackCounter++;
            if (attackCounter == 3) {
                this.flash();
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount), this.amount));
                attackCounter = 0;
            }
        } else if (card.type == AbstractCard.CardType.SKILL) {
            skillCounter++;
            if (skillCounter == 4) {
                this.flash();
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
                skillCounter = 0;
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }
}
