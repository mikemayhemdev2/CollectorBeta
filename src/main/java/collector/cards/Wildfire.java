
package collector.cards;

import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class Wildfire extends AbstractCollectorCard {
    public final static String ID = makeID(Wildfire.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 16, 5, , , , 

    public Wildfire() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 4;
        baseMagicNumber = magicNumber = 1;
        isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int count = 0;
                for (AbstractPower q : m.powers) {
                    if (q.type == AbstractPower.PowerType.DEBUFF) {
                        dmg(m, AttackEffect.FIRE);
                        count++;
                    }
                }
                if (count >= 1){
                    for (int j = 0; count > j; j++){
                        DFL.atb(new ApplyPowerAction(m, p, new DoomPower(m, magicNumber), magicNumber));
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }

    @Override //zhs card text thing
    public void initializeDescriptionCN() {
        super.initializeDescriptionCN();
        if((Settings.language == Settings.GameLanguage.ZHS || Settings.language == Settings.GameLanguage.ZHT) && this.description!=null && this.description.size()>=1 ) {
            for(int i=0; i < this.description.size(); i++){
                if(this.description.get(i).text.equals("，")){
                    StringBuilder sb = new StringBuilder();
                    this.description.get(i-1).text = sb.append(this.description.get(i-1).text).append("，").toString();
                    this.description.remove(i);
                }
            }
        }
    }
}

