package kayncode.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import kayncode.powers.ReapPower;
import kayncode.relics.special.BaseForm;
import kayncode.relics.special.Rhaast;
import kayncode.relics.special.TheDarkinScythe;
import kayncode.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static kayncode.KaynMod.makeID;
import static kayncode.KaynMod.makeImagePath;

public class ReapingSlashAssassin extends AbstractEasyCard implements SpawnModificationCard {
    public final static String ID = makeID(ReapingSlashAssassin.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    public ReapingSlashAssassin() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 5;
        baseSecondMagic = 5;
        setBackgroundTexture(makeImagePath("512/attackAssassin.png"), makeImagePath("1024/attackAssassin.png"));
        setOrbTexture(makeImagePath("512/energyAssassin.png"), makeImagePath("1024/energyAssassin.png"));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        this.addToBot(new SFXAction("REAPING_SLASH"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        Wiz.forAllMonstersLiving(monster -> Wiz.applyToEnemy(monster, new ReapPower(monster, this.magicNumber)));
        this.addToBot(new LoseHPAction(m, AbstractDungeon.player, baseSecondMagic, AbstractGameAction.AttackEffect.NONE));
        }




    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
        upgradeSecondMagic(1);
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
