package kayncode.relics.uncommon;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import kayncode.TheShadowReaper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import kayncode.relics.AbstractEasyRelic;

import static kayncode.KaynMod.makeID;

public class Hubris extends AbstractEasyRelic {
    public static final String ID = makeID("Hubris");

    public Hubris() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {

        this.flash();
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 5)));
        addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, 5)));
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Hubris();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}