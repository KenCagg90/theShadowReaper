package kayncode.cards;

import kayncode.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.powers.ReapPower;

import static kayncode.KaynMod.makeID;

public class ReapAndTear extends AbstractEasyCard {
    public final static String ID = makeID(ReapAndTear.class.getSimpleName());

    public ReapAndTear() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 3;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        Wiz.forAllMonstersLiving(monster -> Wiz.applyToEnemy(monster, new ReapPower(monster, this.magicNumber)));
        }


    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}
