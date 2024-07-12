package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static kayncode.KaynMod.makeID;

public class ShadowAmbush extends AbstractEasyCard {
    public final static String ID = makeID(ShadowAmbush.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ShadowAmbush() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
        this.baseSecondDamage = this.secondDamage = 5;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

        boolean hasDebuff = false;
        for (AbstractPower power : m.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                hasDebuff = true;
                break;
            }
        }

        if (hasDebuff) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.secondDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeSecondDamage(2);
    }
}