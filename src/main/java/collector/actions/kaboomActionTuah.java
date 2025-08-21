package collector.actions;

import collector.cards.Ember;
import collector.cards.SunbloomKindling;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * Awakened one needs a hawk tuah joke somewhere in the beta art.
 */
public class kaboomActionTuah extends AbstractGameAction {

    final int magicN;

    public kaboomActionTuah(int magic){
        magicN = magic;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        int count = AbstractDungeon.player.hand.size();
        int i;
        for (i = 0; i < count; i++) {
            if (Settings.FAST_MODE) {
                addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
            } else {
                addToTop(new ExhaustAction(1, true, true));
            }
        }
        for (i = 0; i < count; i++){
            if (magicN > 1){
                SunbloomKindling sk = new SunbloomKindling();
                sk.upgrade();
                addToBot(new MakeTempCardInHandAction(sk.makeStatEquivalentCopy(), 1));

            }else{
                Ember em = new Ember();
                em.upgrade();
                addToBot(new MakeTempCardInHandAction(em.makeStatEquivalentCopy(), 1));
            }
        }

        this.isDone = true;
    }

}