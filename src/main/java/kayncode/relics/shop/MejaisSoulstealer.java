package kayncode.relics.shop;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import kayncode.TheShadowReaper;
import kayncode.patches.KaynTransformCardsPatch;
import kayncode.relics.AbstractEasyRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;

import static kayncode.KaynMod.makeID;

public class MejaisSoulstealer extends AbstractEasyRelic {
    public static final String ID = makeID("MejaisSoulstealer");
    private static final int INITIAL_STRENGTH = 1;
    private static final int MAX_STRENGTH = 10;
    private int strengthBonus = INITIAL_STRENGTH;
    private boolean failed;

    public MejaisSoulstealer() {
        super(ID, RelicTier.SHOP, LandingSound.MAGICAL, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
    }

    @Override
    public void atBattleStart() {
        if (isEliteOrBossRoom()) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, strengthBonus), strengthBonus));
            this.grayscale = false;
            this.failed = false;
        }
    }

    @Override
    public void onLoseHp(int damageAmount) {
        this.grayscale = true;
        this.failed = true;
    }


    @Override
    public void onEquip() {
        this.counter = 1;
        this.grayscale = true;
    }

    @Override
    public void onVictory() {
        if (isEliteOrBossRoom() && !failed) {
            if (strengthBonus < MAX_STRENGTH) {
                strengthBonus++;
                counter++;
                this.flash();
            }
        }
    }

    private boolean isEliteOrBossRoom() {
        AbstractRoom room = AbstractDungeon.getCurrRoom();
        return room instanceof MonsterRoomElite || room instanceof MonsterRoomBoss;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + INITIAL_STRENGTH + DESCRIPTIONS[1] + MAX_STRENGTH + DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MejaisSoulstealer();
    }
}
