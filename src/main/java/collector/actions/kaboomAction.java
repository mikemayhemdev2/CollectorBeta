package collector.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * Blows up the players entire hand.
 */
public class kaboomAction  extends AbstractGameAction {

    private float startingDuration = Settings.ACTION_DUR_FAST;

    public void update() {
        if (this.duration == this.startingDuration) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));//Cards are kill.
            }
            this.isDone = true;
        }
    }

}
