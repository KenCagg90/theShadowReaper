package kayncode.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import kayncode.util.TexLoader;

import static kayncode.KaynMod.makeRelicPath;
import static kayncode.KaynMod.modID;

public abstract class AbstractEasyRelic extends CustomRelic {
    public AbstractCard.CardColor color;

    public AbstractEasyRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx) {
        this(setId, tier, sfx, null);
    }

    public AbstractEasyRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, AbstractCard.CardColor color) {
        super(setId, TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + ".png")), tier, sfx);
        outlineImg = TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + "Outline.png"));
        this.color = color;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void renderDoubleCounter(SpriteBatch sb, boolean inTopPanel, int redCounter, int blueCounter) {
        if (redCounter > -1 || blueCounter > -1) {
            if (inTopPanel) {
                if (redCounter > -1) {
                    FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(redCounter), this.currentX - 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, new Color(0.98F, 0.40F, 0.39F, 1));
                }
                if (blueCounter > -1) {
                    FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(blueCounter), this.currentX + 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, new Color(0.53F, 0.81F, 0.92F, 1));
                }
            } else {
                if (redCounter > -1) {
                    FontHelper.renderFontLeftTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(redCounter), this.currentX - 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, new Color(0.98F, 0.40F, 0.39F, 1));
                }
                if (blueCounter > -1) {
                    FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(blueCounter), this.currentX + 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, new Color(0.53F, 0.81F, 0.92F, 1));
                }
            }
        }
    }

}

