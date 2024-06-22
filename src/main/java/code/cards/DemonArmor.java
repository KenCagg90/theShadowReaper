package code.cards;

import code.powers.DemonArmorPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class DemonArmor extends AbstractEasyCard {
    public final static String ID = makeID(DemonArmor.class.getSimpleName());

    public DemonArmor() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 5;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DemonArmorPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}