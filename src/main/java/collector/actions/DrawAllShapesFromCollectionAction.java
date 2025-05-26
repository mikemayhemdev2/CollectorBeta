package collector.actions;

import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.characters.AbstractPlayer.MaxHandSizePatch;
import collector.CollectorCollection;
import collector.CollectorMod;
import collector.cardmods.CollectedCardMod;
import collector.cards.collectibles.LuckyWick;
import collector.relics.HolidayCoal;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static utilityClasses.Wiz.att;

public class DrawAllShapesFromCollectionAction extends AbstractGameAction {

    public DrawAllShapesFromCollectionAction() {
        this.actionType = ActionType.SPECIAL;
    }
    @Override
    public void update() {
        if (!AbstractDungeon.player.drawPile.isEmpty()) {

            for (int i = 0; i < AbstractDungeon.player.drawPile.group.size(); i++) {

                AbstractCard tar = AbstractDungeon.player.drawPile.group.get(i);
                if (tar.hasTag(CollectorMod.SHAPESWARM)){

                    AbstractDungeon.player.drawPile.removeCard(tar);
                    CardModifierManager.removeModifiersById(tar, CollectedCardMod.ID, true);
                    AbstractDungeon.player.drawPile.addToTop(tar);
                    att(new DrawCardAction(1));
                    break;
                }
            }


        }

        this.isDone = true;
    }
}