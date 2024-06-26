package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static kayncode.KaynMod.makeID;

public class SwiftDodge extends AbstractEasyCard {
    public final static String ID = makeID(SwiftDodge.class.getSimpleName());

    public SwiftDodge() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 4;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
            this.addToBot(new GainBlockAction(p, p, this.block + this.magicNumber));
        } else {
            this.addToBot(new GainBlockAction(p, p, this.block));
        }
    }

    public void upp() {
        this.upgradeBlock(1); // Upgrade to gain 5 Block instead of 4
        this.upgradeMagicNumber(2); // Upgrade to gain an additional 5 Block instead of 3 if it's the first card played
    }
}
