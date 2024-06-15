package code.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class Gank extends AbstractEasyCard {
    public final static String ID = makeID(Gank.class.getSimpleName());

    public Gank() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Deal 8(11) damage
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

        // If you've played 5 or more cards this turn, deal 8(11) damage again
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= 5) {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        }
    }

    @Override
    public void upp() {
        upgradeDamage(3); // Upgrade to deal 11 damage instead of 8
    }


}
