package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class SlaughterTheWeak extends AbstractEasyCard {
    public final static String ID = makeID(SlaughterTheWeak.class.getSimpleName());

    public SlaughterTheWeak() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ALL_ENEMY);
        baseDamage = 6;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // First hit for all enemies
        this.addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        // Second hit for enemies with 40% or less health
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped() && mo.currentHealth <= mo.maxHealth * 0.40) {
                this.addToBot(new DamageAction(mo, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean enemyLowHealth = false;
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped() && mo.currentHealth <= mo.maxHealth * 0.40) {
                enemyLowHealth = true;
                break;
            }
        }

        this.glowColor = enemyLowHealth ? AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy() : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

}
