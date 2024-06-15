package code.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.ModFile.makeID;

public class ShadowSight extends AbstractEasyCard {
    public final static String ID = makeID(ShadowSight.class.getSimpleName());

    public ShadowSight() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(1));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().anyMatch(card -> card.cardID.equals(ID))) {
            return false;
        }
        return super.canUse(p, m);
    }

    @Override
    public void upp() {
        // No upgrades for now
    }
}