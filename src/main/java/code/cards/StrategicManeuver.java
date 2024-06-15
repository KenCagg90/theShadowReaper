package code.cards;

import code.actions.StrategicManeuverAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static code.ModFile.makeID;

public class StrategicManeuver extends AbstractEasyCard {
    public final static String ID = makeID(StrategicManeuver.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public StrategicManeuver() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new StrategicManeuverAction());
    }

    @Override
    public void upp() {
        this.upgradeBaseCost(1);
    }
}
