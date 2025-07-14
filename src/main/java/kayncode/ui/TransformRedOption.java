package kayncode.ui;

import kayncode.relics.BaseForm;
import kayncode.relics.Rhaast;
import kayncode.relics.TheDarkinScythe;
import kayncode.vfx.TransformEffect;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import kayncode.TheShadowReaper;

import static kayncode.KaynMod.makeImagePath;

public class TransformRedOption extends AbstractCampfireOption {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TransformRedOption");
    private static final Texture ENABLED_IMG = ImageMaster.loadImage(makeImagePath("ui/transformredoption.png"));
    private static final Texture DISABLED_IMG = ImageMaster.loadImage(makeImagePath("ui/transformredoptiondisabled.png"));

    public TransformRedOption() {
        this.label = uiStrings.TEXT[0]; // Example label: "Transform into Rhaast"
        this.description = uiStrings.TEXT[1]; // Example description

        if (AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) && TheDarkinScythe.redOrbs >= 8) {
            this.img = ENABLED_IMG;
            this.usable = true;
        } else {
            this.img = DISABLED_IMG;
            this.usable = false;
        }
    }

    @Override
    public void useOption() {
        if (!this.usable) return;

        AbstractDungeon.player.loseRelic(TheDarkinScythe.ID);
        AbstractDungeon.player.loseRelic(BaseForm.ID);
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.hb.cX, this.hb.cY, new Rhaast());
        if (AbstractDungeon.player instanceof TheShadowReaper) {
            TheShadowReaper player = (TheShadowReaper) AbstractDungeon.player;
            player.transformToRhaast();
        }

        AbstractDungeon.effectList.add(new TransformEffect());
        this.usable = false;
    }

    @Override
    public void update() {
        super.update();
        if (!AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) || TheDarkinScythe.redOrbs < 1) {
            this.usable = false;
            this.img = DISABLED_IMG;
            this.description = uiStrings.TEXT[1];
        }
    }
}
