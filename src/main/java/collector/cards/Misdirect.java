package collector.cards;

import collector.powers.NextTurnReservePower;
import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToSelf;
import static utilityClasses.Wiz.atb;

public class Misdirect extends AbstractCollectorCard {
    public final static String ID = makeID(Misdirect.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , , 

    public Misdirect() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AnimateJumpAction(p));
        //blck();
        applyToSelf(new NextTurnBlockPower(p, block));
        applyToSelf(new NextTurnReservePower(magicNumber));
    }

    public void upp() {
        upgradeBlock(3);
    }
}