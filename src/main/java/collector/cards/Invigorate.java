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
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 1;
//        isPyre();
//        MultiCardPreview.add(this, new Trip(), new Blind());
//        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        int weakVulnCount = 0;
        if (m.hasPower(WeakPower.POWER_ID)){//If we have weak, counter go up.
            weakVulnCount += m.getPower(WeakPower.POWER_ID).amount;
        }
        if (m.hasPower(VulnerablePower.POWER_ID)){//If we have vuln, counter goes down
            weakVulnCount -= m.getPower(VulnerablePower.POWER_ID).amount;
        }

        if (weakVulnCount <= 0){//There is no weak or positive vuln, default behaviour.
            applyToEnemy(m, new WeakPower(m, this.magicNumber, false));
        }else{//If there is actually more weak than vuln, instead apply vuln.
            applyToEnemy(m, new VulnerablePower(m, this.magicNumber, false));
        }

        /*
        ArrayList<AbstractCard> cards = new ArrayList<>();
        cards.add(new Trip());
        cards.add(new Blind());
        if (upgraded){
            for (AbstractCard c : cards)
            {
                c.upgrade();
            }
        }
        atb(new SelectCardsCenteredAction(cards, 1, cardStrings.EXTENDED_DESCRIPTION[0], (selected) -> {
            makeInHandTop(selected.get(0));
        }));
         */
    }

    public void upp() {
        /*
        AbstractCard q = new Trip();
        AbstractCard q2 = new Blind();
        q.upgrade();
        q2.upgrade();
        MultiCardPreview.clear(this);
        MultiCardPreview.add(this, q, q2);
        uDesc();
        */
        upgradeMagicNumber(1);
    }
}
