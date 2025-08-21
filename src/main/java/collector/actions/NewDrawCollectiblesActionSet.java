package collector.actions;

import basemod.helpers.CardModifierManager;
import collector.cardmods.ActuallyCollectedCardMod;
import collector.cardmods.CollectedCardMod;
import collector.cards.collectibles.LuckyWick;
import collector.relics.HolidayCoal;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;
import java.util.ArrayList;
import static utilityClasses.Wiz.att;

public class NewDrawCollectiblesActionSet extends AbstractGameAction {
    private final boolean isRandom;
    private final int quantity;
    private final boolean canLoop;
    private final Random rngType;
    private static ArrayList<AbstractCard> cards = new ArrayList<>();

    public NewDrawCollectiblesActionSet(boolean random, int cards, boolean recur, Random rng) {
        this.actionType = ActionType.SPECIAL;
        isRandom = random;
        quantity = cards;
        canLoop = recur;
        rngType = rng;
    }

    @Override
    public void update() {
        cards.clear();
        for (AbstractCard card : DFL.pl().drawPile.group){//Filter for Collected cards only.
            if (CardModifierManager.hasModifier(card, ActuallyCollectedCardMod.ID)) {

                    cards.add(card);

            }
        }
        if (canLoop){
            for (AbstractCard card : DFL.pl().discardPile.group){
                if (CardModifierManager.hasModifier(card, ActuallyCollectedCardMod.ID)) {

                        cards.add(card);

                }
            }
        }

       for (int i = 0; i < quantity; i++){
            DFL.atb(new LaterAction(()->{//LaterAction prevents simultaneous card manipulation actions causing weird cards to be drawn.

            if (!cards.isEmpty()) {
                AbstractCard cardToDraw = null;
                if (!isRandom) {
                    cardToDraw = cards.get(0);
                }else{
                    if (cards.size() > 1) {
                        cardToDraw = cards.get(rngType.random(cards.size() - 1));//Random cards to draw.
                    }else{
                        cardToDraw = cards.get(0);
                    }
                }

                if (DFL.pl().drawPile.contains(cardToDraw)) {
                    if (DFL.pl().drawPile.size() >= 2) {
                        DFL.pl().drawPile.removeCard(cardToDraw);//Take out the card.
                        DFL.pl().drawPile.addToTop(cardToDraw);//Add to the top.
                    }//If there is only one card in the draw pile it has to be our collectible, draw it.
                    DFL.att(new DrawCardAction(1));//Draw it
                    cards.remove(cardToDraw);//After being drawn, remove in order to ensure next operation does not try to draw the same card.

                }else if (canLoop && DFL.pl().discardPile.contains(cardToDraw)){//If we are allowed to peek in the discard pile to draw.
                    DFL.pl().discardPile.removeCard(cardToDraw);//Remove from discard pile.
                    DFL.pl().drawPile.addToTop(cardToDraw);//Put in draw pile.
                    DFL.att(new DrawCardAction(1));//Draw it.
                    cards.remove(cardToDraw);//After being drawn, remove in order to ensure next operation does not try to draw the same card.
                }

            } else {//If there are no collectibles left, coal happens.
                if (AbstractDungeon.player.hasRelic(HolidayCoal.ID)) {
                    AbstractDungeon.player.getRelic(HolidayCoal.ID).flash();
                    AbstractCard tar = new LuckyWick();
                    CardModifierManager.addModifier(tar, new CollectedCardMod());
                    AbstractDungeon.player.drawPile.addToTop(tar);
                    att(new DrawCardAction(1));
                }
            }

           }));
        }
        this.isDone = true;
    }


}
