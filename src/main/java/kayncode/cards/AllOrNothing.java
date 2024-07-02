package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import kayncode.actions.LoseEnergyNextTurnAction;

import static kayncode.KaynMod.makeID;

public class AllOrNothing extends AbstractEasyCard {
    public final static String ID = makeID(AllOrNothing.class.getSimpleName());

    public AllOrNothing() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new LoseEnergyNextTurnAction(2));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}
