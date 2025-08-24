package collector.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToEnemy;
import static utilityClasses.Wiz.makeInHand;

public class Heatwave extends AbstractCollectorCard {
    public final static String ID = makeID(Heatwave.class.getSimpleName());
    // intellij stuff attack, all_enemy, uncommon, 12, 4, , , , 

    public Heatwave() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 12;
        isMultiDamage = true;
        this.baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int q = 0; q < 6; q++) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new ShockWaveEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(MathUtils.random(1.0f), MathUtils.random(0, 0.2f), MathUtils.random(0, 0.2f), 1.0f), ShockWaveEffect.ShockWaveType.NORMAL)));
        }
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        for (AbstractMonster mo : DFL.activeMonsterList()) {
            applyToEnemy(mo, new VulnerablePower(mo, magicNumber, false));
        }
        //makeInHand(new Ember(), magicNumber);
    }

    public void upp() {
        upgradeDamage(5);
//        upgradeMagicNumber(1);
    }
}