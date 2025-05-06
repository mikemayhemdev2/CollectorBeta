package collector.cards;

import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class YouAreMine extends AbstractCollectorCard {
    public final static String ID = makeID(YouAreMine.class.getSimpleName());
    // intellij stuff skill, enemy, rare, , , , , 4, 2

    public YouAreMine() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ALL);
        baseMagicNumber = magicNumber = 3;//Debuffs
        baseSecondMagic = secondMagic = 5;//Doom
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mm : DFL.activeMonsterList()){
            atb(new VFXAction(new CollectorCurseEffect(mm.hb.cX, mm.hb.cY), .2F));
            applyToEnemy(mm, new WeakPower(mm, magicNumber, false));
            applyToEnemy(mm, new VulnerablePower(mm, magicNumber, false));
            applyToEnemy(mm, new DoomPower(mm, secondMagic));
        }
    }

    public void upp() {
        upgradeSecondMagic(4);
        upgradeMagicNumber(1);
    }
}