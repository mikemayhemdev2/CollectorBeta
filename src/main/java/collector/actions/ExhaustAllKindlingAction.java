package collector.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import expansioncontent.expansionContentMod;

public class ExhaustAllKindlingAction  extends AbstractGameAction {
    private final float startingDuration;

    public ExhaustAllKindlingAction() {
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            for(AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.tags.contains(expansionContentMod.KINDLING)) {
                    this.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                }
            }

            this.isDone = true;
        }

    }
}

