package kayncode.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.relics.BaseForm;
import kayncode.relics.Rhaast;
import kayncode.relics.TheDarkinScythe;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class ShadowSightAssassin extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(ShadowSightAssassin.class.getSimpleName());
    private static int totalPlaysThisTurn = 0;

    public ShadowSightAssassin() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        selfRetain = true;
        this.baseMagicNumber = this.magicNumber = 1;
        setBackgroundTexture(makeImagePath("512/attackAssassin.png"), makeImagePath("1024/attackAssassin.png"));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(this.magicNumber));
        totalPlaysThisTurn++;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (totalPlaysThisTurn >= 2) {
            return false;
        }
        return super.canUse(p, m);
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        totalPlaysThisTurn = 0; // Reset the counter at the start of each turn
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(Rhaast.ID) || AbstractDungeon.player.hasRelic(BaseForm.ID) || AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean canSpawnShop(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(Rhaast.ID) || AbstractDungeon.player.hasRelic(BaseForm.ID) || AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }
}
