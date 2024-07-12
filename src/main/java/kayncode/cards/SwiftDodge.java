package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static kayncode.KaynMod.makeID;

public class SwiftDodge extends AbstractEasyCard {
    public final static String ID = makeID(SwiftDodge.class.getSimpleName());

    public SwiftDodge() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 4;
        this.baseSecondBlock = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() <= 1) {
            blck();
            altBlck();
        } else {
            blck();

        }
    }

    public void upp() {
        this.upgradeBlock(1); // Upgrade to gain 5 Block instead of 4
        this.upgradeSecondBlock(2); // Upgrade to gain an additional 5 Block instead of 3 if it's the first card played
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}