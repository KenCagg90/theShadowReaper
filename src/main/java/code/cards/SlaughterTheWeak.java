package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class SlaughterTheWeak extends AbstractEasyCard {
    public final static String ID = makeID(SlaughterTheWeak.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public SlaughterTheWeak() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 5;
        isMultiDamage = true;
        this.baseMagicNumber = this.magicNumber = 10;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        int[] damageArray = new int[AbstractDungeon.getCurrRoom().monsters.monsters.size()];

        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            int damageToDeal = this.damage;

            if (mo.currentHealth < (mo.maxHealth / 2.0)) {
                damageToDeal *= 2;
            }

            damageArray[i] = damageToDeal;
        }

        this.addToBot(new DamageAllEnemiesAction(p, damageArray, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(6);

    }
}