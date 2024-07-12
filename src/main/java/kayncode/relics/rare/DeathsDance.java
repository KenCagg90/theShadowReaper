package kayncode.relics.rare;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import kayncode.TheShadowReaper;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import kayncode.relics.AbstractEasyRelic;

import static kayncode.KaynMod.makeID;

public class DeathsDance extends AbstractEasyRelic {
    public static final String ID = makeID("DeathsDance");
    public int damageStored;
    public static boolean turnEnded;

    public DeathsDance() {
        super(ID, RelicTier.RARE, LandingSound.CLINK, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
        damageStored = 0;
        this.counter = damageStored;
        turnEnded = false;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner.isPlayer || turnEnded)
            return damageAmount;

        int damageReduced = (int)(damageAmount / 3.f);
        damageStored += damageReduced;
        this.counter = damageStored;
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        return damageAmount - damageReduced;
    }

    @Override
    public void onPlayerEndTurn() {
        if (damageStored == 0)
            return;

        int damageReceived = (int)Math.ceil(damageStored / 4.f); // For some reason can't be done in one line
        damageStored -= damageReceived;
        this.counter = damageStored;

        turnEnded = true;
        AbstractDungeon.player.damage(new DamageInfo(AbstractDungeon.player, damageReceived, DamageInfo.DamageType.HP_LOSS));
        turnEnded = false;
    }

    @Override
    public void onVictory() {
        damageStored = 0;
        this.counter = damageStored;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new DeathsDance();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
