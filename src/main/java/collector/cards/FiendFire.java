package collector.cards;

import collector.actions.kaboomAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import utilityClasses.DFL;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class FiendFire extends AbstractCollectorCard {
    public final static String ID = makeID(FiendFire.class.getSimpleName());

    public FiendFire() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
//        exhaust = true; - No touchy or else.
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new kaboomAction(this.upgraded));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse){
            return false;
        }else if (DFL.pl().hand.size() < 2) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return true;
    }

    public void upp() {
        this.selfRetain = true;
        uDesc();
    }
}