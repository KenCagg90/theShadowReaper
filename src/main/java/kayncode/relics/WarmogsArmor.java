package kayncode.relics;

import kayncode.TheShadowReaper;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static kayncode.KaynMod.makeID;

public class WarmogsArmor extends AbstractEasyRelic {
    public static final String ID = makeID("WarmogsArmor");
    private static final int TURNS_WITHOUT_DAMAGE = 2;
    private static final float HEAL_PERCENTAGE = 0.05f;

    private boolean activatedThisTurn;

    public WarmogsArmor() {
        super(ID, RelicTier.RARE, LandingSound.HEAVY, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
        this.counter = 0;
        this.activatedThisTurn = false;
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
        this.activatedThisTurn = false;
        this.beginLongPulse();
    }

    @Override
    public void atTurnStart() {
        if (this.counter >= TURNS_WITHOUT_DAMAGE && !this.activatedThisTurn) {
            int healAmount = (int) (AbstractDungeon.player.maxHealth * HEAL_PERCENTAGE);
            if (healAmount > 0) {
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new HealAction(AbstractDungeon.player, AbstractDungeon.player, healAmount));
            }
        }
        this.activatedThisTurn = false;
        this.counter++;
        if (this.counter > TURNS_WITHOUT_DAMAGE) {
            this.counter = TURNS_WITHOUT_DAMAGE + 1;  // Cap the counter display
        }
    }

    @Override
    public void onLoseHp(int damageAmount) {
        if (damageAmount > 0) {
            this.counter = 0;
            this.activatedThisTurn = true;
            this.stopPulse();
        }
    }

    @Override
    public void onVictory() {
        this.counter = -1;  // Set to -1 to hide the counter outside of combat
        this.activatedThisTurn = false;
        this.stopPulse();
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        this.counter = -1;  // Set to -1 to hide the counter outside of combat
        this.activatedThisTurn = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0].replace("{0}", String.valueOf(TURNS_WITHOUT_DAMAGE))
                .replace("{1}", String.valueOf((int)(HEAL_PERCENTAGE * 100)));
    }
}