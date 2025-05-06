package collector.cards;

import collector.powers.CracklePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToSelf;

public class Pyromancy extends AbstractCollectorCard {
    public final static String ID = makeID(Pyromancy.class.getSimpleName());
    // intellij stuff power, self, rare, , , , , , 

    public Pyromancy() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CracklePower(this.magicNumber));
    }

    public void upp() {
//        upgradeMagicNumber(1);
        upgradeBaseCost(1);
    }
}