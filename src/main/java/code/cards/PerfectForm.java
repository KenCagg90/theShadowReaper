package code.cards;

import code.powers.PerfectFormPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class PerfectForm extends AbstractEasyCard {
    public final static String ID = makeID(PerfectForm.class.getSimpleName());

    public PerfectForm() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LoseHPAction(p, p, this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new PerfectFormPower(p, 1), 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(-1);
    }
}
