package kayncode.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import kayncode.util.TexLoader;

public class ReapEffect extends AbstractGameEffect {
    private static int blockSound = 0;
    public TextureAtlas.AtlasRegion img;
    private float x;
    private float y;
    private float sY;
    private float tY;
    private AbstractGameAction.AttackEffect effect;
    private boolean triggered;

    public ReapEffect(float x, float y, AbstractGameAction.AttackEffect effect, boolean mute) {
        this.triggered = false;
        this.duration = 0.6F;
        this.startingDuration = 0.4F;
        this.effect = effect;
        this.img = TexLoader.getTextureAsAtlasRegion("theshadowreaperResources/images/vfx/reap.png");
        if (this.img != null) {
            this.x = x - (float)this.img.packedWidth / 2.0F;
            this.y = y - (float)this.img.packedHeight / 2.0F;
        }

        this.color = Color.RED.cpy();
        this.scale = Settings.scale;
        CardCrawlGame.sound.play("REAP_EFFECT_SOUND");
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
            this.color.a = 0.0F;
        } else if (this.duration < 0.2F) {
            this.color.a = this.duration * 5.0F;
        } else {
            this.color.a = Interpolation.fade.apply(1.0F, 0.0F, this.duration * 0.75F / 0.6F);
        }
    }

    public void render(SpriteBatch sb) {
        if (this.img != null) {
            sb.setColor(this.color);
            sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);
        }
    }

    public void dispose() {
    }
}