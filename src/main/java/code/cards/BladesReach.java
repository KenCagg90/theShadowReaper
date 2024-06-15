package code.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;

import static code.ModFile.makeID;

public class BladesReach extends AbstractEasyCard {
    public final static String ID = makeID(BladesReach.class.getSimpleName());

    public BladesReach() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 10;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MonsterGroup monsters = AbstractDungeon.getMonsters();
        int count = 0;

        for (AbstractMonster monster : monsters.monsters) {
            if (monster.isDeadOrEscaped()) continue;

            this.addToBot(new DamageAction(monster, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            count++;
            if (count >= 2) break; // Only attack the first two alive monsters
        }
    }

    @Override
    public void upp() {
        upgradeDamage(5); // Upgrade to deal 15 damage instead of 10
    }

    @Override
    public AbstractEasyCard makeCopy() {
        return new BladesReach();
    }
}
