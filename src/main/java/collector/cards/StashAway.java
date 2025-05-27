package collector.cards;

import collector.actions.GainReservesAction;
import collector.powers.NextTurnReservePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import utilityClasses.DFL;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class StashAway extends AbstractCollectorCard {
    public final static String ID = makeID(StashAway.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , , 

    public StashAway() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = 5;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        int energy = energyOnUse;
        DFL.pl().energy.use(EnergyPanel.totalCount);
        atb(new GainReservesAction(energy));
    }

    public void upp() {
        upgradeBlock(4);
    }
}