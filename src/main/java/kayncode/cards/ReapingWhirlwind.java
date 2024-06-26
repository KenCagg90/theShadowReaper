package kayncode.cards;

import kayncode.powers.ReapPower;
import kayncode.util.Wiz;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static kayncode.KaynMod.makeID;

public class ReapingWhirlwind extends AbstractEasyCard {
    public final static String ID = makeID(ReapingWhirlwind.class.getSimpleName());

    public ReapingWhirlwind() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.magicNumber = this.baseMagicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = EnergyPanel.totalCount;

        for (int i = 0; i < effect; i++) {
            Wiz.forAllMonstersLiving(monster -> Wiz.applyToEnemy(monster, new ReapPower(monster, this.magicNumber)));
        }

        this.addToBot(new LoseEnergyAction(EnergyPanel.totalCount));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
