package code.ui;

import code.relics.BaseForm;
import code.relics.ShadowAssassin;
import code.relics.TheDarkinScythe;
import code.vfx.TransformEffect;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.vfx.campfire.CampfireSleepScreenCoverEffect;
import code.CharacterFile;

import static code.ModFile.makeImagePath;

public class TransformBlueOption extends AbstractCampfireOption {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TransformBlueOption");
    private static final Texture ENABLED_IMG = ImageMaster.loadImage(makeImagePath("ui/transformblueoption.png"));
    private static final Texture DISABLED_IMG = ImageMaster.loadImage(makeImagePath("ui/transformblueoptiondisabled.png"));

    public TransformBlueOption() {
        this.label = uiStrings.TEXT[0]; // Example label: "Transform into Shadow Assassin"
        this.description = uiStrings.TEXT[1]; // Example description

        if (AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) && TheDarkinScythe.blueOrbs >= 8) {
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
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.hb.cX, this.hb.cY, new ShadowAssassin());
        if (AbstractDungeon.player instanceof CharacterFile) {
            CharacterFile player = (CharacterFile) AbstractDungeon.player;
            player.transformToAssassin();
        }

        AbstractDungeon.effectList.add(new TransformEffect());
        this.usable = false;
    }

    @Override
    public void update() {
        super.update();
        if (!AbstractDungeon.player.hasRelic(TheDarkinScythe.ID) || TheDarkinScythe.blueOrbs < 1) {
            this.usable = false;
            this.img = DISABLED_IMG;
            this.description = uiStrings.TEXT[1];
        }
    }
}
