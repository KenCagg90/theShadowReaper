package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import kayncode.util.Wiz;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static kayncode.KaynMod.makeID;

public class AdaptiveStrategy extends AbstractEasyCard {
    public final static String ID = makeID(AdaptiveStrategy.class.getSimpleName());

    public AdaptiveStrategy() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 6;
        this.baseMagicNumber = this.magicNumber = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().noneMatch(card -> card.type == CardType.ATTACK)) {
            this.addToBot(new GainBlockAction(p, this.block));
            Wiz.applyToSelf(new VigorPower(p, this.magicNumber));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream()
                .noneMatch(card -> card.type == CardType.ATTACK)
                ? AbstractCard.GOLD_BORDER_GLOW_COLOR
                : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(3);
    }
}
