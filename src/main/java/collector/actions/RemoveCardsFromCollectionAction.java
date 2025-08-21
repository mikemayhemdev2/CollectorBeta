package collector.actions;

import collector.CollectorCollection;
import collector.patches.CollectorBottleField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;

public class RemoveCardsFromCollectionAction extends AbstractGameAction {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("CollectibleCardReward"));
    private boolean hasCalledScreen = false;
    private final int requiredSize;
    private boolean bing = true;
    private boolean bong = false;

    public RemoveCardsFromCollectionAction(int input){
        this.requiredSize = input;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_LONG;
        update();
    }

    @Override
    public void update() {
        if (!hasCalledScreen) {//Screen not yet called.
                //Part 1
                if (AbstractDungeon.isScreenUp) {
                    AbstractDungeon.dynamicBanner.hide();
                    AbstractDungeon.overlayMenu.cancelButton.hide();
                    AbstractDungeon.previousScreen = AbstractDungeon.screen;
                }

                CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                for (AbstractCard col : CollectorCollection.collection.group) {
                    if (!CollectorBottleField.inCollectionBottle.get(col)) {
                        tmp.addToTop(col);
                    }
                }
                AbstractDungeon.gridSelectScreen.open(tmp, requiredSize, uiStrings.TEXT[2], false, false, false, true);//Note only uiStrings[2] is used anymore.
        } else {//Screen was called already.
            //Part 2
            if (AbstractDungeon.isScreenUp) {
                if (AbstractDungeon.gridSelectScreen.selectedCards.size() == requiredSize) {

                    //AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                    for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                        AbstractDungeon.effectsQueue.add(new PurgeCardEffect(c));
                    }
                    CollectorCollection.collection.group.removeAll(AbstractDungeon.gridSelectScreen.selectedCards);
                    AbstractDungeon.gridSelectScreen.selectedCards.clear();

                    this.isDone = true;
                }
            }
        }
        if (!this.isDone){
            //tickDuration();
            if (bing){
                bing = false;
                bong = true;
            }else{
                bing = true;
                bong = false;
            }
            update();
        }
    }
}
