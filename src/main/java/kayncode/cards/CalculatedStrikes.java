package kayncode.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class CalculatedStrikes extends AbstractEasyCard {
    public final static String ID = makeID(CalculatedStrikes.class.getSimpleName());

    public CalculatedStrikes() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 13;
        this.selfRetain = true;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        if (this.cost > 0) {
            this.modifyCostForCombat(-1);
        }
    }


    @Override
    public void upp() {
        this.upgradeDamage(5); // Upgrade to gain 10 Block instead of 6
    }
}
