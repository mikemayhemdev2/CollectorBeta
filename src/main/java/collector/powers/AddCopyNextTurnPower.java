package collector.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import utilityClasses.DFL;

import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.makeInHand;

public class AddCopyNextTurnPower extends AbstractCollectorPower implements NonStackablePower {
    public static final String NAME = "AddCopyNextTurn";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;

    private AbstractCard tar;

    public AddCopyNextTurnPower(AbstractCard tar) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, -1);
        canGoNegative = false;
        this.tar = tar;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        flash();
        DFL.att(new MakeTempCardInHandAction(tar.makeStatEquivalentCopy()));//Unconventional but needed due to collect draw occupying the same speed.
        DFL.att(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        if (tar != null)
            description = DESCRIPTIONS[0] + FontHelper.colorString(tar.name, "y") + DESCRIPTIONS[1];
    }
}