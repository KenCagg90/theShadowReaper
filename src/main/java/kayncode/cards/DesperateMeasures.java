package kayncode.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static kayncode.KaynMod.makeID;

public class DesperateMeasures extends AbstractEasyCard {
    public final static String ID = makeID(DesperateMeasures.class.getSimpleName());

    public DesperateMeasures() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (p.currentHealth <= p.maxHealth / 2) {
            blck();
        }
    }

    public void upp() {
        this.upgradeBlock(3); // Upgrade to gain 9 Block instead of 6
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player != null && AbstractDungeon.player.currentHealth <= AbstractDungeon.player.maxHealth / 2) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
