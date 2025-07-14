package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.powers.ReapPower;

import static kayncode.KaynMod.makeID;

public class Cull extends AbstractEasyCard {
    public final static String ID = makeID(Cull.class.getSimpleName());
    private static final float BASE_REAP_MULTIPLIER = 0.5F;
    private static final float UPGRADED_REAP_MULTIPLIER = 0.75F;
    private float reapMultiplier;

    public Cull() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.isMultiDamage = true;
        this.reapMultiplier = BASE_REAP_MULTIPLIER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Deal 5(7) damage to all enemies
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        // Trigger Half Reap on the specified enemy
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (m != null && m.hasPower(ReapPower.POWER_ID)) {
                    ((ReapPower)m.getPower(ReapPower.POWER_ID)).triggerReap(reapMultiplier);
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        this.upgradeDamage(2);
        this.reapMultiplier = UPGRADED_REAP_MULTIPLIER;// Upgrade to increase the base damage
    }
}