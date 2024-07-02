package kayncode.cards;

import kayncode.actions.ReapDamageAllEnemiesAction;
import kayncode.powers.ReapPower;
import kayncode.util.Wiz;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.actions.EasyXCostAction;

import static kayncode.KaynMod.makeID;

public class ReapingWhirlwind extends AbstractEasyCard {
    public final static String ID = makeID(ReapingWhirlwind.class.getSimpleName());

    public ReapingWhirlwind() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.magicNumber = this.baseMagicNumber = 4;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                Wiz.forAllMonstersLiving(monster -> Wiz.applyToEnemy(monster, new ReapPower(monster, this.magicNumber)));
            }
            // Add the ReapDamageAllEnemiesAction after applying all Reap powers
            Wiz.atb(new ReapDamageAllEnemiesAction(p));
            return true;
        }));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}