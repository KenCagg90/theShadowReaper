package kayncode.relics.shop;

import kayncode.TheShadowReaper;
import kayncode.relics.AbstractEasyRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static kayncode.KaynMod.makeID;

public class Predator extends AbstractEasyRelic {
    public static final String ID = makeID("Predator");
    private boolean triggeredThisCombat = false;

    public Predator() {
        super(ID, RelicTier.SHOP, LandingSound.CLINK, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void atBattleStart() {
        this.triggeredThisCombat = false;
        this.grayscale = false;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        int energySpent = c.costForTurn;

        // Check for X-cost card
        if (c.costForTurn == -1) {
            energySpent = EnergyPanel.totalCount;
        }

        if (!this.triggeredThisCombat && energySpent >= 2) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
            this.triggeredThisCombat = true;
            this.grayscale = true;
        }
    }

    @Override
    public void onVictory() {
        this.triggeredThisCombat = false;
        this.grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Predator();
    }
}
