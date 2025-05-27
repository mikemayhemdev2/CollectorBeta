package collector.actions;

import collector.cards.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

/**
 * Blows up the players entire hand.
 * Now it is full of kindling!
 */
public class kaboomAction extends AbstractGameAction {

    final boolean Upgraded;
    private static final ArrayList<AbstractCard> kindlingList = new ArrayList<AbstractCard>(){{
        add(new Ember());
        add(new BramblesparKindling());
        add(new OakbrimKindling());
        add(new RotwoodKindling());
        add(new IronbarkKindling());
        add(new SunbloomKindling());
    }};

    public kaboomAction(boolean upgraded){
        this.Upgraded = upgraded;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        int count = AbstractDungeon.player.hand.size();

        for (int o = 0; o < count; o++) {//Blow up hand.
            if (Settings.FAST_MODE) {
                addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
            } else {
                addToTop(new ExhaustAction(1, true, true));
            }
        }

        for (int i = 0; i < count; i++) {//Random kindling's
            int pos = AbstractDungeon.cardRng.random(5);
            AbstractCard nextInHand = kindlingList.get(pos).makeCopy();

            if (Upgraded) {nextInHand.upgrade();}

            addToBot(new MakeTempCardInHandAction(nextInHand, 1));
        }
        this.isDone = true;
    }

}
