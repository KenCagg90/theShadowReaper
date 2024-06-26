package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class DesperateMeasures extends AbstractEasyCard {
    public final static String ID = makeID(DesperateMeasures.class.getSimpleName());

    public DesperateMeasures() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 6;
        this.magicNumber = this.baseMagicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        if (p.currentHealth < p.maxHealth / 2) {
            this.addToBot(new GainBlockAction(p, p, this.magicNumber));
        }
    }

    public void upp() {
        this.upgradeBlock(3); // Upgrade to gain 9 Block instead of 6
        this.upgradeMagicNumber(3); // Upgrade to gain an additional 9 Block instead of 6 if below 50% health
    }
}
