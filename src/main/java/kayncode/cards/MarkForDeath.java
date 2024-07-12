package kayncode.cards;

import kayncode.powers.ReapPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static kayncode.KaynMod.makeID;

public class MarkForDeath extends AbstractEasyCard {
    public final static String ID = makeID(MarkForDeath.class.getSimpleName());

    public MarkForDeath() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = 7;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new ReapPower(m, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, 1), 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(3);
    }
}