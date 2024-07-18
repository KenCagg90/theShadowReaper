package kayncode.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static kayncode.KaynMod.makeID;

public class ReapPower extends AbstractEasyPower implements HealthBarRenderPower {
    public static final String POWER_ID = "Reap";
    public static String ID = makeID(ReapPower.class.getSimpleName());

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public ReapPower(AbstractMonster target, int amount) {
        super(ID, powerStrings.NAME, PowerType.DEBUFF, false, target, amount);
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_POISON", 0.05F);
    }

    public void atStartOfTurn() {
        float extraEnergyMult = (EnergyPanel.totalCount - 1) * 0.2F;
        float endOfTurnReapMult = 1.0F + extraEnergyMult;
        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead() && EnergyPanel.totalCount > 0) {
            triggerReap(endOfTurnReapMult);
        }
    }

    public void triggerReap(float multiplier) {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new SFXAction("REAP"));
        AbstractDungeon.effectList.add(new kayncode.vfx.ReapEffect(this.owner.hb.cX, this.owner.hb.cY, AttackEffect.NONE, false));
        int damageAmount = Math.round(this.amount * multiplier);
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.owner, AbstractDungeon.player, damageAmount, AttackEffect.NONE));
    }

    public static void triggerReapAll(float multiplier) {
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDead && !m.isDying && m.hasPower(ID)) {
                ((ReapPower)m.getPower(ID)).triggerReap(multiplier);
            }
        }
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        updateDescription();
    }

    @Override
    public void updateDescription() {
        float extraEnergyMult = Math.max(0, (EnergyPanel.totalCount - 1) * 0.2F);
        float endOfTurnReapMult = 1.0F + extraEnergyMult;
        int potentialDamage = Math.round(this.amount * endOfTurnReapMult);
        description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1] + potentialDamage + powerStrings.DESCRIPTIONS[2];
    }

    @Override
    public int getHealthBarAmount() {
        float extraEnergyMult = (EnergyPanel.totalCount - 1) * 0.2F;
        float endOfTurnReapMult = 1.0F + extraEnergyMult;
        return (int) (amount * endOfTurnReapMult);
    }

    @Override
    public Color getColor() {
        return new Color(0.290F, 0.0F, 0.451F, 1.0F);
    }
}