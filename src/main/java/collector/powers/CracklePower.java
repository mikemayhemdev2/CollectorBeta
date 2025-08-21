package collector.powers;

import collector.actions.GainReservesAction;
import collector.actions.PyreAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;

public class CracklePower extends AbstractCollectorPower implements OnPyrePower {
    public static final String NAME = "Crackle";
    public static final String POWER_ID = makeID(NAME);
    public static final PowerType TYPE = PowerType.BUFF;
    public static final boolean TURN_BASED = false;
    private static AbstractGameAction lastActionCalled = null;

    public CracklePower(int addAmount) {
        super(NAME, TYPE, TURN_BASED, AbstractDungeon.player, null, addAmount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        DFL.atb(new LaterAction(()->{
            flash();
            if (!AbstractDungeon.player.hand.isEmpty()) {
                for (int a = 0; a < amount; a++){
                    PyreAction action = new PyreAction();
                    lastActionCalled = action;
                    addToTop(action);
                }
            }
        }));
    }

    @Override
    public void onPyre(AbstractCard card) {
        DFL.att(new LaterAction(()->{
        if (lastActionCalled != null) {
            if (lastActionCalled instanceof PyreAction){
                if (((PyreAction) lastActionCalled).lastCardWasKindling()){
                    DFL.atb(new GainReservesAction(1));
                }
            }
            lastActionCalled = null;
        }
        }));
    }

}