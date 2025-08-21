package collector.actions;

import collector.cards.*;
import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import utilityClasses.DFL;

import java.util.ArrayList;

/**
 * Blows up the players entire hand.
 * Now it is full of kindling!
 */
public class kaboomAction extends AbstractGameAction {

    final AbstractMonster targeted;
    final int magicN;

    /*
    private static final ArrayList<AbstractCard> kindlingList = new ArrayList<AbstractCard>(){{
        add(new Ember());
        add(new BramblesparKindling());
        add(new OakbrimKindling());
        add(new RotwoodKindling());
        add(new IronbarkKindling());
        add(new SunbloomKindling());
    }};
     */

    public kaboomAction(int magic, AbstractMonster target){
        magicN = magic;
        targeted = target;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        int count = AbstractDungeon.player.hand.size();
        int i;
        for (i = 0; i < count; i++){
            addToBot(new ApplyPowerAction(targeted, DFL.pl(), new DoomPower(targeted, magicN), magicN));
        }
        for (i = 0; i < count; i++) {
            if (Settings.FAST_MODE) {
                addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
            } else {
                addToTop(new ExhaustAction(1, true, true));
            }
        }
        this.isDone = true;
    }

}
