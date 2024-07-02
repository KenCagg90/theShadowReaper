package kayncode.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import kayncode.util.Wiz;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static kayncode.KaynMod.makeID;

public class GoldAdvantage extends AbstractEasyCard {
    public final static String ID = makeID(GoldAdvantage.class.getSimpleName());

    public GoldAdvantage() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int gold = AbstractDungeon.player.gold;
        int vigorAmount;

        if (gold <= 100) {
            vigorAmount = 8;
        } else if (gold <= 200) {
            vigorAmount = 12;
        } else if (gold <= 300) {
            vigorAmount = 16;
        } else {
            vigorAmount = 20;
        }

        Wiz.applyToSelf(new VigorPower(p, vigorAmount));
    }

    @Override
    public void upp() {
        this.upgradeBaseCost(1);
    }
}
