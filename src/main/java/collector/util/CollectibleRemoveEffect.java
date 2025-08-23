package collector.util;

import collector.CollectorCollection;
import collector.patches.CollectorBottleField;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.rooms.RestRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import guardian.GuardianMod;
import guardian.cards.AbstractGuardianCard;
import slimebound.SlimeboundMod;

import java.util.Iterator;
import java.util.Random;

import static collector.CollectorMod.makeID;

public class CollectibleRemoveEffect extends AbstractGameEffect {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("CollectibleCardReward"));

    public boolean openedScreen = false;
    public boolean removalComplete = false;


    public int removeCardCount = 0;
    private Color screenColor;

    public CollectibleRemoveEffect(int removeCount) {
        this.screenColor = AbstractDungeon.fadeColor.cpy();
        this.duration = 1.0F;
        this.screenColor.a = 0.0F;
        AbstractDungeon.overlayMenu.proceedButton.hide();
        removeCardCount = removeCount;
        SlimeboundMod.logger.info("New collectible remove effect made");

    }

    public void update() {

           // SlimeboundMod.logger.info("Black Screen tick");
            this.duration -= Gdx.graphics.getDeltaTime();
            this.updateBlackScreenColor();

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() == removeCardCount && AbstractDungeon.gridSelectScreen.forPurge) {

            SlimeboundMod.logger.info("Removing cards");
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                if (removeCardCount == 1){
                    AbstractDungeon.effectsQueue.add(new PurgeCardEffect(c, (Settings.WIDTH * .5F), (Settings.HEIGHT * .5F)));

                } else{
                    AbstractDungeon.effectsQueue.add(new PurgeCardEffect(c, (Settings.WIDTH * AbstractDungeon.cardRandomRng.random(0.25F,0.75F)), (Settings.HEIGHT * AbstractDungeon.cardRandomRng.random(0.25F,0.75F))));

                }
                AbstractDungeon.player.masterDeck.removeCard(c);
            }
            CollectorCollection.collection.group.removeAll(AbstractDungeon.gridSelectScreen.selectedCards);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            CardCrawlGame.sound.play("CARD_EXHAUST");
            AbstractDungeon.dungeonMapScreen.open(false);
            this.duration = 1.0F;
            removalComplete = true;
        }

        if (this.duration <= 1.0F && !this.openedScreen) {

            SlimeboundMod.logger.info("Screen opening");
            this.openedScreen = true;

            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            for (AbstractCard col : CollectorCollection.collection.group) {
                if (!CollectorBottleField.inCollectionBottle.get(col)) {
                    tmp.addToTop(col);
                }
            }
            AbstractDungeon.gridSelectScreen.open(tmp, removeCardCount, uiStrings.TEXT[2] + removeCardCount + uiStrings.TEXT[3], false, false, false, true);
        }

        else if (duration <= 0.0F && removalComplete) {
            SlimeboundMod.logger.info("Removal complete");
            this.isDone = true;
        }

    }

    private void updateBlackScreenColor() {
        if (this.duration > 1.0F) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.0F) * 2.0F);
        } else {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / 1.5F);
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);

        if (!this.isDone && this.openedScreen && AbstractDungeon.gridSelectScreen != null){
            AbstractDungeon.gridSelectScreen.render(sb);
        }

    }

    public void dispose() {
        SlimeboundMod.logger.info("New collectible remove effect disposed");
    }
}
