package kayncode.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import kayncode.TheShadowReaper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static kayncode.KaynMod.makeID;

public class SpearOfShojin extends AbstractEasyRelic {
    public static final String ID = makeID("SpearOfShojin");

    public SpearOfShojin() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK, TheShadowReaper.Enums.SHADOWREAPER_COLOR);
        this.counter = 0;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (++(this.counter) == 5) {

            ArrayList<AbstractCard> moreThanZeroCost = new ArrayList<>(AbstractDungeon.player.hand.group.stream().filter(abstractCard -> {return abstractCard.cost > 0;}).collect(Collectors.toList()));
            if (moreThanZeroCost.size() > 0)
                moreThanZeroCost.get((int)(Math.random() * moreThanZeroCost.size())).costForTurn--;
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));

            this.counter = 0;
        }
    }

    @Override
    public void onVictory() {
        this.counter = 0;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SpearOfShojin();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}