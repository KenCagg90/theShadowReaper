package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.powers.ReapPower;

import static kayncode.KaynMod.makeID;

public class Cull extends AbstractEasyCard {
    public final static String ID = makeID(Cull.class.getSimpleName());

    public Cull() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Deal 5(7) damage to all enemies
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        // Trigger Half Reap on the specified enemy
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (m != null && m.hasPower(ReapPower.ID)) {
                    ((ReapPower)m.getPower(ReapPower.ID)).triggerReap(0.5F);
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        this.upgradeDamage(2); // Upgrade to increase the base damage
    }
}