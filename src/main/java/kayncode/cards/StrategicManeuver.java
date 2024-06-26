package kayncode.cards;

import kayncode.actions.StrategicManeuverAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static kayncode.KaynMod.makeID;

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
