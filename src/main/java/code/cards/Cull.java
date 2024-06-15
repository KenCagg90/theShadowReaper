package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import code.actions.ReapDamageAllEnemiesAction;

import static code.ModFile.makeID;

public class Cull extends AbstractEasyCard {
    public final static String ID = makeID(Cull.class.getSimpleName());

    public Cull() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 5;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Deal 5 damage to all enemies
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        // Deal damage to all enemies equal to their Reap amount
        this.addToBot(new ReapDamageAllEnemiesAction(p));
    }

    @Override
    public void upp() {
        this.upgradeDamage(2); // Upgrade to increase the base damage
    }

}
