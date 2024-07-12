package kayncode.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import kayncode.powers.DemonArmorPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kayncode.relics.special.BaseForm;
import kayncode.relics.special.ShadowAssassin;
import kayncode.relics.special.TheDarkinScythe;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class DemonArmor extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(DemonArmor.class.getSimpleName());

    public DemonArmor() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 5;
        setBackgroundTexture(makeImagePath("512/attackRhaast.png"), makeImagePath("1024/attackRhaast.png"));
        setOrbTexture(makeImagePath("512/energyRhaast.png"), makeImagePath("1024/energyRhaast.png"));

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DemonArmorPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
    @Override
    public boolean canSpawn(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(ShadowAssassin.ID) || AbstractDungeon.player.hasRelic(BaseForm.ID) || AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }
    @Override
    public boolean canSpawnShop(ArrayList<AbstractCard> currentRewardCards) {
        if (AbstractDungeon.player.hasRelic(ShadowAssassin.ID) || AbstractDungeon.player.hasRelic(BaseForm.ID) || AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) ) {
            return false;
        }
        else {
            return true;
        }
    }
}