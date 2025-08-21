package collector.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Blind;
import com.megacrit.cardcrawl.cards.colorless.Trip;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class Invigorate extends AbstractCollectorCard {
    public final static String ID = makeID(Invigorate.class.getSimpleName());
    // intellij stuff skill, enemy, common, , , , , , 

    public Invigorate() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 2;
//        isPyre();
//        MultiCardPreview.add(this, new Trip(), new Blind());
//        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        int weakVulnCount = 0;
        if (m.hasPower(WeakPower.POWER_ID)){//If we have weak, counter go down.
            weakVulnCount -= m.getPower(WeakPower.POWER_ID).amount;
        }
        if (m.hasPower(VulnerablePower.POWER_ID)){//If we have vuln, counter goes up
            weakVulnCount += m.getPower(VulnerablePower.POWER_ID).amount;
        }

        if (weakVulnCount <= 0){//There is no vuln or more weak, default behaviour.
            applyToEnemy(m, new VulnerablePower(m, this.magicNumber, false));
        }else{//If there is actually more vuln, instead applies weak.
            applyToEnemy(m, new WeakPower(m, this.magicNumber, false));
        }
    }

    public void upp() {
//        upgradeMagicNumber(1);
        upgradeBaseCost(0);
    }
}
