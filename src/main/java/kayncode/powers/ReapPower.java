package kayncode.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static kayncode.KaynMod.makeID;

public class ReapPower extends AbstractEasyPower {
    public static final String POWER_ID = "Reap";
    public static String ID = makeID(ReapPower.class.getSimpleName());

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    private AbstractCreature source;

    public ReapPower(AbstractMonster target, int amount) {
        super(ID, powerStrings.NAME, PowerType.DEBUFF, false, target, amount);

    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_POISON", 0.05F);
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()){ //&& EnergyPanel.totalCount > 0 {
            this.flash();
            addToBot(new SFXAction("REAP"));
            AbstractDungeon.effectList.add(new kayncode.vfx.ReapEffect(this.owner.hb.cX, this.owner.hb.cY, AttackEffect.NONE, false));
            this.addToBot(new LoseHPAction(this.owner, AbstractDungeon.player, this.amount, AttackEffect.NONE));
        }
    }

    @Override
    public void updateDescription() {
        description = powerStrings.DESCRIPTIONS[0];
    }
}