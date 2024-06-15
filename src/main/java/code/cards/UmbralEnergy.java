package code.cards;

import code.powers.DarkinRagePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static code.ModFile.makeID;

public class UmbralEnergy extends AbstractEasyCard {
    public final static String ID = makeID(UmbralEnergy.class.getSimpleName());

    public UmbralEnergy() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 2;
        baseBlock = 5;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new EnergizedPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}