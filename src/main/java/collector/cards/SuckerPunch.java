package collector.cards;

import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToEnemy;
import static utilityClasses.Wiz.atb;

public class SuckerPunch extends AbstractCollectorCard {
    public final static String ID = makeID(SuckerPunch.class.getSimpleName());
    // intellij stuff attack, enemy, common, 7, 2, , , 1, 1

    public SuckerPunch() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 5;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mm : DFL.activeMonsterList()){
            dmg(mm, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            applyToEnemy(mm, new WeakPower(mm, magicNumber, false));
        }
    }

    public void upp() {
        upgradeDamage(1);
        upgradeSecondMagic(1);
    }
}