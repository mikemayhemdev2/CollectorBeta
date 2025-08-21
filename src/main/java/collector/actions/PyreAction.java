package collector.actions;

import collector.cards.OnPyreCard;
import collector.powers.OnPyrePower;
import collector.relics.OnPyreRelic;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static utilityClasses.Wiz.att;

public class PyreAction extends AbstractGameAction {//My god pyre code is so bad atm.
    private AbstractCard theCard;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("collector:PyreCostSpendScreen");

    public PyreAction(){
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        DFL.att(new SelectCardsInHandAction(uiStrings.TEXT[0], (cards) -> {
            for (AbstractPower pow : AbstractDungeon.player.powers) {
                if (pow instanceof OnPyrePower) {
                    ((OnPyrePower) pow).onPyre(cards.get(0));
                }
            }
            for (AbstractRelic rel : AbstractDungeon.player.relics) {
                if (rel instanceof OnPyreRelic) {
                    ((OnPyreRelic) rel).onPyre(cards.get(0));
                }
            }
            DFL.att(new ExhaustSpecificCardAction(cards.get(0), AbstractDungeon.player.hand));
            theCard = cards.get(0);
        }));
        this.isDone = true;
    }

    /**
     * Make sure to use "laterAction" when using this to avoid CME style NPE crashes.
     * @return - The actual instance of the card.
     */
    public AbstractCard lastCard(){
        return theCard;
    }

    /**
     * Make sure to use "laterAction" when using this to avoid CME style NPE crashes.
     * @return - If the card is kindling, true, otherwise false.
     */
    public boolean lastCardWasKindling(){
        return theCard.tags.contains(expansionContentMod.KINDLING);
    }
}
