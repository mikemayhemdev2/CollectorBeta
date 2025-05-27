package collector.cards;

import collector.powers.DoomPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

import static collector.CollectorMod.makeID;
import static utilityClasses.DFL.applyToEnemy;
import static utilityClasses.Wiz.applyToEnemyTop;
import static utilityClasses.Wiz.atb;

public class Goodbye extends AbstractCollectorCard {
    public final static String ID = makeID(Goodbye.class.getSimpleName());
    // intellij stuff skill, enemy, rare, , , , , 2, 1

    public Goodbye() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 7;
       // exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(m, new VerticalAuraEffect(Color.BLACK, m.hb.cX, m.hb.cY), 0.1F));
        this.addToBot(new VFXAction(m, new VerticalAuraEffect(Color.PURPLE, m.hb.cX, m.hb.cY), 0.0F));
        this.addToBot(new VFXAction(m, new VerticalAuraEffect(Color.CYAN, m.hb.cX, m.hb.cY), 0.0F));
        applyToEnemy(m, new DoomPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}