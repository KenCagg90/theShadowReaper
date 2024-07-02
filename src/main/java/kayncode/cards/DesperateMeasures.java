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
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (p.currentHealth <= p.maxHealth / 2) {
        blck();
        }
    }

    public void upp() {
        this.upgradeBlock(3); // Upgrade to gain 9 Block instead of 6
    }
}
