package collector.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class Torchbearer extends AbstractCollectorCard {
    public final static String ID = makeID(Torchbearer.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 6, 2

    public Torchbearer() {//Called Blindside
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 10;
        baseMagicNumber = magicNumber = 2;
//        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (int j = 0; j < magicNumber; j++){
            atb(new MakeTempCardInHandAction(new Shiv()));
        }
//        atb(new AddTemporaryHPAction(p, p, magicNumber));
    }

    public void upp() {
        upgradeBlock(1);
        upgradeMagicNumber(1);
    }
}