package kayncode.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import kayncode.powers.ReapPower;
import kayncode.relics.special.BaseForm;
import kayncode.relics.special.ShadowAssassin;
import kayncode.relics.special.TheDarkinScythe;
import kayncode.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class ReapingSlashRhaast extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(ReapingSlashRhaast.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    public ReapingSlashRhaast() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 5;
        baseBlock = 6;
        setBackgroundTexture(makeImagePath("512/attackRhaast.png"), makeImagePath("1024/attackRhaast.png"));
        setOrbTexture(makeImagePath("512/energyRhaast.png"), makeImagePath("1024/energyRhaast.png"));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        this.addToBot(new SFXAction("REAPING_SLASH"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        Wiz.forAllMonstersLiving(monster -> Wiz.applyToEnemy(monster, new ReapPower(monster, this.magicNumber)));
        blck();
    }


    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
        upgradeBlock(2);
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