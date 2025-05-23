/*
package collector.ui;

import collector.CollectorCollection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
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
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import utilityClasses.DFL;

import java.util.stream.Collectors;

public class StashAwayCampfireEffect extends com.megacrit.cardcrawl.vfx.AbstractGameEffect {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("collector:StashAwayCampfireOption");
    public static final String[] TEXT = uiStrings.TEXT;

    private static final float DUR = 1.5F;
    private boolean openedScreen = false;
    private Color screenColor = AbstractDungeon.fadeColor.cpy();

    public StashAwayCampfireEffect() {
        this.duration = DUR;
        this.screenColor.a = 0.0F;
        AbstractDungeon.overlayMenu.proceedButton.hide();
    }

    public void update() {
        if (!AbstractDungeon.isScreenUp) {
            this.duration -= Gdx.graphics.getDeltaTime();
            updateBlackScreenColor();
        }

        if (!AbstractDungeon.isScreenUp) {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && AbstractDungeon.gridSelectScreen.forPurge) {

                AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                AbstractDungeon.effectsQueue.add(new PurgeCardEffect(c));
                CollectorCollection.collection.removeCard(c);
                AbstractDungeon.player.gainGold(StashAwayCampfireOption.GOLD_GRANTED);
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                ((RestRoom) AbstractDungeon.getCurrRoom()).fadeIn();

                if (!CollectorCollection.collection.isEmpty()) {//If the collected pile has cards left
                    CardGroup tempCollection = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    CollectorCollection.collection.group.forEach(Card -> {
                        if (!Card.upgraded) {
                            tempCollection.addToBottom(Card);
                        }
                    });
                    if (!tempCollection.isEmpty()) {//If there is an un-upgraded collectible left.
                        AbstractCard U = tempCollection.getRandomCard(AbstractDungeon.miscRng);
                        U.upgrade();
                        AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(U.makeStatEquivalentCopy()));
                        AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(0.5f * Settings.WIDTH, 0.5f * Settings.HEIGHT));
                    }
                }

            }
        }

        if ((this.duration < 1.0F) && (!this.openedScreen)) {
            this.openedScreen = true;
            CardGroup cg = CardGroup.getGroupWithoutBottledCards(CollectorCollection.collection);
            AbstractDungeon.gridSelectScreen.open(cg, 1, TEXT[4], false, false, true, true);
        }


        if (this.duration < 0.0F) {
            this.isDone = true;
            if (CampfireUI.hidden) {
                AbstractRoom.waitTimer = 0.0F;
                AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE;
                ((RestRoom) AbstractDungeon.getCurrRoom()).cutFireSound();
            }
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
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);

        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.GRID) {
            AbstractDungeon.gridSelectScreen.render(sb);
        }
    }

    public void dispose() {
    }
}
 */