package kayncode.cards;

import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
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
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new StrategicManeuverAction(this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);


        }
    }

