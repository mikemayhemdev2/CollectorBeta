package collector.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import expansioncontent.expansionContentMod;

public class ShootingStarPower extends AbstractCollectorPower implements OnPyrePower {
    public static final String NAME = "ShootingStar";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    public ShootingStarPower() {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, 1);
    }

    int pyresThisTurn = 0;

    @Override
    public void atStartOfTurn() {
        pyresThisTurn = 0;
    }

    @Override
    public void onPyre(AbstractCard card) {
        if (pyresThisTurn < amount && card.tags.contains(expansionContentMod.KINDLING)) {
            flash();
            pyresThisTurn++;
            AbstractCard copy = card.makeStatEquivalentCopy();
//            copy.freeToPlayOnce = true; - Kindling's are unplayable anyway.
            addToBot(new MakeTempCardInHandAction(copy, true));
        }
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

}