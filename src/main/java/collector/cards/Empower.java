package collector.cards;

import automaton.actions.EasyXCostAction;
import collector.powers.StrengthOverTurnsPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class Empower extends AbstractCollectorCard {
    public final static String ID = makeID(Empower.class.getSimpleName());
    // intellij stuff power, self, uncommon, , , , , 2, 1

    public Empower() {
        super(ID, -1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            att(new VFXAction(new HeartBuffEffect(DFL.pl().hb.cX, DFL.pl().hb.cY)));
            applyToSelfTop(new StrengthOverTurnsPower(2, effect + magicNumber));
            return true;
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}