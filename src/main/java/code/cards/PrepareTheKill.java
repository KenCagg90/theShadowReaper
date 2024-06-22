package code.cards;

import code.powers.ReapPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static code.ModFile.makeID;

public class PrepareTheKill extends AbstractEasyCard {
    public final static String ID = makeID(PrepareTheKill.class.getSimpleName());

    public PrepareTheKill() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 5;
        this.exhaust = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(4);
    }
}